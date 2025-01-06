package challenge.controller;

import challenge.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import challenge.security.IAuthenticationFacade;
import challenge.security.JwtUtil;
import challenge.security.SecUserDetailsService;

@RestController
public class AuthController {

    @Autowired
    SecUserDetailsService userDetailsService;

    @Autowired
    IAuthenticationFacade authenticationFacade;
    
    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("/users/signIn")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {

        User user = new User();
        user.setEmail(authenticationRequest.getEmail());
        user.setPassword(authenticationRequest.getPassword());

        user = User.getPasswordEncryptedUser(user);

        UserDetails userDetails = userDetailsService.loadUserByEmailAndPassword(user.getEmail(), user.getPassword());

        String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}