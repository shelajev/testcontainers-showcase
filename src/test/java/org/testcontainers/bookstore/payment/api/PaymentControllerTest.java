package org.testcontainers.bookstore.payment.api;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.bookstore.common.AbstractIntegrationTest;
import org.testcontainers.bookstore.payment.domain.CreditCard;
import org.testcontainers.bookstore.payment.domain.CreditCardRepository;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

public class PaymentControllerTest extends AbstractIntegrationTest {

    @DynamicPropertySource
    static void overrideProperties(DynamicPropertyRegistry registry) {
        overridePropertiesInternal(registry);
    }

    @Autowired
    private CreditCardRepository creditCardRepository;

    @BeforeEach
    void setUp() {
        creditCardRepository.deleteAllInBatch();
        creditCardRepository.save( new CreditCard(null, "Siva", "1111222233334444", "123", 2, 2025));
    }

    //@Test
    @RepeatedTest(4)
    void shouldAuthorizePaymentSuccessfully() {
        given()
                .contentType(ContentType.JSON)
                .body(
                        """
                        {
                            "cardNumber": "1111222233334444",
                            "cvv": "123",
                            "expiryMonth": 2,
                            "expiryYear": 2025
                        }
                        """
                )
                .when()
                .post("/api/payments/authorize")
                .then()
                .statusCode(200)
                .body("status", is("ACCEPTED"));
    }

    //@Test
    @RepeatedTest(4)
    void shouldRejectPaymentWhenCVVIsIncorrect() {
        given()
                .contentType(ContentType.JSON)
                .body(
                        """
                        {
                            "cardNumber": "1111222233334444",
                            "cvv": "111",
                            "expiryMonth": 2,
                            "expiryYear": 2024
                        }
                        """
                )
                .when()
                .post("/api/payments/authorize")
                .then()
                .statusCode(200)
                .body("status", is("REJECTED"));
    }

    //@Test
    @RepeatedTest(4)
    void shouldFailWhenMandatoryDataIsMissing() {
        given()
                .contentType(ContentType.JSON)
                .body(
                        """
                        {
                            "cardNumber": "1111222233334444",
                            "cvv": "111"
                        }
                        """
                )
                .when()
                .post("/api/payments/authorize")
                .then()
                .statusCode(400);
    }
}