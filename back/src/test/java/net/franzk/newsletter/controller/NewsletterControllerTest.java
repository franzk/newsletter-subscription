package net.franzk.newsletter.controller;

import net.franzk.newsletter.domain.Subscription;
import net.franzk.newsletter.exception.EmailAlreadySubscribedException;
import net.franzk.newsletter.exception.NoSuchEmailSubscribedException;
import net.franzk.newsletter.service.NewsletterService;
import net.franzk.newsletter.testUtils.GenerateTestData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class NewsletterControllerTest {

    @InjectMocks
    private NewsletterController controllerUnderTest;

    @Mock
    private NewsletterService newsletterService;

    @Test
    void subscribeTest() throws EmailAlreadySubscribedException {
        // Arrange
        String testEmail = GenerateTestData.email();
        SubscriptionDto subscriptionDto = new SubscriptionDto();
        subscriptionDto.setEmail(testEmail);
        when(newsletterService.subscribe(testEmail)).thenReturn(new Subscription());
        // Act
        ResponseEntity<String> result = controllerUnderTest.subscribe(subscriptionDto);
        // Assert
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(newsletterService).subscribe(testEmail);

    }

    @Test
    void unsubscribeTest() throws NoSuchEmailSubscribedException {
        // Arrange
        String testEmail = GenerateTestData.email();
        SubscriptionDto subscriptionDto = new SubscriptionDto();
        subscriptionDto.setEmail(testEmail);
        // Act
        ResponseEntity<String> result = controllerUnderTest.unsubscribe(subscriptionDto);
        // Assert
        verify(newsletterService).unsubscribe(testEmail);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void getSubscriptionsTest() {
        // Act
        controllerUnderTest.getSubscriptions();
        // Assert
        verify(newsletterService).getSubscriptions();
    }

}
