package service.user;

import model.User;
import model.validation.Notification;
import repository.user.AuthenticationException;

/**
 * Created by David Katona on 18/11/2020
 */
public interface AuthenticationService {

    Notification<User> login(String username, String password) throws AuthenticationException;

    public String encodeString (String x);

    boolean logout(User user);

}
