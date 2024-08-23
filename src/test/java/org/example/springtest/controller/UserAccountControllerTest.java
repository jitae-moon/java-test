package org.example.springtest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.springtest.dto.UserAccountDto;
import org.example.springtest.dto.request.UserDetailsRequest;
import org.example.springtest.dto.response.UserDetailsResponse;
import org.example.springtest.service.UserAccountService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@DisplayName("Web layer test splice")
//@AutoConfigureMockMvc(addFilters = false) // Exclude security filters
//@MockBean(classes = {UserAccountServiceImpl.class}) // Need to @Autowired
@WebMvcTest(controllers = UserAccountController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
class UserAccountControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean // Make and put into Spring context @Mock does not put into ac
    UserAccountService userAccountService;

    @DisplayName("Test create user")
    @Test
    void givenUserInfo_whenSavingUser_thenReturnsCreatedUser() throws Exception {
        // Given
        UserDetailsRequest request = createUserDetailRequest();

        UserAccountDto dto = new ModelMapper().map(request, UserAccountDto.class);
        dto.setUserId(UUID.randomUUID().toString());

        when(userAccountService.createUser(any(UserAccountDto.class))).thenReturn(dto);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request));

        // When
        MvcResult mvcResult = mvc.perform(requestBuilder).andReturn();
        String responseAsString = mvcResult.getResponse().getContentAsString();
        UserDetailsResponse userDetailsResponse = new ObjectMapper().readValue(responseAsString, UserDetailsResponse.class);

        // Then
        Assertions.assertEquals(userDetailsResponse.getFirstName(), request.getFirstName());
    }

    @Disabled("TODO")
    @DisplayName("Check first name is not empty")
    @Test
    void givenUserInfo_whenFirstNameNotProvided_thenReturns404() throws Exception {
        // Given
        UserDetailsRequest request = createUserDetailRequest();
        request.setFirstName("");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request));

        // When
        MvcResult mvcResult = mvc.perform(requestBuilder).andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        UserDetailsResponse userDetailsResponse = new ObjectMapper().readValue(response, UserDetailsResponse.class);

        // Then
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, mvcResult.getResponse().getStatus());
    }

    private static UserDetailsRequest createUserDetailRequest() {
        UserDetailsRequest request = new UserDetailsRequest();
        request.setFirstName("admin");
        request.setLastName("admin");
        request.setEmail("admin@test.com");
        request.setPassword("admin1234");
        request.setRepeatedPassword("admin1234");
        return request;
    }

}
