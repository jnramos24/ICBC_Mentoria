package validaciones.validaciones.service;

import org.springframework.stereotype.Service;
import validaciones.validaciones.dtos.UserDto;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private List<UserDto> users = new ArrayList<>();
    private Long countId = 1L;

    public List<UserDto> getAllUsers() {
        return users;
    }

    public UserDto createUser(UserDto userDto) {
        userDto.setIdUser(countId++);
        users.add(userDto);
        return userDto;
    }
}
