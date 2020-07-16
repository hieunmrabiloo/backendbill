package com.example.backendbill.config.security;

import com.example.backendbill.entity.User;
import com.example.backendbill.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElse(null);
        if (user == null) throw new UsernameNotFoundException("User not found with username : " + username);
        return create(user);
    }

    // This method is used by JWTAuthenticationFilter
    @Transactional
    public UserDetails loadUserById(Long id) {
        User user =
                userRepository
                        .findById(id)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found with id : " + id));
        return create(user);
    }

    private UserDetails create(User user) {
        return new UserPrincipal(
                user.getId(), user.getUsername(), user.getPassword(), null );
    }
}
