package org.testcontainers.bookstore.redpanda.orders.event.handlers;

import com.github.dockerjava.api.command.InspectContainerResponse;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.bookstore.orders.event.handlers.OrderCancelledEventHandlerTest;
import org.testcontainers.images.builder.Transferable;
import org.testcontainers.redpanda.RedpandaContainer;

public class RedpandaOrderCancelledEventHandlerTest extends OrderCancelledEventHandlerTest {
    @BeforeAll
    static void setupKafka() {
        kafka = getRedpanda();
    }

    @DynamicPropertySource
    static void overrideProperties(DynamicPropertyRegistry registry) {
        overridePropertiesInternal(registry);
    }
}