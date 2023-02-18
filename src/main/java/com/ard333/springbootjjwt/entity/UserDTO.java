package com.ard333.springbootjjwt.entity;

import com.ard333.springbootjjwt.security.model.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class UserDTO {

    @Getter
    @Setter
    private String id;
    @Getter
    @Setter
    private String username;
    @Getter @Setter
    private String password;



    @Getter @Setter
    private List<Role> roles;

    public UserDTO() {
    }

    public UserDTO(String id,String username, String password,  List<Role> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }
}
