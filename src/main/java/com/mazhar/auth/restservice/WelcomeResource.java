package com.mazhar.auth.restservice;

import com.mazhar.auth.model.AuthRequest;
import com.mazhar.auth.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import static com.mazhar.auth.util.AppURL.base_url;


@RestController
@RequestMapping(path = base_url  /*produces = { "application/json" }*/)
public class WelcomeResource {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("greet")
    public String greetings() {
        return "welcome here!";
    }

    @GetMapping("welcome")
    public String welcome() {
        return "welcome user!";
    }
    @PostMapping("authenticate")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
            );
        } catch (Exception ex) {
            throw new Exception("inavalid username/password");
        }
        return jwtUtil.generateToken(authRequest.getUserName());
    }
}
