/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.langboost.services.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.langboost.domain.EntityRepository;
import ru.langboost.domain.user.*;
import ru.langboost.services.AbstractService;
import ru.langboost.services.ServiceException;

import javax.inject.Inject;

/**
 *
 * @author Ильдар
 */
@Service
public class DefaultUserService extends AbstractService<User> implements UserService {

    @Inject
    private UserRepository userRepository;

    @Inject
    private RoleRepository roleRepository;

    @Inject
    private UserRoleRepository userRoleRepository;

    @Override
    @Transactional
    public User createNewUser(UserCredential credential, UserData data, Role role) throws ServiceException {
        if(credential == null || !credential.isValid()) {
            throw new ServiceException("Регистрационные данные не корректны");
        }
        if(role == null) {
            throw new ServiceException("Для создания пользователя нужно указать роль");
        }
        User user = new User(credential,data);
        UserRole userRole = new UserRole(user, role);
        userRepository.store(user);
        userRoleRepository.store(userRole);
        return user;
    }

    @Override
    @Transactional(readOnly = true)
    public Role getRoleByName(String name) {
        return roleRepository.getByName(name);
    }

    @Override
    @Transactional
    public Role createRole(String name) {
        Role role = roleRepository.getByName(name);
        if (role == null) {
            role = new Role();
            role.setName(name);
            roleRepository.store(role);
        }
        return role;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = getUserByLogin(username);
        if(userDetails == null) {
            throw new UsernameNotFoundException("Couldn't find user by username:"+username);
        }
        return userDetails;
    }

    @Override
    public User getUserByLogin(String login) {
        return userRepository.findUserByLogin(login);
    }

    @Override
    protected EntityRepository getRepository() {
        return userRepository;
    }


}
