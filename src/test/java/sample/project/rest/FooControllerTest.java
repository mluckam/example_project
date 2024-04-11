package sample.project.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class FooControllerTest {

    @Autowired
    private MockMvc mockMvc;

    /**
     * Blank request parameter returns 400
     **/
    @Test
    void testGetFooIsBlank()
            throws Exception {
        mockMvc.perform(get("/foo"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    /**
     * Blank path variable throws {@link jakarta.validation.ConstraintViolationException}
     **/
    @Test
    void testGetTestIsBlank()
            throws Exception {
        mockMvc.perform(get("/foo/ /"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    /**
     * Blank path variable throws {@link jakarta.validation.ConstraintViolationException}
     **/
    @Test
    void testGetTestIsBlank_withParam()
            throws Exception {
        mockMvc.perform(get("/foo").param("test", " "))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}
