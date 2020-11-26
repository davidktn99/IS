package presentationLayer.controller;

import businessLayer.Account;
import businessLayer.Client;
import businessLayer.builder.AccountBuilder;
import businessLayer.builder.ClientBuilder;
import businessLayer.service.account.AccountService;
import businessLayer.service.client.ClientService;
import businessLayer.validation.AccountValidator;
import businessLayer.validation.ClientValidator;
import businessLayer.validation.TypeValidator;
import persistenceLayer.EntityNotFoundException;
import presentationLayer.view.EmployeeView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EmployeeController {

    private final EmployeeView employeeView;
    private final AccountService accountService;
    private final ClientService clientService;
    private ClientValidator clientValidator;
    private AccountValidator accountValidator;

    public EmployeeController(EmployeeView employeeView, ClientService clientService, AccountService accountService) {
        this.employeeView = employeeView;
        this.clientService = clientService;
        this.employeeView.setVisible(true);
        this.accountService = accountService;
        employeeView.setCreateClientListener(new CreateClientListener());
        employeeView.setEditClientListener(new EditClientListener());
        employeeView.setViewClientsListener(new ViewClientsListener());
        employeeView.setCreateAccountListener(new CreateAccountListener());
        employeeView.setEditAccountListener(new EditAccountListener());
        employeeView.setViewAccountsListener(new ViewAccountsListener());
        employeeView.setDeleteAccountListener(new DeleteAccountListener());
        employeeView.setTransferListener(new TransferListener());
        employeeView.setProcessBillListener(new ProcessBillListener());
        employeeView.setDoCreateClientListener(new DoCreateClientListener());
        employeeView.setDoEditClientListener(new DoEditClientListener());
        employeeView.setDoCreateAccountListener(new DoCreateAccountListener());
        employeeView.setDoEditAccountListener(new DoEditAccountListener());
        employeeView.setDoDeleteAccountListener(new DoDeleteAccountListener());
        employeeView.setDoTransferListener(new DoTransferListener());
        employeeView.setPayBillListener(new PayBillListener());
    }

    public void switchPanels(Component panel) {
        employeeView.setLayeredPane(panel);
    }

    private class CreateClientListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            switchPanels(employeeView.getCreateClientPanel());
        }
    }

    private class EditClientListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            switchPanels(employeeView.getEditClientPanel());
        }
    }

    private class ViewClientsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            employeeView.getClientTableModel().setRowCount(0);
            for (Client i : clientService.getClients()) {
                String id = Long.toString(i.getId()), name = i.getName(), pnc = i.getPnc().toString(), address = i.getAddress();
                Object[] row = {id, name, pnc, address};
                employeeView.addClientRow(row);
            }
            switchPanels(employeeView.getViewClientsPanel());
        }
    }

    private class CreateAccountListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            switchPanels(employeeView.getCreateAccPanel());
        }
    }

    private class EditAccountListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            switchPanels(employeeView.getEditAccPanel());
        }
    }

    private class ViewAccountsListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            employeeView.getAccountTableModel().setRowCount(0);
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            for (Account i : accountService.getAccounts()) {
                String id = Long.toString(i.getId()), type = i.getType(), money = Float.toString(i.getMoney()), date = formatter.format(i.getDate()), clientId = Long.toString(i.getClientId());
                Object[] row = {id, type, money, date, clientId};
                employeeView.addAccountRow(row);
            }
            switchPanels(employeeView.getViewAccountsPanel());
        }
    }

    private class DeleteAccountListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            switchPanels(employeeView.getDeleteAccPanel());
        }
    }

    private class TransferListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            switchPanels(employeeView.getTransferPanel());
        }
    }

    private class ProcessBillListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            switchPanels(employeeView.getProcessBillPanel());
        }
    }

    private class DoCreateClientListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!TypeValidator.validateLong(employeeView.getCreatePncText())) {
                JOptionPane.showMessageDialog(null, "PNC must be only digits!");
                return;
            }
            Client client = new ClientBuilder()
                    .setName(employeeView.getCreateNameText())
                    .setAddress(employeeView.getCreateAddressText())
                    .setPnc(Long.parseLong(employeeView.getCreatePncText()))
                    .build();
            clientValidator = new ClientValidator(client);
            if (clientValidator.validateCreate()) {
                clientService.addClient(client);
                JOptionPane.showMessageDialog(null, "Successfully created client!");
            } else {
                StringBuilder errors = new StringBuilder();
                for (String i : clientValidator.getErrors()) {
                    errors.append(i).append("\n");
                }
                JOptionPane.showMessageDialog(null, errors.toString());
            }
        }
    }

    private class DoEditClientListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!TypeValidator.validateLong(employeeView.getEditIdText())) {
                JOptionPane.showMessageDialog(null, "ID must be only digits!");
                return;
            }
            if (!TypeValidator.validateLong(employeeView.getEditPncText())) {
                JOptionPane.showMessageDialog(null, "PNC must be only digits!");
                return;
            }
            Client client = new ClientBuilder()
                    .setId(Long.parseLong(employeeView.getEditIdText()))
                    .setPnc(Long.parseLong(employeeView.getEditPncText()))
                    .setName(employeeView.getEditNameText())
                    .setAddress(employeeView.getEditAddressText())
                    .build();
            clientValidator = new ClientValidator(client);
            if (clientValidator.validateEdit()) {
                if (clientService.updateById(Long.parseLong(employeeView.getEditIdText()), employeeView.getEditNameText(),
                        Long.parseLong(employeeView.getEditPncText()), employeeView.getEditAddressText()))
                    JOptionPane.showMessageDialog(null, "Successfully edited client!");
                else JOptionPane.showMessageDialog(null, "Client not found!");
            } else {
                StringBuilder errors = new StringBuilder();
                for (String i : clientValidator.getErrors()) {
                    errors.append(i).append("\n");
                }
                JOptionPane.showMessageDialog(null, errors.toString());
            }
        }
    }

    private class DoCreateAccountListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!TypeValidator.validateFloat(employeeView.getCreateAccMoneyText())) {
                JOptionPane.showMessageDialog(null, "Money must be only digits and . ");
                return;
            }
            if (!TypeValidator.validateLong(employeeView.getCreateAccClientIdText())) {
                JOptionPane.showMessageDialog(null, "Client ID must be only digits!");
                return;
            }
            Account account = new AccountBuilder()
                    .setType(employeeView.getCreateAccTypeText())
                    .setMoney(Float.parseFloat(employeeView.getCreateAccMoneyText()))
                    .setDate(new Date())
                    .setClientId(Long.parseLong(employeeView.getCreateAccClientIdText()))
                    .build();
            accountValidator = new AccountValidator(account);
            if (accountValidator.validateCreate()) {
                accountService.addAccount(account);
                JOptionPane.showMessageDialog(null, "Successfully created account!");
            } else {
                StringBuilder errors = new StringBuilder();
                for (String i : accountValidator.getErrors()) {
                    errors.append(i).append("\n");
                }
                JOptionPane.showMessageDialog(null, errors.toString());
            }
        }
    }

    private class DoEditAccountListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!TypeValidator.validateLong(employeeView.getEditAccIdText())) {
                JOptionPane.showMessageDialog(null, "ID must be only digits!");
                return;
            }
            if (!TypeValidator.validateFloat(employeeView.getEditAccMoneyText())) {
                JOptionPane.showMessageDialog(null, "Money must be only digits and . ");
                return;
            }
            if (!TypeValidator.validateLong(employeeView.getEditAccClientIdText())) {
                JOptionPane.showMessageDialog(null, "Client ID must be only digits!");
                return;
            }
            Account account = new AccountBuilder()
                    .setId(Long.parseLong(employeeView.getEditAccIdText()))
                    .setType(employeeView.getEditAccTypeText())
                    .setMoney(Float.parseFloat(employeeView.getEditAccMoneyText()))
                    .setDate(new Date())
                    .setClientId(Long.parseLong(employeeView.getEditAccClientIdText()))
                    .build();
            accountValidator = new AccountValidator(account);
            if (accountValidator.validateEdit()) {
                if (accountService.updateById(Long.parseLong(employeeView.getEditAccIdText()), employeeView.getEditAccTypeText(), Float.parseFloat(employeeView.getEditAccMoneyText()), Long.parseLong(employeeView.getEditAccClientIdText())))
                    JOptionPane.showMessageDialog(null, "Successfully edited account!");
                else JOptionPane.showMessageDialog(null, "Account not found!");
            } else {
                StringBuilder errors = new StringBuilder();
                for (String i : accountValidator.getErrors()) {
                    errors.append(i).append("\n");
                }
                JOptionPane.showMessageDialog(null, errors.toString());
            }
        }
    }

    private class DoDeleteAccountListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!TypeValidator.validateLong(employeeView.getDeleteAccIdText())) {
                JOptionPane.showMessageDialog(null, "ID must be only digits!");
                return;
            }
            Account account = new AccountBuilder()
                    .setId(Long.parseLong(employeeView.getDeleteAccIdText()))
                    .setClientId(0L)
                    .setMoney(0.0f)
                    .setType("a")
                    .setDate(new Date())
                    .build();
            accountValidator = new AccountValidator(account);
            if (accountValidator.validateDelete()) {
                if (accountService.deleteById(Long.parseLong(employeeView.getDeleteAccIdText())))
                    JOptionPane.showMessageDialog(null, "Successfully deleted account!");
                else JOptionPane.showMessageDialog(null, "Account not found!");
            } else {
                StringBuilder errors = new StringBuilder();
                for (String i : accountValidator.getErrors()) {
                    errors.append(i).append("\n");
                }
                JOptionPane.showMessageDialog(null, errors.toString());
            }
        }
    }

    private class DoTransferListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (!TypeValidator.validateLong(employeeView.getSrcAccText())) {
                    JOptionPane.showMessageDialog(null, "Source ID must be only digits!");
                    return;
                }
                if (!TypeValidator.validateLong(employeeView.getDestAccText())) {
                    JOptionPane.showMessageDialog(null, "Destination ID must be only digits!");
                    return;
                }
                if (!TypeValidator.validateFloat(employeeView.getTransferSumText())) {
                    JOptionPane.showMessageDialog(null, "Money must be only digits and .");
                    return;
                }
                accountService.transfer(accountService.findById(Long.parseLong(employeeView.getSrcAccText())),
                        accountService.findById(Long.parseLong(employeeView.getDestAccText())), Float.parseFloat(employeeView.getTransferSumText()));
                JOptionPane.showMessageDialog(null, "Successful transfer!");
            } catch (EntityNotFoundException entityNotFoundException) {
                entityNotFoundException.printStackTrace();
            }
        }
    }

    private class PayBillListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (!TypeValidator.validateLong(employeeView.getPayingAccText())) {
                    JOptionPane.showMessageDialog(null, "Account ID must be only digits!");
                    return;
                }
                if (!TypeValidator.validateFloat(employeeView.getBillValueText())) {
                    JOptionPane.showMessageDialog(null, "Bill value must be only digits and .");
                    return;
                }
                Account account = accountService.findById(Long.parseLong(employeeView.getPayingAccText()));
                float newMoney = account.getMoney() - Float.parseFloat(employeeView.getBillValueText());
                if (newMoney < 0.0f) {
                    JOptionPane.showMessageDialog(null, "Not enough money in account!");
                    return;
                }
                accountService.updateById(account.getId(), account.getType(), newMoney, account.getClientId());
                JOptionPane.showMessageDialog(null, "Bill no. " + employeeView.getBillNoText() + " paid successfully!");
            } catch (EntityNotFoundException entityNotFoundException) {
                entityNotFoundException.printStackTrace();
            }
        }
    }
}
