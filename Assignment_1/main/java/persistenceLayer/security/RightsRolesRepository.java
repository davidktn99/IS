package persistenceLayer.security;

import businessLayer.Right;
import businessLayer.Role;
import businessLayer.User;

import java.util.List;

/**
 * Created by David Katona on 18/11/2020
 */
public interface RightsRolesRepository {

    void addRole(String role);

    void addRight(String right);

    Role findRoleByTitle(String role);

    Role findRoleById(Long roleId);

    Right findRightByTitle(String right);

    void addRolesToUser(User user, List<Role> roles);

    List<Role> findRolesForUser(Long userId);

    void addRoleRight(Long roleId, Long rightId);
}
