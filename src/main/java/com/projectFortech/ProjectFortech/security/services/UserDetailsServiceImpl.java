package com.projectFortech.ProjectFortech.security.services;

import com.projectFortech.ProjectFortech.domain.User;
import com.projectFortech.ProjectFortech.exceptions.UnfoundUserException;
import com.projectFortech.ProjectFortech.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional//AICI AM EMAIL IN LOC DE USERNAME!!!!
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UnfoundUserException("User Not Found with email: " + email));
        List<SimpleGrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(user.getRole()));
        return mapUserToCustomUserDetails(user, authorities);
    }
    private UserDetailsImpl mapUserToCustomUserDetails(User user, List<SimpleGrantedAuthority> authorities) {
        UserDetailsImpl customUserDetails = new UserDetailsImpl();
        customUserDetails.setId(user.getUserId());
        customUserDetails.setName(user.getName());
        customUserDetails.setSurname(user.getSurname());
        customUserDetails.setPassword(user.getPassword());
        customUserDetails.setEmail(user.getEmail());
        customUserDetails.setAuthorities(authorities);
        return customUserDetails;
    }

}

