package ru.langboost.repositories.profile;

import org.springframework.stereotype.Repository;
import ru.langboost.domain.course.Course;
import ru.langboost.domain.dictionary.Dictionary;
import ru.langboost.domain.dictionary.DictionaryRepository;
import ru.langboost.domain.profile.Profile;
import ru.langboost.domain.profile.ProfileCourse;
import ru.langboost.domain.profile.ProfileCourseRepository;
import ru.langboost.repositories.AbstractRepositoryJPA;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;

/**
 * Created by Ildar Gafarov on 09.12.15.
 */
@Repository
public class ProfileCourseRepositoryJPA extends AbstractRepositoryJPA<ProfileCourse> implements ProfileCourseRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public ProfileCourseRepositoryJPA() {
        super(ProfileCourse.class);
    }


    @Override
    public List<ProfileCourse> findByProfile(Profile profile) {
        try {
            return entityManager.createNamedQuery("ProfileCourse.findByProfile", ProfileCourse.class)
                    .setParameter("profile", profile)
                    .getResultList();

        } catch (Exception ex) {
            return Collections.emptyList();
        }
    }

    @Override
    public List<ProfileCourse> findByCourse(Course course) {
        try {
            return entityManager.createNamedQuery("ProfileCourse.findByCourse", ProfileCourse.class)
                    .setParameter("course", course)
                    .getResultList();

        } catch (Exception ex) {
            return Collections.emptyList();
        }
    }

    @Override
    public ProfileCourse findByCourseAndProfile(Course course, Profile profile) {
        try {
            return entityManager.createNamedQuery("ProfileCourse.findByCourseAndProfile", ProfileCourse.class)
                    .setParameter("course", course)
                    .setParameter("profile", profile)
                    .getSingleResult();

        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

}
