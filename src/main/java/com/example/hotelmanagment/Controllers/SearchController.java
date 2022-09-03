package com.example.hotelmanagment.Controllers;

import com.example.hotelmanagment.DTO.SearchRequestDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("api/search")
public class SearchController {
    @PostMapping("")
    public ResponseEntity<?> searchProperties(@RequestBody SearchRequestDTO searchRequestDTO){
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
