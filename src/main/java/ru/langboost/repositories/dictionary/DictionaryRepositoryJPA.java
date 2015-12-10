package ru.langboost.repositories.dictionary;

import org.springframework.stereotype.Repository;
import ru.langboost.domain.profile.Profile;
import ru.langboost.domain.Dictionary.Dictionary;
import ru.langboost.domain.Dictionary.DictionaryRepository;
import ru.langboost.repositories.AbstractRepositoryJPA;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;

/**
 * Created by Ildar Gafarov on 09.12.15.
 */
@Repository
public class DictionaryRepositoryJPA extends AbstractRepositoryJPA<Dictionary> implements DictionaryRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public DictionaryRepositoryJPA() {
        super(Dictionary.class);
    }


    @Override
    public List<Dictionary> findByProfile(Profile profile, boolean defaulted) {
        try {
            return entityManager.createNamedQuery("Dictionary.findByProfileAndDefault", Dictionary.class)
                    .setParameter("defaulted", defaulted)
                    .setParameter("profile", profile)
                    .getResultList();

        } catch (Exception ex) {
            return Collections.emptyList();
        }
    }

    @Override
    public List<Dictionary> findByProfile(Profile profile) {
        try {
            return entityManager.createNamedQuery("Dictionary.findByProfile", Dictionary.class)
                    .setParameter("profile", profile)
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
