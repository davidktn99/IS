package databaseLayer;

import java.util.*;

import static databaseLayer.Constants.Rights.*;
import static databaseLayer.Constants.Roles.*;

/**
 * Created by David Katona on 18/11/2020
 */
public class Constants {

    public static Map<String, List<String>> getRolesRights() {

        Map<String, List<String>> ROLES_RIGHTS = new HashMap<>();
        for (String role : ROLES) {
            ROLES_RIGHTS.put(role, new ArrayList<>());
        }
        ROLES_RIGHTS.get(ADMINISTRATOR).addAll(Arrays.asList(RIGHTS));

        ROLES_RIGHTS.get(EMPLOYEE).addAll(Arrays.asList(CREATE_CLIENT, DELETE_CLIENT, UPDATE_CLIENT, CREATE_ACCOUNT, UPDATE_ACCOUNT, DELETE_ACCOUNT, TRANSFER_ACCOUNT));

        return ROLES_RIGHTS;
    }

    public static class Schemas {
        public static final String TEST = "test_bank";
        public static final String PRODUCTION = "bank";

        public static final String[] SCHEMAS = new String[]{TEST, PRODUCTION};
    }

    public static class Tables {
        public static final String ACCOUNT = "account";
        public static final String CLIENT = "client";
        public static final String USER = "user";
        public static final String ROLE = "role";
        public static final String RIGHT = "right";
        public static final String ROLE_RIGHT = "role_right";
        public static final String USER_ROLE = "user_role";

        public static final String[] ORDERED_TABLES_FOR_CREATION = new String[]{
                USER, ROLE, RIGHT, ROLE_RIGHT, USER_ROLE, CLIENT, ACCOUNT};
    }

    public static class Roles {
        public static final String ADMINISTRATOR = "administrator";
        public static final String EMPLOYEE = "employee";

        public static final String[] ROLES = new String[]{ADMINISTRATOR, EMPLOYEE};
    }

    public static class Rights {
        public static final String CREATE_USER = "create_user";
        public static final String DELETE_USER = "delete_user";
        public static final String UPDATE_USER = "update_user";

        public static final String CREATE_CLIENT = "create_client";
        public static final String UPDATE_CLIENT = "update_client";
        public static final String DELETE_CLIENT = "delete_client";

        public static final String CREATE_ACCOUNT = "create_account";
        public static final String DELETE_ACCOUNT = "delete_account";
        public static final String UPDATE_ACCOUNT = "update_account";
        public static final String TRANSFER_ACCOUNT = "transfer_account";
        public static final String PROCESS_BILL = "process_bill";

        public static final String[] RIGHTS = new String[]{
                CREATE_USER, DELETE_USER, UPDATE_USER,
                CREATE_CLIENT, DELETE_CLIENT, UPDATE_CLIENT,
                CREATE_ACCOUNT, DELETE_ACCOUNT, UPDATE_ACCOUNT,
                TRANSFER_ACCOUNT, PROCESS_BILL};
    }

}
