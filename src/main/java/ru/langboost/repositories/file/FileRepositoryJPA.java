/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.langboost.repositories.file;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import ru.langboost.domain.file.File;
import ru.langboost.domain.file.FileRepository;
import ru.langboost.repositories.AbstractRepositoryJPA;

/**
 *
 * @author bad
 */
@Repository
public class FileRepositoryJPA extends AbstractRepositoryJPA<File> implements FileRepository {
   
    @PersistenceContext
    private EntityManager entityManager;

    public FileRepositoryJPA() {
        super(File.class);
    }
    
    

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
    
    
}
