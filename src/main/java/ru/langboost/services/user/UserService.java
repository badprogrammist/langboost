package ru.langboost.services.user;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.langboost.domain.user.Role;
import ru.langboost.domain.user.User;
import ru.langboost.domain.user.UserCredential;
import ru.langboost.domain.user.UserData;
import ru.langboost.services.ServiceException;

/**
 * Created by Ildar Gafarov on 09.12.15.
 */
public interface UserService extends UserDetailsService {
    User createNewUser(UserCredential credential, UserData data, Role role) throws ServiceException;
    Role getRoleByName(String name);
    Role createRole(String name);
    User getUserByLogin(String login);
}
