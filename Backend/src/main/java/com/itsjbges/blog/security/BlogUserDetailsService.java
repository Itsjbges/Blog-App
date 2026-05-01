package com.itsjbges.blog.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.itsjbges.blog.domain.entities.User;
import com.itsjbges.blog.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BlogUserDetailsService implements UserDetailsService { // Class ini ngebantu UserDetails class untuk
                                                                    // actually go into the db and find the value and
                                                                    // wrap it into a UserDetail object

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        return new BlogUserDetail(user);

    }

}
