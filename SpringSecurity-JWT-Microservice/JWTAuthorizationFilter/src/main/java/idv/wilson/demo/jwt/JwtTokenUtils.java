package idv.wilson.demo.jwt;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

import org.springframework.security.core.GrantedAuthority;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtTokenUtils {

	public static final String TOKEN_HEADER = "Authorization";
	public static final String TOKEN_PREFIX = "Bearer ";

	private static final String SECRET = "jwtsecretdemo";
	private static final String ISS = "WilsonDemo";

	// 过期时间是3600秒，既是1个小时
	private static final long EXPIRATION = 3600L;
	// 选择了记住我之后的过期时间为7天
	private static final long EXPIRATION_REMEMBER = 604800L;
	// 添加角色的key
	private static final String ROLE_CLAIMS = "rol";
	private static final String ID_CLAIMS = "id";

	public static String createToken(JwtUserDetails userDetails, boolean isRememberMe) {
		long expiration = isRememberMe ? EXPIRATION_REMEMBER : EXPIRATION;
		String role = "";
		Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
		for (GrantedAuthority authority : authorities) {
			if (role.length() > 1) {
				role += ",";
			}
			role += authority.getAuthority();
		}

		HashMap<String, Object> map = new HashMap<>();
		map.put(ROLE_CLAIMS, role);
		map.put(ID_CLAIMS, userDetails.getUserId());
		return Jwts.builder().signWith(SignatureAlgorithm.HS512, SECRET).setClaims(map).setIssuer(ISS)
				.setSubject(userDetails.getUsername()).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + expiration * 1000)).compact();
	}

	public static JwtUserDetails getUserDetails(String token) {
		Claims body = getTokenBody(token);
		JwtUserDetails userDetails = new JwtUserDetails((String) body.get(ID_CLAIMS), body.getSubject(), "",
				(String) body.get(ROLE_CLAIMS));
		log.trace((String) body.get(ID_CLAIMS));
		log.trace(userDetails.toString());
		return userDetails;
	}

	// 从token中获取用户名
	public static String getUsername(String token) {
		return getTokenBody(token).getSubject();
	}

	// 获取用户角色
	public static String getUserRole(String token) {
		return (String) getTokenBody(token).get(ROLE_CLAIMS);
	}

	public static String getUserId(String token) {
		return (String) getTokenBody(token).get(ID_CLAIMS);
	}

	// 是否已过期
	public static boolean isExpiration(String token) {
		return getTokenBody(token).getExpiration().before(new Date());
	}

	private static Claims getTokenBody(String token) {
		return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
	}

}