package org.testcontainers.bookstore.redpanda.payment.api;

import com.github.dockerjava.api.command.InspectContainerResponse;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.bookstore.payment.api.PaymentControllerTest;
import org.testcontainers.images.builder.Transferable;
import org.testcontainers.redpanda.RedpandaContainer;

public class RedpandaPaymentControllerTest extends PaymentControllerTest {
    @BeforeAll
    static void setupKafka() {
        kafka = getRedpanda();
    }

    @DynamicPropertySource
    static void overrideProperties(DynamicPropertyRegistry registry) {
        overridePropertiesInternal(registry);
    }
}