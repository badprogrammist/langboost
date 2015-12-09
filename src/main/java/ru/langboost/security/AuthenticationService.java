package ru.langboost.security;

import ru.langboost.domain.user.User;

/**
 * Created by Ildar Gafarov on 09.12.15.
 */
public interface AuthenticationService {
    UserTransfer authenticate(Credentials credentials) throws SecurityException;
    boolean isAuthenticated();
    boolean hasRole(String authority);
    User getPrincipal();

}
