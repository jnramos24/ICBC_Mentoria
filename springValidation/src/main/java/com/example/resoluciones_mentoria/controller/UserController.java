package com.example.resoluciones_mentoria.controller;

import com.example.resoluciones_mentoria.dto.UserDTO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UserController {

    private final List<UserDTO> userDTOList = new ArrayList<>();

    @PostMapping
    public String userCreate (@Valid @RequestBody UserDTO user){
        userDTOList.add(user);

        return "Se creo el usuario " + user.getName();
    }

    @GetMapping
    public List<UserDTO> getUserDTOList() {
        return userDTOList;
    }

}



