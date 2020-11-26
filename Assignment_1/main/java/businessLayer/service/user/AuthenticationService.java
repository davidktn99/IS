package businessLayer.service.user;

import businessLayer.Role;
import businessLayer.User;
import businessLayer.validation.Notification;
import persistenceLayer.user.AuthenticationException;

import java.util.List;

/**
 * Created by David Katona on 18/11/2020
 */
public interface AuthenticationService {

    Notification<User> login(String username, String password, String mode) throws AuthenticationException;

    String encodeString(String x);

    Role getRoleByTitle(String title);

    List<User> findAll();

    Notification<User> findByUsernameAndPassword(String username, String password, String mode) throws AuthenticationException;

    boolean addUser(User user);

    boolean deleteById(String id);

    boolean editById(String id, String username, String password);

}
