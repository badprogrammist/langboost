/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.langboost.domain;

import java.util.List;

/**
 *
 * @author bad
 */
public interface EntityRepository<E extends AbstractEntity> {
    E store(E entity);
    E update(E entity);
    void remove(E entity);
    void remove(Long id);
    E get(Long id);
    List<E> getAll();
}
