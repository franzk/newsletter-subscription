package net.franzk.newsletter.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

/**
 * Entity class for newsletter subscriptions.
 */
@Entity
@Data
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String email;
}
