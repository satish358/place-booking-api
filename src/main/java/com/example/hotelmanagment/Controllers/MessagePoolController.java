package com.example.hotelmanagment.Controllers;

import com.example.hotelmanagment.DTO.BasicResponseDTO;
import com.example.hotelmanagment.DTO.SendMessageRequestDTO;
import com.example.hotelmanagment.Models.MessagePool;
import com.example.hotelmanagment.Services.MessagePoolService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/messaging")
public class MessagePoolController {

    @Autowired
    MessagePoolService messagePoolService;

    @GetMapping("/{userId}")
    @Operation(summary = "It will shows all messages", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<BasicResponseDTO<List<MessagePool>>> getMessages(@PathVariable("userId") Long userId) {
        return new ResponseEntity<>(
                new BasicResponseDTO<>(
                        true,
                        "Data is available",
                        messagePoolService.getAllMessagesByUserId(userId)
                ),
                HttpStatus.OK
        );
    }
    @GetMapping("/all")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<BasicResponseDTO<List<MessagePool>>> getMessages() {
        return new ResponseEntity<>(
                new BasicResponseDTO<>(
                        true,
                        "Data is available",
                        messagePoolService.getAll()
                ),
                HttpStatus.OK
        );
    }
    @PostMapping("/send")
    @Operation(summary = "It will send new message", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<BasicResponseDTO<MessagePool>> sendMessage(@RequestBody SendMessageRequestDTO sendMessageRequestDTO){
        MessagePool messagePool = messagePoolService.createMessage(sendMessageRequestDTO.getMessage(), sendMessageRequestDTO.getSender(), sendMessageRequestDTO.getReceiver());

        return new ResponseEntity(
                new BasicResponseDTO<MessagePool>(true, "Message sent", messagePool),
                HttpStatus.CREATED);
    }
}
