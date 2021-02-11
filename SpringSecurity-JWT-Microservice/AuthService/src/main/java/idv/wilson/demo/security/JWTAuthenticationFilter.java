package idv.wilson.demo.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import idv.wilson.demo.jwt.JwtTokenUtils;
import idv.wilson.demo.jwt.JwtUserDetails;
import idv.wilson.demo.model.LoginUser;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
		super.setFilterProcessesUrl("/auth/login");
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		// 从输入流中获取到登录的信息
		try {
			LoginUser loginUser = new ObjectMapper().readValue(request.getInputStream(), LoginUser.class);
			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getUsername(),
					loginUser.getPassword(), new ArrayList<>()));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	// 成功验证后调用的方法
	// 如果验证成功，就生成token并返回
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {

		// 查看源代码会发现调用getPrincipal()方法会返回一个实现了`UserDetails`接口的对象
		// 所以就是JwtUser啦
		JwtUserDetails jwtUser = (JwtUserDetails) authResult.getPrincipal();
		System.out.println("jwtUser:" + jwtUser.toString());
		String token = JwtTokenUtils.createToken(jwtUser, false);
		// 返回创建成功的token
		// 但是这里创建的token只是单纯的token
		// 按照jwt的规定，最后请求的格式应该是 `Bearer token`
		response.setHeader("token", JwtTokenUtils.TOKEN_PREFIX + token);
	}

	// 这是验证失败时候调用的方法
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		response.getWriter().write("authentication failed, reason: " + failed.getMessage());
	}
}
