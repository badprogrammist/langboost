/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.langboost.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.langboost.domain.user.User;
import ru.langboost.services.user.DefaultUserService;
import ru.langboost.services.user.UserService;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Ильдар
 */
@Service
@Transactional
public class DefaultAuthenticationService implements AuthenticationService {

    public static final String CREDENTIALS_IS_NOT_VALID = "Credentials is not valid";
    
    @Inject
    private UserService userService;

    @Inject
    private AuthenticationManager authManager;

    @Override
    public UserTransfer authenticate(Credentials credentials) throws SecurityException {
        if (credentials.isValid()) {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(credentials.getLogin(), credentials.getPassword());
            Authentication authentication = this.authManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            Map<String, Boolean> roles = new HashMap<>();
            UserDetails userDetails = userService.loadUserByUsername(credentials.getLogin());
            userDetails.getAuthorities().stream().forEach((authority) -> {
                roles.put(authority.toString(), Boolean.TRUE);
            });
            return new UserTransfer(userDetails.getUsername(), roles, TokenUtils.createToken(userDetails));
        } else {
            throw new SecurityException(CREDENTIALS_IS_NOT_VALID);
        }
    }

    @Override
    public boolean isAuthenticated() {
        return SecurityContextHolder.getContext().getAuthentication().isAuthenticated() && SecurityContextHolder.getContext().getAuthentication().getPrincipal() != "anonymousUser";
    }

    @Override
    public boolean hasRole(String authority) {
        for (GrantedAuthority  ga : SecurityContextHolder.getContext().getAuthentication().getAuthorities()) {
            if(ga.getAuthority().equals(authority)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public User getPrincipal() {
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return user;
        } catch (Exception ex) {
            return User.NULL;
        }
    }
}
