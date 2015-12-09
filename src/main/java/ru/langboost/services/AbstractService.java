/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.langboost.services;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import ru.langboost.domain.AbstractEntity;
import ru.langboost.domain.EntityRepository;

/**
 *
 * @author bad
 */
public abstract class AbstractService<E extends AbstractEntity> {

    protected abstract EntityRepository<E> getRepository();

    @Transactional
    protected void store(E entity) {
        getRepository().store(entity);
    }

    @Transactional
    protected E update(E entity) {
        return getRepository().update(entity);
    }

    @Transactional
    protected E merge(E entity) {
        if (entity != null && entity.getId() != null) {
            E old = get(entity.getId());
            if (old != null) {
                old.merge(entity);
                return getRepository().update(old);
            }
        }
        return entity;
    }

    public List<E> getAll() {
        return getRepository().getAll();
    }

    public E get(Long id) {
        return (E) getRepository().get(id);
    }

    @Transactional
    public void remove(Long id) {
        getRepository().remove(id);
    }

    @Transactional
    public void remove(E entity) {
        getRepository().remove(entity);
    }
}
