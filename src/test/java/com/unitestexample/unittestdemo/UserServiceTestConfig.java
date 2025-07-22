package com.unitestexample.unittestdemo;

import com.unitestexample.unittestdemo.service.UserService;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
class UserServiceTestConfig {

    @Bean
    public UserService userService() {
        return Mockito.mock(UserService.class);
    }
}
