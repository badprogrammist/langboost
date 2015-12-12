package ru.langboost.services.profile;

import ru.langboost.domain.course.Course;
import ru.langboost.domain.profile.Profile;
import ru.langboost.domain.profile.ProfileCourse;
import ru.langboost.domain.user.User;
import ru.langboost.services.ServiceException;

import java.util.List;

/**
 * Created by Ildar Gafarov on 09.12.15.
 */
public interface ProfileService {

    Profile createNewProfile(User user) throws ServiceException;

    ProfileCourse attachCourse(Profile profile, Course course) throws ServiceException;

    boolean isAttachable(User user, Course course);

    List<ProfileCourse> getProfileCourses(Profile profile) throws ServiceException;
}
