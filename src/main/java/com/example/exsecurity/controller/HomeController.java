package com.example.exsecurity.controller;


import com.example.exsecurity.Utility.JWTUtility;
import com.example.exsecurity.model.JwtRequest;
import com.example.exsecurity.model.JwtResponse;
import com.example.exsecurity.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @Autowired
    private JWTUtility jwtUtility;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    UserServiceImpl userService;

    @GetMapping("/home")
    public String getHome(){
        return  "Welcome to Spring Security";
    }


    @GetMapping("/admin")
    public String getAdmin(){
        return  "Welcome to Admin Spring Security";
    }


    @PostMapping("/auth")
    public JwtResponse getAuthenticate(@RequestBody JwtRequest jwtRequest) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequest.getUserName(), jwtRequest.getPassword()
                    )
            );
        }catch (BadCredentialsException e){
            throw  new Exception("Invalid Credential.", e);
        }

        final UserDetails userDetails = userService.loadUserByUsername(jwtRequest.getUserName());

        final  String token  = jwtUtility.generateToken(userDetails);

        return  new JwtResponse(token);

    }



}
