package com.standard.security;

import com.standard.constant.ErrorEnum;
import com.standard.entity.User;
import com.standard.exception.ApplicationException;
import com.standard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
//        Optional<User> optionalUser = userRepository.findByUsername(username);
//        return optionalUser.orElseThrow(() ->
//                new UsernameNotFoundException("Username: {" + username +"} not found"));

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username: {" + username +"} not found"));
        return new UserDetailsImpl(user);
    }

}
