package com.example.actuator;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.LocalManagementPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasEntry;

// The @RunWith(SpringRunner.class) annotation tells JUnit to run the test using Spring's testing
// support.
@RunWith(SpringRunner.class)

// The @SpringBootTest annotation tells Spring Boot to look for a main configuration class
// (one with @SpringBootApplication, for instance) and use that to start a Spring application
// context.
@SpringBootTest(
        // webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT makes the HTTP test double
        // start at a random port.
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        // properties = "management.port=0" makes the Management context run on a different port.
        properties = "management.port=0")

public class LoginServiceIntegrationTest {
    // RestTemplate is a synchronous HTTP client that we can use to consume HTTP web services.
    private final RestTemplate restTemplate = new RestTemplate();

    // @LocalManagementPort annotation is used to inject the HTTP port that got allocated at
    // runtime.
    @LocalManagementPort private int port;

    // @Autowired annotation is used to auto-wire spring bean on setter methods, instance
    // variable, and constructor.
    @Autowired private LoginServiceImpl loginService;

    // ObjectMapper provides functionality for reading and writing JSON, either to and from basic
    // POJOs (Plain Old Java Objects), or to and from a general-purpose JSON Tree Model
    // (JsonNode), as well as related functionality for performing conversions.
    @Autowired private ObjectMapper objectMapper;

    // The @Test annotation tells JUnit that the public void method to which it is attached can
    // be run as a test case.
    @Test public void whenLoginIsAdmin_thenSuccessCounterIsIncremented() throws IOException {
        // login() method is called on the loginService instance with "admin" as username and
        // "secret" as password.
        boolean success = loginService.login("admin", "secret".toCharArray());

        // restTemplate.getForEntity() method retrieves a representation by doing a GET on the
        // specified URL.
        ResponseEntity<String> entity = restTemplate.getForEntity(
                "http://localhost:" + port + "/metrics", String.class);

        // objectMapper.readValue() method parse JSON content from given JSON content String.
        Map<String, Object> response = objectMapper.readValue(entity.getBody(),
                                                              new TypeReference<HashMap<String,
                                                                      Object>>() {});

        // assertThat() is a statement in JUnit. If the statement does not throw an exception,
        // then the test case is assumed to have succeeded.
        assertThat(success, is(true));
        assertThat(entity.getStatusCode(), is(HttpStatus.OK));
        assertThat(response, hasEntry("counter.login.success", 1));
    }

    @Test public void whenLoginIsNotAdmin_thenFailureCounterIsIncremented() throws IOException {
        // login() method is called on the loginService instance with "user" as username and
        // "notsecret" as password.
        boolean success = loginService.login("user", "notsecret".toCharArray());

        // restTemplate.getForEntity() method retrieves a representation by doing a GET on the
        // specified URL.
        ResponseEntity<String> entity = restTemplate.getForEntity(
                "http://localhost:" + port + "/metrics", String.class);

        // objectMapper.readValue() method parse JSON content from given JSON content String.
        Map<String, Object> response = objectMapper.readValue(entity.getBody(),
                                                              new TypeReference<HashMap<String,
                                                                      Object>>() {});

        // assertThat() is a statement in JUnit. If the statement does not throw an exception,
        // then the test case is assumed to have succeeded.
        assertThat(success, is(false));
        assertThat(entity.getStatusCode(), is(HttpStatus.OK));
        assertThat(response, hasEntry("counter.login.failure", 1));
    }
}
