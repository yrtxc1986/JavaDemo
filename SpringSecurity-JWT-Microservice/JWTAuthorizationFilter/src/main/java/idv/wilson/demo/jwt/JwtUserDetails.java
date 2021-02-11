package idv.wilson.demo.jwt;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class JwtUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;
	private String password;
	private String username;
	private String userId;
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credentialsNonExpired;
	private boolean enabled;
	private Collection<? extends GrantedAuthority> authorities;

	public JwtUserDetails(String userId, String username, String password, String roles) {
		this.username = username;
		this.password = password;
		this.userId = userId;
		this.authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(roles);
		accountNonExpired = true;
		accountNonLocked = true;
		credentialsNonExpired = true;
		enabled = true;
	}

}
