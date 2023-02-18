package com.ard333.springbootjjwt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import com.ard333.springbootjjwt.security.model.Role;

import java.lang.annotation.Documented;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author ard333
 */
@NoArgsConstructor @AllArgsConstructor @ToString @Document(collection = "User")
public class User implements UserDetails{
	
	//private static final long serialVersionUID = 1L;
	@Id
	@Getter
	@Setter
	private String id;

	@Getter
	@Setter
	private String username;
	
	private String password;
	@Getter @Setter
	private List<Role> roles;

	@JsonIgnore
	public String getPassword() {
		return password;
	}
	
	@JsonProperty
	public void setPassword(String password) {
		this.password = password;
	}



	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		for (Role r : this.roles) {
			authorities.add(new SimpleGrantedAuthority(r.toString()));
		}
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}

}