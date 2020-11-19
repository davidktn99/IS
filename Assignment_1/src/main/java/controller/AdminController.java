package controller;

import model.Role;
import model.User;
import model.builder.UserBuilder;
import repository.user.UserRepository;
import service.user.AuthenticationService;
import view.AdminView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AdminController {

    private final AdminView adminView;
    private final UserRepository userRepository;
    private final AuthenticationService authenticationService;

    public AdminController(AdminView adminView, UserRepository userRepository, AuthenticationService authenticationService) {
        this.adminView = adminView;
        this.userRepository = userRepository;
        this.adminView.setVisible(true);
        this.authenticationService = authenticationService;
        adminView.setCreateListener(new CreateButtonListener());
        adminView.setEditListener(new EditButtonListener());
        adminView.setShowListener(new ShowButtonListener());
        adminView.setDeleteListener(new DeleteButtonListener());
        adminView.setDoCreateListener(new doCreateListener());
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

    private class ShowButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            adminView.getShowModel().setRowCount(0);
            for (Iterator<User> it = userRepository.findAll().iterator(); it.hasNext();) {
                User i = it.next();
                String id = Long.toString(i.getId()), name = i.getUsername(), pass = i.getPassword();
                Object[] row = { id, name, pass};
                adminView.addARow(row);
            }
            switchPanels(adminView.getShowPanel());
        }
    }

    private class DeleteButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            switchPanels(adminView.getDeletePanel());
        }
    }

    private class doCreateListener implements  ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            List<Role> roles = new ArrayList<>();
            User user = new UserBuilder()
                    .setUsername(adminView.getCreateName())
                    .setPassword(authenticationService.encodeString(adminView.getCreatepass()))
                    .setRoles(roles)
                    .build();
            userRepository.addUser(user);
            JOptionPane.showMessageDialog(null, "Succesfully created user!");
        }
    }

    private class doEditListener implements  ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

            JOptionPane.showMessageDialog(null, "Succesfully edited user!");
        }
    }

    public void switchPanels(Component panel) {
        adminView.setLayeredPane(panel);
    }
}
