package com.example.validation.controller;

import com.example.validation.dtos.UserDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private Long countId = 0L;
    private List<UserDto> users = new ArrayList<>();
    @GetMapping("")
    public List<UserDto> getUsers(){
        return users;
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@Valid @RequestBody UserDto userDto){
        userDto.setIdUser(countId++);
        users.add(userDto);
        return userDto;
    }
}
