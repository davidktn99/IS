package businessLayer.service.user;

import businessLayer.Role;
import businessLayer.User;
import businessLayer.validation.Notification;
import persistenceLayer.security.RightsRolesRepository;
import persistenceLayer.user.AuthenticationException;
import persistenceLayer.user.UserRepository;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.List;


/**
 * Created by David Katona on 18/11/2020
 */
public class AuthenticationServiceMySQL implements AuthenticationService {

    private final UserRepository userRepository;
    private final RightsRolesRepository rightsRolesRepository;

    public AuthenticationServiceMySQL(UserRepository userRepository, RightsRolesRepository rightsRolesRepository) {
        this.userRepository = userRepository;
        this.rightsRolesRepository = rightsRolesRepository;
    }

    @Override
    public Notification<User> login(String username, String password, String mode) throws AuthenticationException {
        return userRepository.findByUsernameAndPassword(username, encodePassword(password), mode);
    }

    @Override
    public String encodeString(String x) {
        return encodePassword(x);
    }

    private String encodePassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public Role getRoleByTitle(String title) {
        return rightsRolesRepository.findRoleByTitle(title);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Notification<User> findByUsernameAndPassword(String username, String password, String mode) throws AuthenticationException {
        return userRepository.findByUsernameAndPassword(username, password, mode);
    }

    @Override
    public boolean addUser(User user) {
        return userRepository.addUser(user);
    }

    @Override
    public boolean deleteById(String id) {
        return userRepository.deleteById(id);
    }

    @Override
    public boolean editById(String id, String username, String password) {
        return userRepository.editById(id, username, password);
    }
}
