/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.langboost.services.registration;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.langboost.domain.user.*;
import ru.langboost.security.Credentials;
import ru.langboost.services.ServiceException;
import ru.langboost.services.profile.ProfileService;
import ru.langboost.services.user.UserService;

import javax.inject.Inject;

/**
 *
 * @author bad
 */
@Service
public class DefaultRegistrationService implements RegistrationService {

    @Inject
    private PasswordEncoder passwordEncoder;
    
    @Inject
    private UserService userService;

    @Inject
    private ProfileService profileService;

    @Override
    @Transactional
    public void register(Credentials credentials, UserData userData, String role) throws ServiceException {
        if (credentials.isValid() && role != null && !role.isEmpty()) {
            UserDetails userDetails = this.userService.getUserByLogin(credentials.getLogin());
            if (userDetails == null) {
                Role userRole = userService.createRole(role);
                UserCredential userCredential = createUserCredential(credentials);
                User user = userService.createNewUser(userCredential, userData, userRole);
                if(!user.hasRole(Roles.ROLE_ADMIN)) {
                    profileService.createNewProfile(user);
                }
            } else {
                throw new ServiceException("Такой пользователь уже существует");
            }
        } else {
            throw new ServiceException("Данные для регистрации не корректны");
        }
        
    }
    
    private UserCredential createUserCredential(Credentials credentials) {
        String encodedPassword = passwordEncoder.encode(credentials.getPassword());
        return new UserCredential(credentials.getLogin(), encodedPassword);
    }
    
}
