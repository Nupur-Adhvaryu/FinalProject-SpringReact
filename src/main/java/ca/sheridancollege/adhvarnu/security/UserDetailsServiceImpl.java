package ca.sheridancollege.adhvarnu.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ca.sheridancollege.adhvarnu.beans.Role;
import ca.sheridancollege.adhvarnu.repositories.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		ca.sheridancollege.adhvarnu.beans.User user = userRepository.findByEmail(username);
		
				if(user == null) {
					System.out.println("User not found: "+ username);
					throw new UsernameNotFoundException("User " + username);
				}
				
				List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
				
				for(Role role : user.getRoleList()) {
					grantList.add(new SimpleGrantedAuthority(role.getRolename()));
					
				}
				
				UserDetails userDetails = (UserDetails) new org.springframework.security.core.userdetails.User(user.getEmail(),user.getEncryptedPassword(), grantList);
				return userDetails;
	}

}
