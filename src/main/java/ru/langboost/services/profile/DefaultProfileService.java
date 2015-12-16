package ru.langboost.services.profile;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.langboost.domain.EntityRepository;
import ru.langboost.domain.course.Course;
import ru.langboost.domain.profile.Profile;
import ru.langboost.domain.profile.ProfileCourse;
import ru.langboost.domain.profile.ProfileCourseRepository;
import ru.langboost.domain.profile.ProfileRepository;
import ru.langboost.domain.user.Roles;
import ru.langboost.domain.user.User;
import ru.langboost.services.AbstractService;
import ru.langboost.services.ServiceException;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Ildar Gafarov on 09.12.15.
 */
@Service
public class DefaultProfileService extends AbstractService<Profile> implements ProfileService {

    @Inject
    private ProfileRepository profileRepository;

    @Inject
    private ProfileCourseRepository profileCourseRepository;

    @Override
    @Transactional
    public Profile createNewProfile(User user) throws ServiceException {
        if(user == null) {
            throw new ServiceException("Отсутствует пользователь для создания профиля");
        }
        Profile profile = new Profile(user);
        profileRepository.store(profile);
        return profile;
    }

    @Override
    @Transactional
    public ProfileCourse attachCourse(Profile profile, Course course) throws ServiceException {
        if(profile == null) {
            throw new ServiceException("Отсутвует профиль для прикрепления курса");
        }
        if(course == null) {
            throw new ServiceException("Отсутвует курс для прикрепления к профилю");
        }
        if(!profile.getUser().hasRole(Roles.ROLE_USER)) {
            throw new ServiceException("Курс можно прикрепить только к пользователю!");
        }
        ProfileCourse profileCourse = new ProfileCourse(profile, course);
        profileCourseRepository.store(profileCourse);
        return profileCourse;
    }

    @Override
    public boolean isAttachable(User user, Course course) {
        if(user.hasRole(Roles.ROLE_USER)) {
            return profileCourseRepository.findByCourseAndProfile(course,user.getProfile()) == null;
        }
        return false;
    }

    @Override
    public boolean isAttached(User user, Course course) {
        return profileCourseRepository.findByCourseAndProfile(course,user.getProfile()) != null;
    }

    @Override
    public List<ProfileCourse> getProfileCourses(Profile profile) throws ServiceException {
        if(profile == null) {
            throw new ServiceException("Отсутвует профиль");
        }
        return profileCourseRepository.findByProfile(profile);
    }

    @Override
    protected EntityRepository<Profile> getRepository() {
        return null;
    }
}
