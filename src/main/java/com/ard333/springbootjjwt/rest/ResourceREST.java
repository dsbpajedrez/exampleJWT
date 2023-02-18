package com.ard333.springbootjjwt.rest;


import com.ard333.springbootjjwt.UseCase.MapperUtils;
import com.ard333.springbootjjwt.entity.Message;
import com.ard333.springbootjjwt.entity.User;
import com.ard333.springbootjjwt.entity.UserDTO;
import com.ard333.springbootjjwt.security.PBKDF2Encoder;
import com.ard333.springbootjjwt.service.UserServiceI;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import reactor.core.publisher.Mono;

import java.util.function.Function;

/**
 *
 * @author ard333
 */
@AllArgsConstructor
@RestController
public class ResourceREST {

	private UserServiceI userServiceI;
	private MapperUtils mapperUtils;
	private PBKDF2Encoder pbkdf2Encoder;
	@GetMapping("/resource/user")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> user() {
		return ResponseEntity.ok(new Message("Content for user"));
	}
	
	@GetMapping("/resource/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> admin() {
		return ResponseEntity.ok(new Message("Content for admin"));
	}
	
	@GetMapping("/resource/user-or-admin")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<?> userOrAdmin() {
		return ResponseEntity.ok(new Message("Content for user or admin"));
	}


	//@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@PostMapping("/create")
	public Mono<String> create (@RequestBody UserDTO userDTO) {
		System.out.println(userServiceI.existsByUsername(userDTO.getUsername()));
		return userServiceI.existsByUsername(userDTO.getUsername())
				.flatMap(username -> {
					if(username){

						return Mono.error(new Exception("Ya existe este usuario"));
					}else{
						userDTO.setPassword(pbkdf2Encoder.encode(userDTO.getPassword()));
						return userServiceI.save(mapperUtils
								.mapperToUser(null).apply(userDTO))
								.map(User::getId);
					}
				});
	}
	/*public RouterFunction<ServerResponse> create(CreateUserUseCase createUserUseCase) {
		Function<UserDTO, Mono<ServerResponse>> excecutor = userDTO -> createUserUseCase.apply(userDTO)
				.flatMap(result -> ServerResponse.ok()
						.contentType(MediaType.TEXT_PLAIN)
						.bodyValue(result))
				.and(accept(MediaType.APPLICATION_JSON)),
		request -> request.bodyToMono(UserDTO.class).flatMap(excecutor)
		return route(
				POST("/cre").and(accept(MediaType.APPLICATION_JSON)),
				request -> request.bodyToMono(UserDTO.class).flatMap(excecutor)
		)
	};*/
}
