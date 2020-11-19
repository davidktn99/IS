package controller;

import model.User;
import model.validation.Notification;
import repository.account.AccountRepository;
import repository.client.ClientRepository;
import repository.user.AuthenticationException;
import repository.user.UserRepository;
import service.user.AuthenticationService;
import view.AdminView;
import view.EmployeeView;
import view.LoginView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by David Katona on 18/11/2020
 */
public class LoginController {

    private final LoginView loginView;
    private final AuthenticationService authenticationService;
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final ClientRepository clientRepository;
    private enum loginMode {ADMIN, EMPLOYEE}
    private loginMode mode;


    public LoginController(LoginView loginView, AuthenticationService authenticationService, UserRepository userRepository, AccountRepository accountRepository, ClientRepository clientRepository) {
        this.loginView = loginView;
        this.authenticationService = authenticationService;
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.clientRepository = clientRepository;
        loginView.setLoginButtonListener(new LoginButtonListener());
        loginView.setAdminListener(new AdminListener());
        loginView.setEmployeeListener(new EmployeeListener());
    }

    private class LoginButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = loginView.getUsername();
            String password = loginView.getPassword();

            Notification<User> loginNotification = null;
            try {
                loginNotification = authenticationService.login(username, password);
            } catch (AuthenticationException e1) {
                e1.printStackTrace();
            }

            if (loginNotification != null) {
                if (loginNotification.hasErrors()) {
                    JOptionPane.showMessageDialog(loginView.getContentPane(), loginNotification.getFormattedErrors());
                } else {
                    if (mode == loginMode.ADMIN){
                        AdminController ac = new AdminController(new AdminView(), userRepository, authenticationService);
                    }
                    else{
                        EmployeeController ec = new EmployeeController(new EmployeeView(), clientRepository, accountRepository);
                    }
                }
            }
        }
    }

    private class AdminListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            mode = loginMode.ADMIN;
        }
    }

    private class EmployeeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            mode = loginMode.EMPLOYEE;
        }
    }

}
