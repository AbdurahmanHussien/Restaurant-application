package com.spring.restaurant.service.auth;

import com.spring.restaurant.config.jwt.JwtUtils;
import com.spring.restaurant.entity.auth.Role;
import com.spring.restaurant.entity.auth.User;
import com.spring.restaurant.entity.auth.UserDetails;
import com.spring.restaurant.exceptions.DuplicateFieldException;
import com.spring.restaurant.exceptions.ResourceNotFoundException;
import com.spring.restaurant.repository.auth.RoleRepository;
import com.spring.restaurant.repository.auth.UserRepository;
import com.spring.restaurant.request.LoginRequest;
import com.spring.restaurant.request.RegisterRequest;
import com.spring.restaurant.response.AuthResponse;
import com.spring.restaurant.utils.RoleType;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
@AllArgsConstructor
public class AuthenticationService implements IAuthenticationService {

        private final UserRepository userRepository;
        private final RoleRepository roleRepository;
        private final JwtUtils jwtUtils;
        private final PasswordEncoder passwordEncoder;
        private final AuthenticationManager authenticationManager;



    @Override
    @Cacheable(value = "user" , key = "#id")
    public User getUserById(Long id) {
       return userRepository.findById(id)
               .orElseThrow(()-> new ResourceNotFoundException("user.notfound"));
    }

    @Override
    @CacheEvict(value = "user", allEntries = true, key = "#email")
    public void resetPassword(String email) {
        User user = userRepository.findByUserDetailsEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("user.not.found"));
        user.setPassword(passwordEncoder.encode("Hello@1234"));
        userRepository.save(user);
    }

    @Override
    public AuthResponse createUser(RegisterRequest request) {

        UserDetails userDetails = UserDetails.builder()
                .name(request.name())
                .email(request.email())
                .phoneNum(request.phoneNum())
                .address(request.address())
                .age(request.age())
                .build();

        Optional<User> getUser= userRepository.findByUserDetailsEmail(request.email());
        Optional<User> getUser1= userRepository.findByUsername(request.username());
        if (getUser.isPresent()) {
            throw new DuplicateFieldException("email.exists");
        }
        if (getUser1.isPresent()) {
            throw new DuplicateFieldException("username.exists");
        }

        Role userRole = roleRepository.findByRoleType(RoleType.USER)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found"));

        User user = User.builder()
                .username(request.username())
                .password(passwordEncoder.encode(request.password() != null && !request.password().isEmpty() ? request.password() : "hello@1234"))
                .userDetails(userDetails)
                .roles(Set.of((userRole)))
                .build();

        userDetails.setUser(user);

      User savedUser =  userRepository.save(user);

        if (savedUser.getId() == null) {
            throw new RuntimeException("User not saved");
        }

        List<String> roles = user.getRoles().stream().map(Role::getRoleType).map(RoleType::name).toList();

        String token = jwtUtils.generateToken(user);


        return AuthResponse.builder()
                .userId(savedUser.getId())
                .token(token)
                .UserRole(roles)
                .build();
    }

    @Override
    public AuthResponse loginUser(LoginRequest request) {
        User user = userRepository.findByUserDetailsEmail(request.email())
                .orElseThrow(() -> new AuthenticationServiceException("email.not.found"));
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.email(),
                            request.password()
                    )
            );
        } catch (BadCredentialsException ex) {
            throw new BadCredentialsException("invalid.password");
        }
            List<String> roles = user.getRoles().stream().map(Role::getRoleType).map(RoleType::name).toList();
            String token = jwtUtils.generateToken(user);
            return AuthResponse.builder()
                    .userId(user.getId())
                    .token(token)
                    .UserRole(roles)
                    .build();
        }

        @CacheEvict(value = "user", allEntries = true)
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("user.not.found"));
        userRepository.delete(user);
    }

}
