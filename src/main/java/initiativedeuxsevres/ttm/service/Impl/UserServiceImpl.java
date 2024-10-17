package initiativedeuxsevres.ttm.service.Impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import initiativedeuxsevres.ttm.DTO.RegisterDto;
import initiativedeuxsevres.ttm.model.User;
import initiativedeuxsevres.ttm.repository.UserRepository;
import initiativedeuxsevres.ttm.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(RegisterDto user) {
        User userEntity = User.builder()
                .username(user.getUsername())
                .password(passwordEncoder.encode(user.getPassword()))
                .email(user.getEmail())
                .build();
        userRepository.save(userEntity);
    }

    @Override
    public Optional<User> from(Authentication authentication) {

        if(authentication == null) {
            return Optional.empty();
        }

        Object principal = authentication.getPrincipal();
        if(principal instanceof UserDetails) {
            return Optional.empty();
        }
        UserDetails userDetails = (UserDetails)principal;
        return userRepository.findByUsername(userDetails.getUsername());
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
