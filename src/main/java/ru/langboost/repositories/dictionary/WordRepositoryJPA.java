package ru.langboost.repositories.dictionary;

import org.springframework.stereotype.Repository;
import ru.langboost.domain.Dictionary.Dictionary;
import ru.langboost.domain.Dictionary.Word;
import ru.langboost.domain.Dictionary.WordRepository;
import ru.langboost.repositories.AbstractRepositoryJPA;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;

/**
 * Created by Ildar Gafarov on 09.12.15.
 */
@Repository
public class WordRepositoryJPA extends AbstractRepositoryJPA<Word> implements WordRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public WordRepositoryJPA() {
        super(Word.class);
    }


    @Override
    public List<Word> findAll(Dictionary dictionary) {
        try {
            return entityManager.createNamedQuery("Word.findByDictionary", Word.class)
                    .setParameter("dictionary", dictionary)
                    .getResultList();
        } catch (Exception ex) {
            return Collections.emptyList();
        }
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

}
