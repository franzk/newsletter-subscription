package net.franzk.newsletter.integration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.bytebuddy.utility.RandomString;
import net.franzk.newsletter.controller.SubscriptionDto;
import net.franzk.newsletter.domain.Subscription;
import net.franzk.newsletter.repository.SubscriptionRepository;
import net.franzk.newsletter.testUtils.GenerateTestData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = "CORS_ALLOWED_ORIGIN=http://localhost")
public class NewsletterControllerTestIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        subscriptionRepository.deleteAll();
    }

    @DisplayName("POST /api/newsletter/subscribe should return 200 OK and subscribe the email")
    @Test
    void subscribeTestIT() throws Exception {
        // Arrange
        String testEmail = GenerateTestData.email();
        SubscriptionDto subscriptionDto = new SubscriptionDto();
        subscriptionDto.setEmail(testEmail);
        String body = objectMapper.writeValueAsString(subscriptionDto);
        // Act
        mockMvc.perform(post("/api/newsletter/subscribe")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andDo(print())
                .andExpect(status().isOk());
        // Assert
        List<Subscription> subscriptions = subscriptionRepository.findAll();
        assertThat(subscriptions.stream().anyMatch(s -> s.getEmail().equals(testEmail))).isTrue();

    }

    @DisplayName("POST /api/newsletter/subscribe with invalid email should return 400 Bad Request")
    @Test
    void subscribeTestITInvalidEmail() throws Exception {
        // Arrange
        SubscriptionDto subscriptionDto = new SubscriptionDto();
        subscriptionDto.setEmail(RandomString.make(10));
        String body = objectMapper.writeValueAsString(subscriptionDto);
        // Act & Assert
        mockMvc.perform(post("/api/newsletter/subscribe")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @DisplayName("POST /api/newsletter/subscribe an already subscribed email should return 409 Conflict")
    @Test
    void subscribeTestITAlreadySubscribed() throws Exception {
        // Arrange
        String testEmail = GenerateTestData.email();
        SubscriptionDto subscriptionDto = new SubscriptionDto();
        subscriptionDto.setEmail(testEmail);
        Subscription subscription = new Subscription();
        subscription.setEmail(testEmail);
        subscriptionRepository.saveAndFlush(subscription);
        String body = objectMapper.writeValueAsString(subscriptionDto);
        // Act & Assert
        mockMvc.perform(post("/api/newsletter/subscribe")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andDo(print())
                .andExpect(status().isConflict());
    }

    @DisplayName("POST /api/newsletter/unsubscribe should return 200 OK and unsubscribe the email")
    @Test
    void unsubscribeTestIT() throws Exception {
        // Arrange
        String testEmail = GenerateTestData.email();
        Subscription subscription = new Subscription();
        subscription.setEmail(testEmail);
        subscriptionRepository.saveAndFlush(subscription);
        SubscriptionDto subscriptionDto = new SubscriptionDto();
        subscriptionDto.setEmail(testEmail);
        String body = objectMapper.writeValueAsString(subscriptionDto);
        // Act
        mockMvc.perform(post("/api/newsletter/unsubscribe")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andDo(print())
                .andExpect(status().isOk());
        // Assert
        List<Subscription> subscriptions = subscriptionRepository.findAll();
        assertThat(subscriptions.stream().noneMatch(s -> s.getEmail().equals(testEmail))).isTrue();
    }

    @DisplayName("POST /api/newsletter/unsubscribe an not subscribed email should return 404 Not Found")
    @Test
    void unsubscribeTestITNotSubscribed() throws Exception {
        // Arrange
        String testEmail = GenerateTestData.email();
        SubscriptionDto subscriptionDto = new SubscriptionDto();
        subscriptionDto.setEmail(testEmail);
        String body = objectMapper.writeValueAsString(subscriptionDto);
        // Act & Assert
        mockMvc.perform(post("/api/newsletter/unsubscribe")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @DisplayName("GET /api/newsletter/list should return 200 OK and list all subscriptions")
    @Test
    void listTestIT() throws Exception {
        // Arrange
        List<Subscription> subscriptions = GenerateTestData.subscriptions(5);
        subscriptionRepository.saveAll(subscriptions);
        // Act
        ResultActions resultActions = mockMvc.perform(get("/api/newsletter/mailing-list"))
                .andDo(print())
                .andExpect(status().isOk());
        // Assert
        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        List<SubscriptionDto> resultData = objectMapper.readValue(contentAsString, new TypeReference<>() {});
        assertThat(resultData.size()).isEqualTo(subscriptions.size());
        // compare the 2 lists of emails
        Set<String> dataEmails = subscriptions.stream().map(Subscription::getEmail).collect(Collectors.toSet());
        Set<String> resultEmails = resultData.stream().map(SubscriptionDto::getEmail).collect(Collectors.toSet());
        assertThat(dataEmails).isEqualTo(resultEmails);
    }
}
