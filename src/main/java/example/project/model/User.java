package example.project.model;

import jakarta.validation.constraints.NotBlank;

public record User(@NotBlank String username, @NotBlank String emailAddress) {
}
