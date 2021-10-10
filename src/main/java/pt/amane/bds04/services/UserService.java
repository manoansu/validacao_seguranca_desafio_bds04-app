package pt.amane.bds04.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import pt.amane.bds04.entities.User;
import pt.amane.bds04.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService{

	private Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = repository.findByEmail(username);
		if (user == null) {
			logger.error("Email not found! " + username);
			throw new UsernameNotFoundException("Email not found!");
		}
		logger.info("User found success! " + username);
		return user;
	}

}
