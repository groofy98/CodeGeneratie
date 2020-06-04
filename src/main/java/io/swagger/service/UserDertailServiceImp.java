package io.swagger.service;

import io.swagger.dao.UserRepository;
import io.swagger.model.User;
import io.swagger.model.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDertailServiceImp implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = Optional.ofNullable(userRepository.findUserByEmail(s)).orElseThrow(
                () -> new UsernameNotFoundException("User " + s + " not found"));
        return new UserDetail(user);
    }
}
