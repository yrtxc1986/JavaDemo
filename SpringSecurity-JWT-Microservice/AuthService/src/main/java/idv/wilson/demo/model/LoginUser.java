package idv.wilson.demo.model;

import lombok.Data;

@Data
public class LoginUser {
	String username;
	String password;
	boolean rememberMe;
}
