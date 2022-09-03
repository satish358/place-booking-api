package com.example.hotelmanagment.Services;

import com.example.hotelmanagment.DTO.BasicResponseDTO;
import com.example.hotelmanagment.DTO.LoginResponseDTO;
import com.example.hotelmanagment.DTO.RegisterRequestDTO;
import com.example.hotelmanagment.DTO.RegisterResponseDTO;
import com.example.hotelmanagment.Models.User;
import com.example.hotelmanagment.Repositories.UserDAO;
import com.example.hotelmanagment.Utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService  {
    @Autowired
    UserDAO userDAO;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JWTUtil jwtUtil;


    public Optional<RegisterResponseDTO> addUser(RegisterRequestDTO r) {
        if(userDAO.existsByEmail(r.getEmail())){
            return Optional.empty();
        }
        User user = new User();
        user.setName(r.getName());
        user.setEmail(r.getEmail());
        user.setRole(r.getRole());
        user.setPassword(passwordEncoder.encode(r.getPassword()));
        userDAO.save(user);
        final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
        return Optional.of(new RegisterResponseDTO(jwtUtil.generateToken(userDetails), user.getEmail(), user.getName()));
    }


    public BasicResponseDTO<LoginResponseDTO> login(String email, String password) {
        BasicResponseDTO<LoginResponseDTO> basicResponseDTO = new BasicResponseDTO<>();
        Optional<User> _user = userDAO.findUserByEmail(email);
        if(_user.isEmpty()){
            basicResponseDTO.setMessage("User not found");
            return basicResponseDTO;
        }

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            email,
                            password
                    )
            );
        } catch (BadCredentialsException e) {
            basicResponseDTO.setMessage("Credentials not matched");
            return basicResponseDTO;
        }
        User user = _user.get();
        final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
        loginResponseDTO.setToken(jwtUtil.generateToken(userDetails));
        loginResponseDTO.setContact(user.getContact());
        loginResponseDTO.setName(user.getName());
        loginResponseDTO.setEmail(user.getEmail());
        loginResponseDTO.setUser(user);
        basicResponseDTO.setData(loginResponseDTO);
        basicResponseDTO.setSuccess(true);

        return basicResponseDTO;
    }





}
