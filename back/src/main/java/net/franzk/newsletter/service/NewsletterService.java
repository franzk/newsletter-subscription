package net.franzk.newsletter.service;

import net.franzk.newsletter.controller.SubscriptionDto;
import net.franzk.newsletter.domain.Subscription;
import net.franzk.newsletter.exception.EmailAlreadySubscribedException;
import net.franzk.newsletter.exception.NoSuchEmailSubscribedException;
import net.franzk.newsletter.repository.SubscriptionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for newsletter subscriptions.
 */
@Service
public class NewsletterService {

    private final SubscriptionRepository subscriptionRepository;

    public NewsletterService(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    /**
     * Subscribe to the newsletter
     *
     * @param email the email to subscribe
     * @return the subscription
     * @throws EmailAlreadySubscribedException if the email is already subscribed
     */
    public Subscription subscribe(String email) throws EmailAlreadySubscribedException {

        // Cette adresse est-elle déjà inscrite ?
        if (!subscriptionRepository.findByEmail(email).isEmpty()) {
            throw new EmailAlreadySubscribedException();
        }

        Subscription subscription = new Subscription();
        subscription.setEmail(email);
        return subscriptionRepository.save(subscription);
    }

    /**
     * Get all subscriptions
     *
     * @return list of subscriptions
     */
    public List<SubscriptionDto> getSubscriptions() {
        return subscriptionRepository.findAll().stream()
                .map(subscription -> {
                    SubscriptionDto subscriptionDto = new SubscriptionDto();
                    subscriptionDto.setEmail(subscription.getEmail());
                    return subscriptionDto;
                })
                .toList();
    }

    /** Unsubscribe from the newsletter
     *
     * @param email the email to unsubscribe
     * @throws NoSuchEmailSubscribedException if the email is not subscribed
     */
    public void unsubscribe(String email) throws NoSuchEmailSubscribedException {
        List<Subscription> subscriptions = subscriptionRepository.findByEmail(email);
        if (subscriptions.isEmpty()) {
            throw new NoSuchEmailSubscribedException();
        }
        subscriptionRepository.deleteAll(subscriptions);
    }
}
