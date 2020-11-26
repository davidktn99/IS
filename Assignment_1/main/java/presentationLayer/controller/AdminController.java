package presentationLayer.controller;

import businessLayer.Role;
import businessLayer.User;
import businessLayer.builder.UserBuilder;
import businessLayer.service.user.AuthenticationService;
import businessLayer.validation.UserValidator;
import presentationLayer.view.AdminView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AdminController {

    private final AdminView adminView;
    private final AuthenticationService authenticationService;

    public AdminController(AdminView adminView, AuthenticationService authenticationService) {
        this.adminView = adminView;
        this.adminView.setVisible(true);
        this.authenticationService = authenticationService;
        adminView.setCreateListener(new CreateButtonListener());
        adminView.setEditListener(new EditButtonListener());
        adminView.setShowListener(new ShowButtonListener());
        adminView.setDeleteListener(new DeleteButtonListener());
        adminView.setDoCreateListener(new doCreateListener());
        adminView.setDoDeleteListener(new doDeleteListener());
        adminView.setDoEditListener(new doEditListener());
    }

    public void switchPanels(Component panel) {
        adminView.setLayeredPane(panel);
    }

    private class CreateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            switchPanels(adminView.getCreatePanel());
        }
    }

    private class EditButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            switchPanels(adminView.getEditPanel());
        }
    }

    private class ShowButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            adminView.getShowModel().setRowCount(0);
            for (User i : authenticationService.findAll()) {
                String id = Long.toString(i.getId()), name = i.getUsername(), pass = i.getPassword();
                Object[] row = {id, name, pass};
                adminView.addARow(row);
            }
            switchPanels(adminView.getShowPanel());
        }
    }

    private class DeleteButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            switchPanels(adminView.getDeletePanel());
        }
    }

    private class doCreateListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<Role> roles = new ArrayList<>();
            if (adminView.isEmployeeRadioSelected())
                roles.add(authenticationService.getRoleByTitle("employee"));
            else
                roles.add(authenticationService.getRoleByTitle("administrator"));
            User user = new UserBuilder()
                    .setUsername(adminView.getCreateName())
                    .setPassword(adminView.getCreatePass())
                    .setRoles(roles)
                    .build();
            UserValidator userValidator = new UserValidator(user);
            if (userValidator.validate()) {
                user.setPassword(authenticationService.encodeString(adminView.getCreatePass()));
                if (authenticationService.addUser(user)) {
                    JOptionPane.showMessageDialog(null, "Successfully created user!");
                } else JOptionPane.showMessageDialog(null, "Error creating user!");

            } else {
                StringBuilder errors = new StringBuilder();
                for (String i : userValidator.getErrors()) {
                    errors.append(i).append("\n");
                }
                JOptionPane.showMessageDialog(null, errors.toString());
            }
        }
    }

    private class doEditListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (authenticationService.editById(adminView.getEditIdText(),
                    adminView.getEditNameText(), authenticationService.encodeString(adminView.getEditPassText())))
                JOptionPane.showMessageDialog(null, "Successfully edited user!");
            else JOptionPane.showMessageDialog(null, "User not found!");
        }
    }

    private class doDeleteListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (authenticationService.deleteById(adminView.getDeleteText()))
                JOptionPane.showMessageDialog(null, "Successfully deleted user!");
            else JOptionPane.showMessageDialog(null, "User not found!");
        }
    }
}
