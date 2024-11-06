package initiativedeuxsevres.ttm;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import initiativedeuxsevres.ttm.model.Fields;
import initiativedeuxsevres.ttm.model.Role;
import initiativedeuxsevres.ttm.model.Support;
import initiativedeuxsevres.ttm.model.User;
import initiativedeuxsevres.ttm.repository.UserRepository;
import initiativedeuxsevres.ttm.service.Impl.UserServiceImpl;

public class userServiceTests {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserServiceImpl userServiceImpl;

    @Test
    public void compareFields() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        User user1 = User.builder()
                .id(1L)
                .username("Charlotte")
                .email("charlotte.test@test.com")
                .password(passwordEncoder.encode("MotDePasse"))
                .role(Role.PARRAIN)
                .fields(List.of(Fields.INDUSTRIE, Fields.TRANSPORTS))
                .build();

        User user2 = User.builder()
                .id(2L)
                .username("Omer")
                .email("omer.test@test.com")
                .password(passwordEncoder.encode("MotDePasse2"))
                .role(Role.PORTEUR_PROJET)
                .fields(List.of(Fields.TRANSPORTS))
                .supports(List.of(Support.INFORMATIQUE))
                .build();

        assertEquals(user1.getFields().get(1), userServiceImpl.hasCommonFields(user1, user2));
    }
}
