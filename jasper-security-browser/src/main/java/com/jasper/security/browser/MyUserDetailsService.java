package com.jasper.security.browser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class MyUserDetailsService implements UserDetailsService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		/*
		 * public User(String username, String password, boolean enabled, boolean
		 * accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked,
		 * Collection<? extends GrantedAuthority> authorities) {
		 */
		String password = passwordEncoder.encode("123456");

		logger.info("login username : " + username);
		logger.info("login password : " + password);

		return new User(username, password, true, true, true, true,
				AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));

		// get user details by username
		// return new User(username, "123456",
		// AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
	}

}
