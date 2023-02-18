package com.ard333.springbootjjwt.UseCase;

import com.ard333.springbootjjwt.entity.User;
import com.ard333.springbootjjwt.entity.UserDTO;
import com.ard333.springbootjjwt.security.PBKDF2Encoder;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class MapperUtils {
    private PBKDF2Encoder pbkdf2Encoder;
    public Function<UserDTO, User> mapperToUser(String id) {
        return updateUser -> {
            var userDTO = new User();
            userDTO.setUsername(updateUser.getUsername());
            userDTO.setRoles(updateUser.getRoles());
            userDTO.setId(updateUser.getId());
            userDTO.setPassword(updateUser.getPassword());
            return userDTO;
        };
    }

    public Function<User, UserDTO> mapEntityToUser() {
        return entity -> new UserDTO(
                entity.getId(),
                entity.getUsername(),
                entity.getPassword(),
                entity.getRoles()
        );
    }
}
