package challenge.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Service;

import challenge.model.User;
import challenge.repository.UserRepository;

@Service
public class SecUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException(String.format("Username %s not found", username));
		} else {
			UserDetails details = new SecUserDetails(user);
			return details;
		}
	}

	public UserDetails loadUserByEmailAndPassword(String username, String password) throws UsernameNotFoundException {
		User user = userRepository.findByEmailAndPassword(username, password);
		if (user == null) {
			throw new SessionAuthenticationException("Login failed");
		} else {
			user.hidePassword();
			UserDetails details = new SecUserDetails(user);
			return details;
		}
	}

}
