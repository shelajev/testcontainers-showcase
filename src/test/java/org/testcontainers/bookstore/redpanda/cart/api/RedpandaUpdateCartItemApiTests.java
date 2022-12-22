package org.testcontainers.bookstore.redpanda.cart.api;

import com.github.dockerjava.api.command.InspectContainerResponse;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.bookstore.cart.api.UpdateCartItemApiTests;
import org.testcontainers.images.builder.Transferable;
import org.testcontainers.redpanda.RedpandaContainer;

public class RedpandaUpdateCartItemApiTests extends UpdateCartItemApiTests {
    @BeforeAll
    static void setupKafka() {
        kafka = getRedpanda();
    }

    @DynamicPropertySource
    static void overrideProperties(DynamicPropertyRegistry registry) {
        overridePropertiesInternal(registry);
    }
}