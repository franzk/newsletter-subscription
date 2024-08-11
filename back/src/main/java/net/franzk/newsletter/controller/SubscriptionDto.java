package net.franzk.newsletter.controller;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class SubscriptionDto {
    @Pattern(
        regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
        message = "Invalid email address."
    )
    String email;
}
