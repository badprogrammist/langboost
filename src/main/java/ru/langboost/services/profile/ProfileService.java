package ru.langboost.services.profile;

import ru.langboost.domain.profile.Profile;
import ru.langboost.domain.user.User;
import ru.langboost.services.ServiceException;

/**
 * Created by Ildar Gafarov on 09.12.15.
 */
public interface ProfileService {

    Profile createNewProfile(User user) throws ServiceException;

}
