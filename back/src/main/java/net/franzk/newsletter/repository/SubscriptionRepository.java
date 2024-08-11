package net.franzk.newsletter.repository;

import net.franzk.newsletter.domain.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * Repository for newsletter subscriptions.
 */
public interface SubscriptionRepository extends JpaRepository<Subscription, UUID> {

    /**
     * Find subscriptions by email.
     *
     * @param email the email to search
     * @return list of subscriptions
     */
    List<Subscription> findByEmail(String email);
}
