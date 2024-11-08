package initiativedeuxsevres.ttm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.Authentication;

import initiativedeuxsevres.ttm.DTO.UserDto;
import initiativedeuxsevres.ttm.model.User;

public interface UserService {
    void saveUser(UserDto userMapping);

    Optional<User> from(Authentication authentication);

    Optional<User> findByUsername(String username);

    boolean hasCommonFields(User user1, User user2);

    List<User> getAllUsers();

    void addMatch(User user1, User user2);
}
