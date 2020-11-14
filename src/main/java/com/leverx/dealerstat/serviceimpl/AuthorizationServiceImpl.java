package com.leverx.dealerstat.serviceimpl;

import com.leverx.dealerstat.dto.AuthenticationRequestDTO;
import com.leverx.dealerstat.entity.User;
import com.leverx.dealerstat.security.JwtTokenProvider;
import com.leverx.dealerstat.service.AuthorizationService;
import com.leverx.dealerstat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {

    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;
    private UserService userService;

    @Autowired
    public AuthorizationServiceImpl(AuthenticationManager authenticationManager,
                                    JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @Override
    public Map<String, String> login(AuthenticationRequestDTO authenticationRequestDTO) {
        try {
            String email = authenticationRequestDTO.getEmail();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email,
                    authenticationRequestDTO.getPassword()));
            User user = userService.getUserByEmail(email);

            if (user == null) {
                throw new UsernameNotFoundException("User with email: " + email + " not found");
            }

            String token = jwtTokenProvider.createToken(email, user.getRole().getValueOfRole());
            Map<String, String> response = new HashMap<>();
            response.put("email", email);
            response.put("token", token);

            return response;
        } catch (AuthenticationException e) {
            System.out.println(e);
            throw new BadCredentialsException("Invalid email or password", e);
        }
    }
}
