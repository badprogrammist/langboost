/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.langboost.repositories.user;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import ru.langboost.domain.user.User;
import ru.langboost.domain.user.UserRepository;
import ru.langboost.repositories.AbstractRepositoryJPA;

/**
 *
 * @author Ильдар
 */
@Repository
public class UserRepositoryJPA extends AbstractRepositoryJPA<User> implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public UserRepositoryJPA() {
        super(User.class);
    }

    
    @Override
    public User findUserByLogin(String login) {
        List<User> user;
        user = entityManager.createNamedQuery("User.findByLogin", User.class)
                .setParameter("login", login)
                .getResultList();
        if (user != null && user.size() == 1) {
            return user.iterator().next();
        } else {
            return null;
        }
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
}
