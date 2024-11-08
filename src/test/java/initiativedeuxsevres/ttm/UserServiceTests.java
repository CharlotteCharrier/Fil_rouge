package initiativedeuxsevres.ttm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import initiativedeuxsevres.ttm.model.enums.Fields;
import initiativedeuxsevres.ttm.model.enums.Role;
import initiativedeuxsevres.ttm.model.enums.Support;
import initiativedeuxsevres.ttm.model.User;
import initiativedeuxsevres.ttm.repository.UserRepository;
import initiativedeuxsevres.ttm.service.Impl.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @InjectMocks
    UserServiceImpl userServiceImpl;

    @Mock
    private UserRepository userRepository;

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


    @Test
    public void getAllUsers() {
        when(userRepository.findAll()).thenReturn(List.of(user1, user2));
        assertEquals(2, userServiceImpl.getAllUsers().size());
    }

    @Test
    public void addParrainToAPorteurDeProjet() {
        User userOutput1 = User.builder()
                .id(1L)
                .username("Charlotte")
                .email("charlotte.test@test.com")
                .password(user1.getPassword())
                .role(Role.PARRAIN)
                .fields(List.of(Fields.INDUSTRIE, Fields.TRANSPORTS))
                .porteurs(List.of(user2))
                .build();

        User userOutput2 = User.builder()
                .id(2L)
                .username("Omer")
                .email("omer.test@test.com")
                .password(user2.getPassword())
                .role(Role.PORTEUR_PROJET)
                .fields(List.of(Fields.TRANSPORTS))
                .supports(List.of(Support.INFORMATIQUE))
                .parrain(user1)
                .build();

        when(userRepository.save(user1)).thenReturn(userOutput1);
        when(userRepository.save(user2)).thenReturn(userOutput2);

        userServiceImpl.addMatch(user1, user2);

        assertEquals(userOutput1.getUsername(), user2.getParrain().getUsername());
        assertEquals(userOutput2.getUsername(), user1.getPorteurs().get(0).getUsername());
    }
}
