package idv.wilson.demo.jwt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter {

	@Autowired
	AdditionalPermit permit;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String tokenHeader = request.getHeader(JwtTokenUtils.TOKEN_HEADER);
		// 如果请求头中没有Authorization信息则直接放行了
		if (tokenHeader == null || !tokenHeader.startsWith(JwtTokenUtils.TOKEN_PREFIX)) {
			chain.doFilter(request, response);
			return;
		}
		// 如果请求头中有token，则进行解析，并且设置认证信息
		SecurityContextHolder.getContext().setAuthentication(getAuthentication(tokenHeader));
		chain.doFilter(request, response);
	}

	// 这里从token中获取用户信息并新建一个token
	private UsernamePasswordAuthenticationToken getAuthentication(String tokenHeader) {
		String token = tokenHeader.replace(JwtTokenUtils.TOKEN_PREFIX, "");
		try {
			JwtUserDetails userDetails = JwtTokenUtils.getUserDetails(token);
			String additionalPermit = permit.get(userDetails.getUserId());
			List<GrantedAuthority> newPermit = new ArrayList<GrantedAuthority>();
			List<GrantedAuthority> addPermit = AuthorityUtils.commaSeparatedStringToAuthorityList(additionalPermit);
			newPermit.addAll(addPermit);
			newPermit.addAll(userDetails.getAuthorities());

			return new UsernamePasswordAuthenticationToken(userDetails, null, newPermit);
		} catch (ExpiredJwtException e) {

		}
		return null;
	}
}