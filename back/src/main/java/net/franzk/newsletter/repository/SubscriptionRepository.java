package net.franzk.newsletter.repository;

import net.franzk.newsletter.domain.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SubscriptionRepository extends JpaRepository<Subscription, UUID> {
    List<Subscription> findByEmail(String email);
}
