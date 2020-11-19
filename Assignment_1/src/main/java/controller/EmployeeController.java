package controller;

import model.Client;
import model.builder.ClientBuilder;
import repository.account.AccountRepository;
import repository.client.ClientRepository;
import view.EmployeeView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeController {

    private final EmployeeView employeeView;
    private final AccountRepository accountRepository;
    private final ClientRepository clientRepository;

    public EmployeeController(EmployeeView employeeView, ClientRepository clientRepository, AccountRepository accountRepository) {
        this.employeeView = employeeView;
        this.clientRepository = clientRepository;
        this.employeeView.setVisible(true);
        this.accountRepository = accountRepository;
        employeeView.setAddListener(new addButtonListener());
    }

    private class addButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            Client client = new ClientBuilder()
                    .setName(employeeView.getNameText())
                    .setAddress(employeeView.getAddressText())
                    .setPnc(Long.parseLong(employeeView.getPNCText()))
                    .build();
            clientRepository.addClient(client);
            JOptionPane.showMessageDialog(null, "Succesfully created client!");

        }
    }
}
