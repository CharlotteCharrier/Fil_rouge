package initiativedeuxsevres.ttm;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import initiativedeuxsevres.ttm.model.Fields;
import initiativedeuxsevres.ttm.model.Role;
import initiativedeuxsevres.ttm.model.Support;
import initiativedeuxsevres.ttm.model.User;
import initiativedeuxsevres.ttm.service.Impl.UserServiceImpl;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    UserServiceImpl userServiceImpl;

    private User user1;
    private User user2;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @BeforeEach
    public void setup() {
         user1 = User.builder()
                .id(1L)
                .username("Charlotte")
                .email("charlotte.test@test.com")
                .password(passwordEncoder.encode("MotDePasse"))
                .role(Role.PARRAIN)
                .fields(List.of(Fields.INDUSTRIE, Fields.TRANSPORTS))
                .build();

         user2 = User.builder()
                .id(2L)
                .username("Omer")
                .email("omer.test@test.com")
                .password(passwordEncoder.encode("MotDePasse2"))
                .role(Role.PORTEUR_PROJET)
                .fields(List.of(Fields.TRANSPORTS))
                .supports(List.of(Support.INFORMATIQUE))
                .build();

    }

    @Test
    public void compareFields() {
        assertTrue(userServiceImpl.hasCommonFields(user1, user2));
    }
}
