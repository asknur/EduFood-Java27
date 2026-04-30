package askar.edufoodjava27.controller;

import askar.edufoodjava27.dto.UserDto;
import askar.edufoodjava27.dto.UserRegistrationDto;
import askar.edufoodjava27.exception.UserAlreadyExistsException;
import askar.edufoodjava27.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @GetMapping("register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userDto", new UserRegistrationDto());
        return "auth/register";
    }

    @PostMapping("register")
    public String registerUser(@Valid @ModelAttribute("userDto") UserRegistrationDto userDto,
                               BindingResult bindingResult,
                               Model model,
                               RedirectAttributes ra) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("userDto", userDto);
            return "auth/register";
        }
        try {
            userService.register(userDto);
            ra.addFlashAttribute("success", "Регистрация выполнена. Войдите в систему.");
            return "redirect:/login";
        } catch (UserAlreadyExistsException ex) {
            model.addAttribute("registrationError", ex.getMessage());
            model.addAttribute("userDto", userDto);
            return "auth/register";
        }
    }

    @GetMapping("login")
    public String showLoginForm() {
        return "auth/login";
    }



}
