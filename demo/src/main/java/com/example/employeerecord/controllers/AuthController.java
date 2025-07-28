package com.example.employeerecord.controllers;

import com.example.employeerecord.Security.JwtUtil;
import com.example.employeerecord.dao.Employees;
import com.example.employeerecord.dto.AddressDto;
import com.example.employeerecord.dto.EmployeeDto;
import com.example.employeerecord.dto.GenericResponseEntity;
import com.example.employeerecord.repository.EmployeeRepo;
import com.example.employeerecord.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private EmployeeRepo employeesRepo;
    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @PostMapping("/login")
    public GenericResponseEntity<Map<String, String>> login(@RequestBody Employees user) {
        try {
            System.out.println("Login Attempt:");
            System.out.println("Email: " + user.getEmail());
            System.out.println("Raw Password: " + user.getPassword());
            Employees employee = employeesRepo.findByEmail(user.getEmail())
                    .orElseThrow(() -> new RuntimeException("Employee not found"));
            System.out.println("Employee Found in DB:");
            System.out.println("Hashed Password from DB: " + employee.getPassword());
            System.out.println("Matches? " + passwordEncoder.matches("123", employee.getPassword()));

            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            //Employees employee = employeesRepo.findByEmail(user.getEmail())
                   // .orElseThrow(() -> new RuntimeException("Employee not found"));
            String token = jwtUtil.generateJwtToken(userDetails,employee);
            String refreshToken = jwtUtil.generateRefreshToken(userDetails);

            //return ResponseEntity.ok(Map.of(
                   // "accessToken", token,
                    //, refreshToken));
            return GenericResponseEntity.<Map<String, String>>builder()
                    .message("Tokens generated successfully")
                    .data(Map.of(
                            "accessToken", token,
                            "refreshToken", refreshToken))
                    .success(true)
                    .build();

        } catch (Exception e) {

           // return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid credentials"+user.getEmail()+" "+user.getPassword()));
            return GenericResponseEntity.<Map<String, String>>builder()
                    .message("Invalid credentials")
                    .data(Map.of("error", "Invalid credentials"+user.getEmail()+" "+user.getPassword()))
                    .success(false)
                    .build();
        }
    }
    @GetMapping("/testHash")
    public void testHash() {
        String raw = "123";
        String hashed = passwordEncoder.encode(raw);
        System.out.println("Raw: " + raw);
        System.out.println("Hashed: " + hashed);
    }

    @PostMapping("/refresh-token")
    public GenericResponseEntity<Map<String, String>> refreshToken(@RequestBody Map<String, String> body) {
        try {
            String refreshToken = body.get("refreshToken");
            String username = jwtUtil.extractUsername(refreshToken);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if (jwtUtil.isTokenValid(refreshToken, userDetails)) {
                Employees emp = employeesRepo.findByEmail(username).get();
                String newAccessToken = jwtUtil.generateJwtToken(userDetails, emp);

                return GenericResponseEntity.<Map<String, String>>builder()
                        .message("AccessToken generated successfully")
                        .data(Map.of("accessToken", newAccessToken))
                        .success(true)
                        .build();

            } else {
                //return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid refresh token"));
                return GenericResponseEntity.<Map<String, String>>builder()
                        .message("Invalid refresh token")
                        .data(Map.of("error", "Invalid refresh token"))
                        .success(false)
                        .build();
            }
        } catch (Exception e) {
            //return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Token refresh failed"));
            return GenericResponseEntity.<Map<String, String>>builder()
                    .message("Invalid refresh token")
                    .data(Map.of("error", "Invalid refresh token"))
                    .success(false)
                    .build();
        }
    }



}