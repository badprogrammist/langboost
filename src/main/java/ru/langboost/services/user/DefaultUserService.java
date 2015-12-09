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
    public void registerNewUser(UserCredential credential, UserData data, Role role) {
        User user = new User(credential,data);
        UserRole userRole = new UserRole(user, role);
        userRoleRepository.store(userRole);
        userRepository.store(user);
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
        UserDetails userDetails = userRepository.findUserByLogin(username);
        if(userDetails == null) {
            throw  new UsernameNotFoundException("Couldn't find user by username:"+username);
        }
        return userDetails;
    }

    @Override
    protected EntityRepository getRepository() {
        return userRepository;
    }


}
