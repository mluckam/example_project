package example.project.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import example.project.config.ObjectMapperConfiguration;
import jakarta.validation.ConstraintViolationException;
import java.io.IOException;
import java.io.InputStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTest {

    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        objectMapper = new ObjectMapperConfiguration().getObjectMapper();
    }

    @Test
    void testDeserialize() throws IOException {
        String expected = "emailAddress: must not be blank";

        String actual =  Assertions.assertThrows(ConstraintViolationException.class, () -> {
            try (InputStream jsonInputStream = Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream("user.json")) {
                objectMapper.readValue(jsonInputStream, User.class);
            }
        }).getMessage();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testSerialize() {
        String expected = "emailAddress: must not be blank";

        User user = new User("testUserName", null);
        String actual =  Assertions.assertThrows(ConstraintViolationException.class, () -> {
            objectMapper.writeValueAsString(user);
        }).getMessage();

        Assertions.assertEquals(expected, actual);
    }
}
