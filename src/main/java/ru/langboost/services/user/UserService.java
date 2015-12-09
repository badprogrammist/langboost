package ru.langboost.services.user;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.langboost.domain.user.Role;
import ru.langboost.domain.user.UserCredential;
import ru.langboost.domain.user.UserData;

/**
 * Created by Ildar Gafarov on 09.12.15.
 */
public interface UserService extends UserDetailsService {
    void registerNewUser(UserCredential credential, UserData data, Role role);
    Role getRoleByName(String name);
    Role createRole(String name);

}
