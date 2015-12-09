/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.langboost.domain.user;

import ru.langboost.domain.EntityRepository;


/**
 *
 * @author Ильдар
 */
public interface UserRepository extends EntityRepository<User> {
    public User findUserByLogin(String login);
}
