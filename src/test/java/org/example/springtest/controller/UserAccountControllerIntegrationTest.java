package org.example.springtest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.springtest.dto.request.UserDetailsRequest;
import org.example.springtest.dto.response.UserDetailsResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

//@TestPropertySource(locations = "/application-test.properties", properties = "server.port=8082")
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
//properties = "server.port=8081")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserAccountControllerIntegrationTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int port;

    @DisplayName("User can be created.")
    @Test
    void givenUserInfo_whenCreatesUser_thenSavesUserAndReturnsUserInfo() throws JsonProcessingException {
        // Given
        UserDetailsRequest request = new UserDetailsRequest();
        request.setFirstName("First name");
        request.setLastName("Last name");
        request.setEmail("test@test.com");
        request.setPassword("asdfasdfasdf");
        request.setRepeatedPassword("asdfasdfasdf");

        ObjectMapper mapper = new ObjectMapper();
        String requestJson = mapper.writeValueAsString(request);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> requestEntity = new HttpEntity<>(requestJson, headers);

        String url = "http://localhost:" + port + "/users";

        // When
        UserDetailsResponse body = testRestTemplate.exchange(url, HttpMethod.POST, requestEntity, UserDetailsResponse.class).getBody();

        // Then
        assertThat(body.getFirstName()).isEqualTo(request.getFirstName());
    }

    @DisplayName("Requires JWT")
    @Test
    void givenGetUsers_whenMissingJWT_thenReturns403() {
        // Given
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> requestEntity = new HttpEntity(headers);

        // When
        ResponseEntity<List<UserDetailsResponse>> response = testRestTemplate.exchange("/users", HttpMethod.GET, requestEntity, new ParameterizedTypeReference<List<UserDetailsResponse>>() {
        });

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }

}
