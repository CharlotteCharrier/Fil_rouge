package initiativedeuxsevres.ttm.DTO;

import initiativedeuxsevres.ttm.model.Role;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDto {

    @NotEmpty(message = "Name must not be empty")
    private String username;

    @NotEmpty(message = "Email must not be empty")
    private String email;

    @NotEmpty(message = "Password must not be empty")
    private String password;

    @NotEmpty(message = "Password confirm must not be empty")
    private String confirmPassword;

    @NotEmpty(message = "Role must not be empty")
    private Role role;
}
