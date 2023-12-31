package finances.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SignUpRequest(
        @NotBlank(message = "Email cannot be blank")
        @Email(message = "Invalid email format")
        String email,
        @NotBlank(message =  "Password cannot be blank")
        @Size(min = 6, max = 12, message = "Password must be between 6 and 12 characters")
        String password
) {}
