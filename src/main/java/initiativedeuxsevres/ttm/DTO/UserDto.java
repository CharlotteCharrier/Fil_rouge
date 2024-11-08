package initiativedeuxsevres.ttm.DTO;

import java.util.List;

import initiativedeuxsevres.ttm.model.enums.Fields;
import initiativedeuxsevres.ttm.model.enums.Role;
import initiativedeuxsevres.ttm.model.enums.Support;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @NotEmpty(message = "Name must not be empty")
    private String username;

    @NotEmpty(message = "Email must not be empty")
    private String email;

    @NotEmpty(message = "Password must not be empty")
    private String password;

    @NotEmpty(message = "Role must not be empty")
    private Role role;

    @NotEmpty(message = "Fields must not be empty")
    private List<Fields> fields;

    @NotEmpty(message = "Support mus not be empty")
    private List<Support> supports;
}
