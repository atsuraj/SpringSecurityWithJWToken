package com.example.exsecurity.serviceImpl;

import com.example.exsecurity.services.UserService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {



        return new User("system","system", new ArrayList<>());
    }
}
