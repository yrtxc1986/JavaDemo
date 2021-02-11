package idv.wilson.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import idv.wilson.demo.jwt.JwtUserDetails;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
//
//    @Autowired
//    private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
//        User user = userRepository.findByUsername(userName);

		String roles = "";
		String userid = "1";
		if (userName.equals("admin")) {
			roles = "ROLE_ADMIN";
		} else if (userName.equals("user")) {
			roles = "ROLE_USER";
		} else if (userName.equals("wilson")) {
			roles = "ROLE_USER, ROLE_ADMIN";
			userid = "3";
		}
		return new JwtUserDetails(userid, userName, passwordEncoder.encode("1234"), roles);

	}

}