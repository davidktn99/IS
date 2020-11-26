package persistenceLayer.user;

import businessLayer.User;
import businessLayer.validation.Notification;

import java.util.List;

/**
 * Created by Alex on 11/03/2017.
 */
public interface UserRepository {

    List<User> findAll();

    Notification<User> findByUsernameAndPassword(String username, String password, String mode) throws AuthenticationException;

    boolean addUser(User user);

    boolean deleteById(String id);

    boolean editById(String id, String username, String password);

}
