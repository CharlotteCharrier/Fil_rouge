package initiativedeuxsevres.ttm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.Authentication;

import initiativedeuxsevres.ttm.DTO.RegisterDto;
import initiativedeuxsevres.ttm.model.User;

public interface UserService {
    void saveUser(RegisterDto userMapping);

    Optional<User> from(Authentication authentication);

    Optional<User> findByUsername(String username);

    boolean hasCommonFields(User user1, User user2);

    List<User> getAllUsers();
}
