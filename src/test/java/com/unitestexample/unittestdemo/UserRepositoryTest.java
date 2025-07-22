package com.unitestexample.unittestdemo;

import com.unitestexample.unittestdemo.entity.User;
import com.unitestexample.unittestdemo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/*
@DataJpaTest
Purpose: This tells Spring Boot to test only JPA components (i.e., @Entity, @Repository).
It sets up an in-memory database (like H2 by default).
Loads only:
JPA-related beans
The actual UserRepository
The EntityManager, etc.
Does NOT load services, controllers, or full application context — it's isolated for testing the database layer.*/
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testSaveAndFindUser() {
        User user = new User(null, "Nikesh");
        userRepository.save(user);

        Optional<User> found = userRepository.findById(user.getId());

        assertTrue(found.isPresent());
        assertEquals("Nikesh", found.get().getName());
    }

/*
            | Type             | Real Repository (`@DataJpaTest`)              | Mock Repository (`@Mock`)          |
            | ---------------- | --------------------------------------------- | ---------------------------------- |
            | Purpose          | Test actual DB operations (save, query, etc.) | Test interaction, not DB behavior  |
            | Uses             | Real database (e.g., in-memory H2)            | Fake behavior (no DB)              |
            | Save/query works | Yes, real persistence                         | No, unless you define behavior     |
            | Useful for       | Testing JPA mapping, queries, integration     | Testing service logic in isolation |
*/


}

