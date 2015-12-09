/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.langboost.domain.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import ru.langboost.domain.AbstractEntity;

/**
 *
 * @author Ильдар
 */
@Entity
@Table(name = "roles")
@NamedQueries({
    @NamedQuery(name = "Role.findAll",
            query = "Select c from Role c"),
    @NamedQuery(name = "Role.findByName",
            query = "Select c from Role c where c.name = :name")})
public class Role extends AbstractEntity<Role> {
    
    @Column(name = "name",unique = true)
    private String name;

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void merge(Role entity) {
        this.name = entity.getName();
    }
    
    
    
}
