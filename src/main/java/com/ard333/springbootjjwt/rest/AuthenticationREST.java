package com.ard333.springbootjjwt.rest;

import com.ard333.springbootjjwt.entity.User;
import com.ard333.springbootjjwt.entity.UserDTO;
import com.ard333.springbootjjwt.security.JWTUtil;
import com.ard333.springbootjjwt.security.PBKDF2Encoder;
import com.ard333.springbootjjwt.security.model.AuthRequest;
import com.ard333.springbootjjwt.security.model.AuthResponse;
import com.ard333.springbootjjwt.service.UserService;

import com.ard333.springbootjjwt.service.UserServiceI;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 *
 * @author ard333
 */
@AllArgsConstructor
@RestController
public class AuthenticationREST {

	@Autowired
	private JWTUtil jwtUtil;

	@Autowired
	private PBKDF2Encoder passwordEncoder;

	@Autowired
	private UserService userService;
	@Autowired
	private UserServiceI userServiceI;
	@PostMapping("/login")
	public Mono<ResponseEntity<AuthResponse>> login(@RequestBody AuthRequest ar) {
		return userServiceI.findByUsername(ar.getUsername())
				.filter(userDetails -> passwordEncoder.encode(ar.getPassword()).equals(userDetails.getPassword()))
				.map(userDetails -> ResponseEntity.ok(new AuthResponse(jwtUtil.generateToken(userDetails))))
				.switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()));
	}
	/*@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
		//no convence a lili
		User u = userServiceI.findByUsername(authRequest.getUsername()).block();
		if (u != null) {
			if (passwordEncoder.encode(authRequest.getPassword()).equals(u.getPassword())) {
				return ResponseEntity.ok(new AuthResponse(jwtUtil.generateToken(u)));
			} else {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
		User u = userService.findByUsername(authRequest.getUsername());
		if (u != null) {
			if (passwordEncoder.encode(authRequest.getPassword()).equals(u.getPassword())) {
				return ResponseEntity.ok(new AuthResponse(jwtUtil.generateToken(u)));
			} else {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}
	@PostMapping("/create")
	public Mono<User> create(@RequestBody User user) {
		return userServiceI.save(user);
		//return createUserUseCase.apply(user);

		Function<UserDTO, Mono<ServerResponse>> excecutor = userDTO -> createUserUseCase.apply(userDTO)
				.flatMap(result -> ServerResponse.ok()
						.contentType(MediaType.TEXT_PLAIN)
						.bodyValue(result));
		return route(
				POST("/createUser").and(accept(MediaType.APPLICATION_JSON)),
				request -> request.bodyToMono(UserDto.class).flatMap(executor)
		);
	}*/


}
