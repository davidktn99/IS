package presentationLayer.controller;

import businessLayer.User;
import businessLayer.service.account.AccountService;
import businessLayer.service.client.ClientService;
import businessLayer.service.user.AuthenticationService;
import businessLayer.validation.Notification;
import persistenceLayer.user.AuthenticationException;
import presentationLayer.view.AdminView;
import presentationLayer.view.EmployeeView;
import presentationLayer.view.LoginView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by David Katona on 18/11/2020
 */
public class LoginController {

    private final LoginView loginView;
    private final AuthenticationService authenticationService;
    private final AccountService accountService;
    private final ClientService clientService;

    private enum loginMode {ADMIN, EMPLOYEE}

    private loginMode mode;

    public LoginController(AuthenticationService authenticationService, AccountService accountService, ClientService clientService) {
        this.loginView = new LoginView();
        this.authenticationService = authenticationService;
        this.accountService = accountService;
        this.clientService = clientService;
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
                if (mode == loginMode.ADMIN)
                    loginNotification = authenticationService.login(username, password, "administrator");
                else
                    loginNotification = authenticationService.login(username, password, "employee");
            } catch (AuthenticationException e1) {
                e1.printStackTrace();
            }

            if (loginNotification != null) {
                if (loginNotification.hasErrors()) {
                    JOptionPane.showMessageDialog(loginView.getContentPane(), loginNotification.getFormattedErrors());
                } else {
                    if (mode == loginMode.ADMIN) {
                        new AdminController(new AdminView(), authenticationService);
                    } else {
                        new EmployeeController(new EmployeeView(), clientService, accountService);
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
