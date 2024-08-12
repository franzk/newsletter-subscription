package net.franzk.newsletter.testUtils;

import net.bytebuddy.utility.RandomString;
import net.franzk.newsletter.domain.Subscription;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GenerateTestData {

    public static String email() {
        return RandomString.make(new Random().nextInt(40) + 1) +
                "@" +
                RandomString.make(new Random().nextInt(40) + 1)  +
                ".com";
    }

    public static List<Subscription> subscriptions(int count) {
        List<Subscription> subscriptions = new ArrayList<>();
        for(int i = 0; i < count; i++) {
            Subscription subscription = new Subscription();
            subscription.setEmail(email());
            subscriptions.add(subscription);
        }
        return subscriptions;
    }
}
