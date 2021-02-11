package idv.wilson.demo.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import idv.wilson.demo.jwt.JwtTokenUtils;
import idv.wilson.demo.jwt.JwtUserDetails;
import idv.wilson.demo.model.LoginUser;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/login")
	public String login(@RequestBody LoginUser loginUser) {
		Authentication authResult = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				loginUser.getUsername(), loginUser.getPassword(), new ArrayList<>()));

		if (authResult.isAuthenticated()) {
			JwtUserDetails userDetails = (JwtUserDetails) authResult.getPrincipal();
			boolean isRemember = loginUser.isRememberMe();
			log.trace(userDetails.toString());
			String token = JwtTokenUtils.createToken(userDetails, isRemember);
			return JwtTokenUtils.TOKEN_PREFIX + token;
		}
		throw new AuthenticationException("") {
			private static final long serialVersionUID = 1L;
		};
	}

}
