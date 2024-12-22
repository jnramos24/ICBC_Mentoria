package com.example.resoluciones_mentoria.repository;

import com.example.resoluciones_mentoria.dto.UserDTO;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private final List<UserDTO> userDTOList = new ArrayList<>();

    public void saveUser(UserDTO user) {
        userDTOList.add(user);
    }

    public List<UserDTO> findAllUser() {
        return userDTOList;
    }

}
