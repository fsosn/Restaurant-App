package com.myrestaurant.restaurantapp.security.auth.service;

import com.myrestaurant.restaurantapp.security.auth.request.RegisterRequest;
import com.myrestaurant.restaurantapp.security.auth.request.AuthenticationRequest;
import com.myrestaurant.restaurantapp.security.auth.request.ChangePasswordRequest;
import com.myrestaurant.restaurantapp.security.auth.response.RegisterResponse;
import com.myrestaurant.restaurantapp.security.auth.response.AuthenticationResponse;
import com.myrestaurant.restaurantapp.security.auth.response.ChangePasswordResponse;
import com.myrestaurant.restaurantapp.security.auth.response.RoleResponse;
import com.myrestaurant.restaurantapp.security.auth.validation.AuthValidationUtil;
import com.myrestaurant.restaurantapp.security.jwt.JwtService;
import com.myrestaurant.restaurantapp.user.exception.UserAlreadyRegisteredException;
import com.myrestaurant.restaurantapp.user.model.Role;
import com.myrestaurant.restaurantapp.user.model.User;
import com.myrestaurant.restaurantapp.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final AuthValidationUtil validation;

    public RegisterResponse register(RegisterRequest request) {
        try {
            if (userRepository.findByEmail(request.getEmail()).isPresent()) {
                throw new UserAlreadyRegisteredException(request.getEmail());
            }

            validation.validateRegistrationData(
                    request.getFirstName(),
                    request.getLastName(),
                    request.getEmail(),
                    request.getPassword()
            );

            var user = User.builder()
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(Role.USER)
                    .build();

            userRepository.save(user);

            return RegisterResponse.builder()
                    .message("Successfully registered account with email: " + request.getEmail())
                    .success(true)
                    .build();
        } catch (UserAlreadyRegisteredException e) {
            return RegisterResponse.builder()
                    .message(e.getMessage())
                    .success(false)
                    .build();
        } catch (RuntimeException e) {
            return RegisterResponse.builder()
                    .message("Error during registration: " + e.getMessage())
                    .success(false)
                    .build();
        }
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        try {
            validation.validateLoginData(
                    request.getEmail(),
                    request.getPassword()
            );

            var user = userRepository.findByEmail(request.getEmail()).orElseThrow();

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword())
            );

            userRepository.save(user);
            var token = jwtService.generateToken(user);

            return AuthenticationResponse.builder()
                    .token(token)
                    .message("Successfully authenticated user.")
                    .success(true)
                    .build();
        } catch (BadCredentialsException e) {
            return AuthenticationResponse.builder()
                    .token(null)
                    .message("Incorrect credentials. Please try again.")
                    .success(false)
                    .build();
        } catch (RuntimeException e) {
            return AuthenticationResponse.builder()
                    .message("Error during login: " + e.getMessage())
                    .success(false)
                    .build();
        }
    }

    public ChangePasswordResponse changePassword(ChangePasswordRequest request) {
        try {
            validation.validateChangePasswordData(
                    request.getOldPassword(),
                    request.getNewPassword()
            );

            String currentUserEmail = getCurrentUserEmail();
            var user = userRepository.findByEmail(currentUserEmail).orElseThrow();

            if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
                return ChangePasswordResponse.builder()
                        .message("Old password is incorrect.")
                        .success(false)
                        .build();
            }

            user.setPassword(passwordEncoder.encode(request.getNewPassword()));
            userRepository.save(user);

            return ChangePasswordResponse.builder()
                    .message("Password changed successfully.")
                    .success(true)
                    .build();
        } catch (RuntimeException e) {
            return ChangePasswordResponse.builder()
                    .message("Error during changing password: " + e.getMessage())
                    .success(false)
                    .build();
        } catch (Exception e) {
            return ChangePasswordResponse.builder()
                    .message("Failed to change password. Please try again.")
                    .success(false)
                    .build();
        }
    }

    public RoleResponse getRole(String jwt){
        String role = jwtService.extractUserRole(jwt.substring(7));

        return RoleResponse.builder()
                .role(role)
                .build();
    }

    private String getCurrentUserEmail() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        return userDetails.getUsername();
    }
}
