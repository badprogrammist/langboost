/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.langboost.repositories.user;

import org.springframework.stereotype.Repository;
import ru.langboost.domain.user.UserRole;
import ru.langboost.domain.user.UserRoleRepository;
import ru.langboost.repositories.AbstractRepositoryJPA;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ильдар
 */
@Repository
public class UserRoleRepositoryJPA extends AbstractRepositoryJPA<UserRole> implements UserRoleRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public UserRoleRepositoryJPA() {
        super(UserRole.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
}
