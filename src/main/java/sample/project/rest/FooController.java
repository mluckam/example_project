package sample.project.rest;

import jakarta.validation.constraints.NotBlank;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class FooController {

    @GetMapping(value = "/foo", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getFoo(@NotBlank @RequestParam String test) {
        return "SUCCESS";
    }

    @GetMapping(value = "/foo/{test}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getTest(@NotBlank @PathVariable String test) {
        return "SUCCESS";
    }
}
