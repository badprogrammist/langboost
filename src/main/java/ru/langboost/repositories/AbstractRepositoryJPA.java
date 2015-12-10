/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.langboost.repositories;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import ru.langboost.domain.AbstractEntity;
import ru.langboost.domain.EntityRepository;

/**
 *
 * @author bad
 */
public abstract class AbstractRepositoryJPA<E extends AbstractEntity> implements EntityRepository<E> {

    protected abstract EntityManager getEntityManager();
    private final Class<E> entityClass;

    public AbstractRepositoryJPA(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public void store(E entity) {
        getEntityManager().persist(getEntityManager().merge(entity));
    }

    @Override
    public void remove(Long id) {
        E entity = get(id);
        if (entity != null) {
            remove(entity);
        }
    }

    @Override
    public void remove(E entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    @Override
    public E update(E entity) {
        return getEntityManager().merge(entity);
    }

    @Override
    public List<E> getAll() {
        final CriteriaBuilder builder = this.getEntityManager().getCriteriaBuilder();
        final CriteriaQuery<E> criteriaQuery = builder.createQuery(this.entityClass);
        criteriaQuery.from(this.entityClass);
        TypedQuery<E> typedQuery = this.getEntityManager().createQuery(criteriaQuery);
        return typedQuery.getResultList();
    }

    @Override
    public E get(Long id) {
        try {
            return getEntityManager().find(entityClass, id);
        } catch (Exception ex) {
            return null;
        }
    }
}
