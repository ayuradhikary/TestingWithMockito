package com.unitestexample.unittestdemo;

import com.unitestexample.unittestdemo.entity.User;
import com.unitestexample.unittestdemo.repository.UserRepository;
import com.unitestexample.unittestdemo.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
/*
@ExtendWith annotation Enables Mockito annotations (@Mock, @InjectMocks) in this test class. Required for JUnit 5 (@Test) to support Mockito.
This tells JUnit to enable Mockito support.
*/
public class UserServiceTest {

    @Mock
    /* @Mock annotation
    Tells Mockito to create a fake instance of UserRepository.
    This mock won’t hit the real database — it just returns what you tell it to with when(...).
    */
    private UserRepository userRepository;

    @InjectMocks
    /* @InjectMocks annotation
    Automatically injects the mocked userRepository into UserService.
    This allows you to test UserService in isolation.
    */
    private UserService userService;

    @Test
    /*
    @Test annotation is
    Standard JUnit test method. This is where the actual unit test logic lives.
    */
    void testCreateUser() {
        User user = new User(null, "Nikesh");
        User saved = new User(1L, "Nikesh"); //mocked data
        when(userRepository.save(user)).thenReturn(saved); // "Hey Mockito, if someone calls userRepository.save(user), then return the saved object."
        User result = userService.createUser(user); // method to be tested
        assertEquals("Nikesh", result.getName()); // if the name matches with the name we passed in the mocked data test passed
        assertEquals(1L, result.getId()); // if the id matches to the id we passed in the mocked data then the test passed.
    }

/*
            |        `@Mock`                   |           `@MockBean`                     |
            | -------------------------------- | ----------------------------------------- |
            | Comes from **Mockito**           | Comes from **Spring Boot Test**           |
            | Used for **pure unit tests**     | Used for **Spring integration tests**     |
            | Does **not** load Spring context | **Loads** Spring ApplicationContext       |
            | Fast and lightweight             | Slower, as Spring context must be created |
*/

// notes :-
//we choose @Mock here because we are doing a true unit tests no spring context, no beans and no HTTP just checking the logic in isolation
// UserRepository is mocked mannually not autowired from Spring.
//Use @MockBean when writing @SpringBootTest or @WebMvcTest, where you test the actual Spring beans but want to override a specific one

}
