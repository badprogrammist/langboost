package ru.langboost.services.profile;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.langboost.domain.EntityRepository;
import ru.langboost.domain.profile.Profile;
import ru.langboost.domain.profile.ProfileRepository;
import ru.langboost.domain.user.User;
import ru.langboost.services.AbstractService;
import ru.langboost.services.ServiceException;

import javax.inject.Inject;

/**
 * Created by Ildar Gafarov on 09.12.15.
 */
@Service
public class DefaultProfileService extends AbstractService<Profile> implements ProfileService {

    @Inject
    private ProfileRepository profileRepository;

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
    protected EntityRepository<Profile> getRepository() {
        return null;
    }
}
