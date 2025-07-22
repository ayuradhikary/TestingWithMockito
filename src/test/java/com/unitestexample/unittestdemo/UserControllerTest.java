package com.unitestexample.unittestdemo;

import com.unitestexample.unittestdemo.controller.UserController;
import com.unitestexample.unittestdemo.entity.User;
import com.unitestexample.unittestdemo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
/*
@WebMvcTest(UserController.class)
Loads only the Spring Web layer (i.e., @Controller, @RestController, @ControllerAdvice).
Does not load the service or repository layers.
Use @MockBean to provide fake dependencies for the controller.
Fast and focused on testing only the HTTP API behavior.
*/
@Import(UserServiceTestConfig.class) // Custom config to inject mocks
public class UserControllerTest {

    @Autowired
    /*
    Tells Spring to inject an instance of MockMvc, which is a Spring test utility to:
    Simulate real HTTP requests (GET, POST, etc.)
    Verify HTTP status codes and response bodies
    You use this to test the controller without starting a real server.
    MockMvc provides support for testing Spring MVC applications, It performs full
    Spring MVC request handling but via mock request and response objects instead of a running server.
*/
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @Test
    void testCreateUser() throws Exception {
        User user = new User(null, "Nikesh");
        User saved = new User(1L, "Nikesh");

        when(userService.createUser(any())).thenReturn(saved);

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Nikesh\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Nikesh"))
                .andExpect(jsonPath("$.id").value(1));
    }

/*
    mockMvc.perform(...) :-
    mockMvc is a Spring-provided test utility that simulates HTTP requests without starting an actual server.
            .perform(...) means: "send a request" — it accepts a MockHttpServletRequestBuilder, which builds an HTTP request.
    post("/users") :-
    This simulates a POST request to the URL /users.
    It's the same as calling your REST API:
    curl -X POST http://localhost:8080/users

    .contentType(MediaType.APPLICATION_JSON) :-
     Tells Spring that you are sending JSON data in the request body.

    .content("{\"name\":\"Nikesh\"}") :-
    This is the actual body of the HTTP POST request.
    It's a JSON string that sends { "name": "Nikesh" }
    \" is used to   escape the double quotes inside the Java string.

    Equivalent in a REST client:

    POST /users
    Content-Type: application/json
    {
        "name": "Nikesh"
    }

    .andExpect(status().isOk()) :-

          This asserts that the response status code is 200 OK.
         If your controller returns anything else (e.g. 400, 500), the test will fail.
        It's checking if the controller behaved correctly.

*/

/*    We don’t use @InjectMocks in this test because we're using @WebMvcTest, which loads only the web layer of the
    Spring context and manages dependency injection automatically. Instead of manually injecting mocks like @InjectMocks does in
    plain Mockito tests, we rely on Spring to inject mocked dependencies (typically using @MockBean or @Import). @InjectMocks is used
    when you're constructing and injecting dependencies manually, usually in unit tests outside the Spring context.*/


}
