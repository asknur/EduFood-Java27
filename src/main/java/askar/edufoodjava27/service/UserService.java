package askar.edufoodjava27.service;

import askar.edufoodjava27.dto.UserDto;
import askar.edufoodjava27.dto.UserRegistrationDto;
import askar.edufoodjava27.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService {
    UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;

    UserDto register(UserRegistrationDto registrationDto);

    User findByEmail(String email);
}
