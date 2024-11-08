package initiativedeuxsevres.ttm.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import initiativedeuxsevres.ttm.DTO.UserDto;
import initiativedeuxsevres.ttm.model.User;
import initiativedeuxsevres.ttm.service.UserService;
import jakarta.validation.Valid;

@Controller
public class AuthController {

private final UserService userService;

@Autowired
public AuthController(UserService userService) {
    this.userService = userService;
}

@GetMapping("/home")
    public String home(Model model, Authentication auth) {
    Optional<User>user = userService.from(auth);
    if(user.isPresent()) {
        model.addAttribute("currentUser", user.get());
    }
    return "home";
}

@PostMapping("/register/save")
    public String registerUser(@Valid @ModelAttribute UserDto userMapping) {
        userService.saveUser(userMapping);
        return "redirect:/home";
}

@PostMapping("/match")
    public String addParrain(@Valid @ModelAttribute User parrain, User porteur) {
    userService.addMatch(parrain, porteur);

    return "redirect:/home";
}
}