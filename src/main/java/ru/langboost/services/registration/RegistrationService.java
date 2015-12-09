package ru.langboost.services.registration;

import ru.langboost.domain.user.UserData;
import ru.langboost.security.Credentials;
import ru.langboost.services.ServiceException;

/**
 * Created by Ildar Gafarov on 09.12.15.
 */
public interface RegistrationService {
    void register(Credentials credentials, UserData userData, String role) throws ServiceException;
}
