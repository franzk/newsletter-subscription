package net.franzk.newsletter.service;

import net.franzk.newsletter.domain.Subscription;
import net.franzk.newsletter.exception.EmailAlreadySubscribedException;
import net.franzk.newsletter.exception.NoSuchEmailSubscribedException;
import net.franzk.newsletter.repository.SubscriptionRepository;
import net.franzk.newsletter.testUtils.GenerateTestData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class NewsletterServiceTest {

    @InjectMocks
    private NewsletterService serviceUnderTest;

    @Mock
    private SubscriptionRepository subscriptionRepository;

    @Test
    void subscribeTest() throws EmailAlreadySubscribedException {
        // Arrange
        String testEmail = GenerateTestData.email();
        when(subscriptionRepository.findByEmail(testEmail)).thenReturn(List.of());
        // Act
        serviceUnderTest.subscribe(testEmail);
        // Assert
        verify(subscriptionRepository).save(any());
    }

    @Test
    void subscribeTestEmailAlreadySubscribed() {
        // Arrange
        String testEmail = GenerateTestData.email();
        Subscription subscription = new Subscription();
        subscription.setEmail(testEmail);
        when(subscriptionRepository.findByEmail(testEmail)).thenReturn(List.of(subscription));
        // Act & Assert
        assertThrows(EmailAlreadySubscribedException.class, () -> serviceUnderTest.subscribe(testEmail));
    }

    @Test
    void unsubscribeTest() throws NoSuchEmailSubscribedException {
        // Arrange
        String testEmail = GenerateTestData.email();
        Subscription subscription = new Subscription();
        subscription.setEmail(testEmail);
        List<Subscription> result = List.of(subscription);
        when(subscriptionRepository.findByEmail(testEmail)).thenReturn(result);
        // Act
        serviceUnderTest.unsubscribe(testEmail);
        // Assert
        verify(subscriptionRepository).deleteAll(result);
    }

    @Test
    void unsubscribeTestNoSuchEmailSubscribed() {
        // Arrange
        String testEmail = GenerateTestData.email();
        when(subscriptionRepository.findByEmail(testEmail)).thenReturn(List.of());
        // Act & Assert
        assertThrows(NoSuchEmailSubscribedException.class, () -> serviceUnderTest.unsubscribe(testEmail));
    }

    @Test
    void getSubscriptionsTest() {
        // Act
        serviceUnderTest.getSubscriptions();
        // Assert
        verify(subscriptionRepository).findAll();
    }

}
