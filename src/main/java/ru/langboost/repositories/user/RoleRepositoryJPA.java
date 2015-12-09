/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.langboost.repositories.user;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import ru.langboost.domain.user.Role;
import ru.langboost.domain.user.RoleRepository;
import ru.langboost.repositories.AbstractRepositoryJPA;

/**
 *
 * @author Ильдар
 */
@Repository
public class RoleRepositoryJPA extends AbstractRepositoryJPA<Role> implements RoleRepository {
    
    @PersistenceContext
    private EntityManager entityManager;

    public RoleRepositoryJPA() {
        super(Role.class);
    }
    
    @Override
    public Role getByName(String name) {
        List<Role> roles = entityManager.createNamedQuery("Role.findByName",Role.class)
                .setParameter("name", name)
                .getResultList();
        if (roles == null || roles.isEmpty()) {
            return null;
        } else {
            return roles.iterator().next();
        }
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
        

}
