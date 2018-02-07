package com.rmportal.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rmportal.model.Role;
import com.rmportal.model.User;
import com.rmportal.repositories.UserRepository;

@Service(value = "authService")
public class AuthService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Username " + username + " not found");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword() ,getGrantedAuthorities(user));
	}

	private Collection<? extends GrantedAuthority> getGrantedAuthorities(User user) {
		Collection<SimpleGrantedAuthority> authorities= new ArrayList<SimpleGrantedAuthority>();
		for(Role role : user.getRoles()){
			System.out.println("Role : " + role.getName());
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}
//		authorities.add(new SimpleGrantedAuthority("ROLE_BASIC"));
//		authorities.add(new SimpleGrantedAuthority("ROLE_ADMI"));
		return authorities;
	}

}
