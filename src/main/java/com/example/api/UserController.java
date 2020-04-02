package com.example.api;

import com.example.entity.User;
import com.example.napages.request.LoginForm;
import com.example.napages.response.JwtResponse;
import com.example.security.JWT.JwtProvider;
import com.example.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@CrossOrigin
@RestController
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    UserService userService;


    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    AuthenticationManager authenticationManager;


//    @RequestMapping(value = "/**/{[path:[^\\.]*}")
//    public String redirect() {
//        // Forward to home page so that route is preserved.
//        return "forward:/index.html";
//    }

//    @GetMapping("/")
//    public String index() {
//        return "index";
//    }

//    @GetMapping(value = "/error")
//    public String forwardTo() {
//        return "forward:/index.html";
//    }
//
//    @GetMapping(value = "/**/{path:[^\\.]*}")
//    public String forward() {
//        return "forward:/index.html";
//    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginForm loginForm) {
        logger.info("Check login");

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginForm.getUsername(), loginForm.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtProvider.generate(authentication);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User user = userService.findOne(userDetails.getUsername());
            return ResponseEntity.ok(new JwtResponse(jwt, user.getEmail(), user.getName(), user.getRole()));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }


    @PostMapping("/register")
    public ResponseEntity<User> save(@RequestBody User user) {
        try {
            return ResponseEntity.ok(userService.save(user));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/profile")
    public ResponseEntity<User> update(@RequestBody User user, Principal principal) {

        try {
            if (!principal.getName().equals(user.getEmail())) throw new IllegalArgumentException();
            return ResponseEntity.ok(userService.update(user));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/profile/{email}")
    public ResponseEntity<User> getProfile(@PathVariable("email") String email, Principal principal) {
        if (principal.getName().equals(email)) {
            return ResponseEntity.ok(userService.findOne(email));
        } else {
            return ResponseEntity.badRequest().build();
        }

    }
}
