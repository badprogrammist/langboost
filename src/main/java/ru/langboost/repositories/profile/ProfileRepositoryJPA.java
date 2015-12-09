package ru.langboost.repositories.profile;

import org.springframework.stereotype.Repository;
import ru.langboost.domain.profile.Profile;
import ru.langboost.domain.profile.ProfileRepository;
import ru.langboost.repositories.AbstractRepositoryJPA;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Ildar Gafarov on 09.12.15.
 */
@Repository
public class ProfileRepositoryJPA extends AbstractRepositoryJPA<Profile> implements ProfileRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public ProfileRepositoryJPA() {
        super(Profile.class);
    }


    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

}
