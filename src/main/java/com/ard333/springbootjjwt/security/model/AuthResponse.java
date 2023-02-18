package com.ard333.springbootjjwt.security.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author ard333
 */
@NoArgsConstructor @AllArgsConstructor @Data
public class AuthResponse {
	private String token;
}
