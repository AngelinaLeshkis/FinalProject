package com.leverx.dealerstat.serviceimpl;

import com.leverx.dealerstat.DealerstatApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = DealerstatApplication.class,webEnvironment = WebEnvironment.RANDOM_PORT)
public class TraderControllerIntegrationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testAllEmployees() {
        assertTrue(
                restTemplate
                        .getForObject("http://localhost:" + port + "/traders/topOfTraders", List.class)
                        .size() > 0);
    }
}
