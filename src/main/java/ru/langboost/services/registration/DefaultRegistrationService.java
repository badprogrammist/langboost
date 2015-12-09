/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.langboost.services.registration;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.langboost.domain.user.Role;
import ru.langboost.domain.user.UserCredential;
import ru.langboost.domain.user.UserData;
import ru.langboost.security.Credentials;
import ru.langboost.services.user.DefaultUserService;
import ru.langboost.services.user.UserService;

import javax.inject.Inject;

/**
 *
 * @author bad
 */
@Service
@Transactional
public class DefaultRegistrationService implements RegistrationService {

    @Inject
    private PasswordEncoder passwordEncoder;
    
    @Inject
    private UserService userService;

    @Override
    public boolean register(Credentials credentials, UserData userData, String role) {
        if (credentials.isValid() && role != null && !role.isEmpty()) {
            UserDetails userDetails = this.userService.loadUserByUsername(credentials.getLogin());
            if (userDetails == null) {
                Role userRole = userService.createRole(role);
                UserCredential userCredential = createUserCredential(credentials);
                if (userRole != null) {
                    userService.registerNewUser(userCredential,userData, userRole);
                    return true;
                }
            }
        }
        return false;
        
    }
    
    private UserCredential createUserCredential(Credentials credentials) {
        String encodedPassword = passwordEncoder.encode(credentials.getPassword());
        return new UserCredential(credentials.getLogin(), encodedPassword);
    }
    
}
