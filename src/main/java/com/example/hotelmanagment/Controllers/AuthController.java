package com.example.hotelmanagment.Controllers;

import com.example.hotelmanagment.DTO.*;
import com.example.hotelmanagment.Constants.Messages;
import com.example.hotelmanagment.Enums.UserRole;
import com.example.hotelmanagment.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<BasicResponseDTO<RegisterResponseDTO>> registerUser(@RequestBody RegisterRequestDTO registerRequestDTO) {
        BasicResponseDTO<RegisterResponseDTO> basicResponseDTO = new BasicResponseDTO<>();
        basicResponseDTO.setData(null);
        basicResponseDTO.setSuccess(false);
        if( !registerRequestDTO.getPassword().equals(registerRequestDTO.getConfirmPassword()) ){
            basicResponseDTO.setMessage(Messages.passwordAndConfirmPasswordNotMatched);
            return new ResponseEntity<>(basicResponseDTO, HttpStatus.BAD_REQUEST);
        }
        Optional<RegisterResponseDTO> rr = userService.addUser(registerRequestDTO);
        if(!rr.isPresent()) {
            basicResponseDTO.setMessage(Messages.userAlreadyExists);
            return new ResponseEntity<>(basicResponseDTO, HttpStatus.CONFLICT);
        }
        if (registerRequestDTO.getRole().equals(UserRole.ADMIN) && !registerRequestDTO.getMasterPassword().equals("India@123")){
            basicResponseDTO.setMessage("Wrong master password");
            return new ResponseEntity<>(basicResponseDTO, HttpStatus.CONFLICT);
        }
        basicResponseDTO.setData(rr.get());
        basicResponseDTO.setSuccess(true);
        return new ResponseEntity<>(basicResponseDTO, HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<BasicResponseDTO<LoginResponseDTO>> login(@RequestBody LoginRequestDTO loginRequestDTO){
        BasicResponseDTO<LoginResponseDTO> result = userService.login(
                loginRequestDTO.getEmail(),
                loginRequestDTO.getPassword()
                );
        return new ResponseEntity<>(result, result.isSuccess() ? HttpStatus.OK : HttpStatus.UNAUTHORIZED);
    }
}
