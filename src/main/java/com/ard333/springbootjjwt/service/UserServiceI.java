package com.ard333.springbootjjwt.service;

import com.ard333.springbootjjwt.entity.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
@Repository
public interface UserServiceI extends ReactiveCrudRepository<User, String> {
    Mono<Boolean> existsByUsername(String username);
    Mono<User> findByUsername(String username);
}
