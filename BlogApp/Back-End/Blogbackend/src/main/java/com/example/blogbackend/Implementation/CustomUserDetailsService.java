package com.example.blogbackend.Implementation;

import com.example.blogbackend.Repository.UserRepository;
import com.example.blogbackend.Modal.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findFirstByEmail(username);
        if(user ==null) {
            throw new UsernameNotFoundException("User Not Found");
        }
        return new CustomUser(user);
    }
}
