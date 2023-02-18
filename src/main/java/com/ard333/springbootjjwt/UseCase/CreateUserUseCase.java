/*package com.ard333.springbootjjwt.UseCase;

import com.ard333.springbootjjwt.entity.User;
import com.ard333.springbootjjwt.entity.UserDTO;
import com.ard333.springbootjjwt.service.UserServiceI;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class CreateUserUseCase implements SaveUser {
    UserServiceI userServiceI;
    MapperUtils mapperUtils;

    public CreateUserUseCase(UserServiceI userServiceI, MapperUtils mapperUtils) {
        this.userServiceI = userServiceI;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public Mono<String> apply(UserDTO userDTO) {
        return userServiceI.save(mapperUtils.mapperToUser(null).apply(userDTO))
                .map(User::getId);
    }


}*/
