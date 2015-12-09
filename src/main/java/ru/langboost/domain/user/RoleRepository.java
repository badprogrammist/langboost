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
public interface RoleRepository extends EntityRepository<Role> {
    public Role getByName(String name);
}
