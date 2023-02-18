package com.ard333.springbootjjwt.UseCase;

import com.ard333.springbootjjwt.entity.UserDTO;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
@FunctionalInterface
public interface SaveUser {
    Mono<String> apply(@Valid UserDTO userDTO);
}