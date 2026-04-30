package askar.edufoodjava27.service.impl;

import askar.edufoodjava27.dto.UserDto;
import askar.edufoodjava27.dto.UserRegistrationDto;
import askar.edufoodjava27.exception.RoleNoteFoundException;
import askar.edufoodjava27.exception.UserAlreadyExistsException;
import askar.edufoodjava27.exception.UserNotFoundException;
import askar.edufoodjava27.model.User;
import askar.edufoodjava27.repository.RoleRepository;
import askar.edufoodjava27.model.Role;
import askar.edufoodjava27.repository.UserRepository;
import askar.edufoodjava27.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final PasswordEncoder encoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("loadUserByUsername {}", email);
        return userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public UserDto register(UserRegistrationDto registrationDto) {
        log.info("register {}", registrationDto);
        userRepository.findByEmail(registrationDto.getEmail()).ifPresent(u -> {
            throw new UserAlreadyExistsException("User with email " + registrationDto.getEmail() + " already exists");
        });

        Role userRole = roleRepository.findByName("ROLE_USER").orElseThrow(RoleNoteFoundException::new);


        User newUser = User.builder()
                .name(registrationDto.getName())
                .email(registrationDto.getEmail())
                .password(encoder.encode(registrationDto.getPassword()))
                .roles(Set.of(userRole))
                .enabled(true)
                .build();

        User savedUser = userRepository.save(newUser);
        return toDto(savedUser);
    }

    @Override
    public User findByEmail(String email) {
        log.info("findByEmail {}", email);
        return userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);
    }

    private UserDto toDto(User user) {
        log.info("toDto {}", user);
        if (user == null) return null;

        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }




}
