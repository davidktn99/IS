package presentationLayer.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class EmployeeView extends JFrame {

    private JPanel employeePanel;
    private JLabel employeeLabel;
    private JLayeredPane layeredPane;
    private JPanel createClientPanel;
    private JPanel editClientPanel;
    private JScrollPane viewClientsPanel;
    private JPanel createAccPanel;
    private JPanel editAccPanel;
    private JScrollPane viewAccountsPanel;
    private JPanel deleteAccPanel;
    private JPanel transferPanel;
    private JPanel processBillPanel;

    private JButton createClientButton;
    private JButton editClientButton;
    private JButton viewClientsButton;
    private JButton createAccountButton;
    private JButton editAccountButton;
    private JButton viewAccountsButton;
    private JButton deleteAccountButton;
    private JButton transferButton;
    private JButton processBillButton;

    // CREATE CLIENT
    private JLabel createName;
    private JLabel createAddress;
    private JLabel createPnc;
    private JTextField createNameText;
    private JTextField createAddressText;
    private JTextField createPncText;
    private JButton doCreateClientButton;

    // EDIT CLIENT
    private JLabel editId;
    private JLabel editName;
    private JLabel editPnc;
    private JLabel editAddress;
    private JTextField editIdText;
    private JTextField editNameText;
    private JTextField editAddressText;
    private JTextField editPncText;
    private JButton doEditClientButton;

    // VIEW CLIENTS
    private DefaultTableModel clientTableModel;
    private JTable clientTable;

    // CREATE ACCOUNT
    private JLabel createAccType;
    private JLabel createAccMoney;
    private JLabel createAccClientId;
    private JTextField createAccTypeText;
    private JTextField createAccMoneyText;
    private JTextField createAccClientIdText;
    private JButton doCreateAccountButton;

    // EDIT ACCOUNT
    private JLabel editAccId;
    private JLabel editAccType;
    private JLabel editAccMoney;
    private JLabel editAccClientId;
    private JTextField editAccIdText;
    private JTextField editAccTypeText;
    private JTextField editAccMoneyText;
    private JTextField editAccClientIdText;
    private JButton doEditAccountButton;

    // VIEW ACCOUNTS
    private DefaultTableModel accountTableModel;
    private JTable accountTable;

    // DELETE ACCOUNT
    private JLabel deleteAccId;
    private JTextField deleteAccIdText;
    private JButton doDeleteAccountButton;

    // TRANSFER MONEY
    private JLabel srcAcc;
    private JLabel destAcc;
    private JLabel transferSum;
    private JTextField srcAccText;
    private JTextField destAccText;
    private JTextField transferSumText;
    private JButton doTransfer;

    // PROCESS BILL
    private JLabel billNo;
    private JLabel payingAcc;
    private JLabel billValue;
    private JTextField billNoText;
    private JTextField payingAccText;
    private JTextField billValueText;
    private JButton payBill;

    public EmployeeView() throws HeadlessException {

        // Init and setContentPane
        initializeFields();
        setSize(500, 380);
        setTitle("Employee Interface");
        setLocationRelativeTo(null);
        setContentPane(employeePanel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Add components
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = 4;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.CENTER;
        add(employeeLabel, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(createClientButton, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(editClientButton, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(viewClientsButton, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(createAccountButton, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(editAccountButton, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(viewAccountsButton, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(deleteAccountButton, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 2;
        add(transferButton, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(processBillButton, gbc);

        // Add layered pane
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 4;
        gbc.fill = GridBagConstraints.BOTH;
        add(layeredPane, gbc);

        // CREATE
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        createClientPanel.add(createName, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        createClientPanel.add(createPnc, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        createClientPanel.add(createAddress, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        createClientPanel.add(createNameText, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        createClientPanel.add(createPncText, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        createClientPanel.add(createAddressText, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 2;
        createClientPanel.add(doCreateClientButton, gbc);

        // EDIT
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        editClientPanel.add(editId, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        editClientPanel.add(editIdText, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        editClientPanel.add(editName, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        editClientPanel.add(editNameText, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        editClientPanel.add(editPnc, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        editClientPanel.add(editPncText, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        editClientPanel.add(editAddress, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        editClientPanel.add(editAddressText, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        editClientPanel.add(doEditClientButton, gbc);

        // VIEW CLIENTS
        viewClientsPanel.setOpaque(false);
        viewClientsPanel.setViewportView(clientTable);

        // CREATE ACCOUNT
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        createAccPanel.add(createAccType, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        createAccPanel.add(createAccTypeText, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        createAccPanel.add(createAccMoney, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        createAccPanel.add(createAccMoneyText, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        createAccPanel.add(createAccClientId, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        createAccPanel.add(createAccClientIdText, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        createAccPanel.add(doCreateAccountButton, gbc);

        // EDIT ACCOUNT
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        editAccPanel.add(editAccId, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        editAccPanel.add(editAccIdText, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        editAccPanel.add(editAccType, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        editAccPanel.add(editAccTypeText, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        editAccPanel.add(editAccMoney, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        editAccPanel.add(editAccMoneyText, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        editAccPanel.add(editAccClientId, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        editAccPanel.add(editAccClientIdText, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 2;
        editAccPanel.add(doEditAccountButton, gbc);

        // VIEW ACCOUNT
        viewAccountsPanel.setOpaque(false);
        viewAccountsPanel.setViewportView(accountTable);

        // DELETE ACCOUNT
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        deleteAccPanel.add(deleteAccId, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        deleteAccPanel.add(deleteAccIdText, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 2;
        deleteAccPanel.add(doDeleteAccountButton, gbc);

        // TRANSFER MONEY
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        transferPanel.add(srcAcc, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        transferPanel.add(srcAccText, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        transferPanel.add(destAcc, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        transferPanel.add(destAccText, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        transferPanel.add(transferSum, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        transferPanel.add(transferSumText, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 2;
        transferPanel.add(doTransfer, gbc);

        // PROCESS BILL
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        processBillPanel.add(billNo, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        processBillPanel.add(billNoText, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        processBillPanel.add(payingAcc, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        processBillPanel.add(payingAccText, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        processBillPanel.add(billValue, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        processBillPanel.add(billValueText, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        processBillPanel.add(payBill, gbc);

    }

    private void initializeFields() {

        // Init content pane
        GridBagLayout gbl = new GridBagLayout();
        GridBagLayout gbl_create = new GridBagLayout();
        GridBagLayout gbl_creatAcc = new GridBagLayout();
        GridBagLayout gbl_edit = new GridBagLayout();
        GridBagLayout gbl_editAcc = new GridBagLayout();
        GridBagLayout gbl_deleteAcc = new GridBagLayout();
        GridBagLayout gbl_transfer = new GridBagLayout();
        GridBagLayout gbl_processBill = new GridBagLayout();

        gbl.columnWidths = new int[]{100, 100, 100, 100};
        gbl.rowHeights = new int[]{40, 30, 30, 30, 150};
        employeePanel = new JPanel();
        employeePanel.setLayout(gbl);
        layeredPane = new JLayeredPane();
        layeredPane.setLayout(new CardLayout(0, 0));
        // 1st row
        createClientPanel = new JPanel();
        gbl_create.columnWidths = new int[]{200, 200};
        createClientPanel.setLayout(gbl_create);
        editClientPanel = new JPanel();
        gbl_edit.columnWidths = new int[]{200, 200};
        editClientPanel.setLayout(gbl_edit);
        viewClientsPanel = new JScrollPane();
        // 2nd row
        createAccPanel = new JPanel();
        gbl_creatAcc.columnWidths = new int[]{200, 200};
        createAccPanel.setLayout(gbl_creatAcc);
        editAccPanel = new JPanel();
        gbl_editAcc.columnWidths = new int[]{200, 200};
        editAccPanel.setLayout(gbl_edit);
        viewAccountsPanel = new JScrollPane();
        deleteAccPanel = new JPanel();
        gbl_deleteAcc.columnWidths = new int[]{200, 200};
        deleteAccPanel.setLayout(gbl_edit);
        // 3rd row
        transferPanel = new JPanel();
        gbl_transfer.columnWidths = new int[]{200, 200};
        transferPanel.setLayout(gbl_transfer);
        processBillPanel = new JPanel();
        gbl_processBill.columnWidths = new int[]{200, 200};
        processBillPanel.setLayout(gbl_processBill);

        // Init main components
        employeeLabel = new JLabel("Employee UI");
        employeeLabel.setFont(employeeLabel.getFont().deriveFont(25.0f));
        createClientButton = new JButton("Create client");
        editClientButton = new JButton("Edit client");
        viewClientsButton = new JButton("View clients");
        createAccountButton = new JButton("Create account");
        editAccountButton = new JButton("Edit account");
        viewAccountsButton = new JButton("View accounts");
        deleteAccountButton = new JButton("Delete account");
        transferButton = new JButton("Transfer money");
        processBillButton = new JButton("Process bill");

        // CREATE CLIENT
        createName = new JLabel("Name: ");
        createPnc = new JLabel("PNC: ");
        createAddress = new JLabel("Address: ");
        createNameText = new JTextField();
        createAddressText = new JTextField();
        createPncText = new JTextField();
        doCreateClientButton = new JButton("Add client");

        // EDIT CLIENT
        editId = new JLabel("ID: ");
        editName = new JLabel("Name: ");
        editPnc = new JLabel("PNC: ");
        editAddress = new JLabel("Address: ");
        editIdText = new JTextField();
        editNameText = new JTextField();
        editAddressText = new JTextField();
        editPncText = new JTextField();
        doEditClientButton = new JButton("Edit client by ID");

        // VIEW CLIENTS
        Object[] clientHeaders = {"ID", "Name", "PNC", "Address"};
        clientTableModel = new DefaultTableModel(clientHeaders, 0);
        clientTable = new JTable(clientTableModel);
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(clientTable.getModel());
        List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
        sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
        sortKeys.add(new RowSorter.SortKey(1, SortOrder.ASCENDING));
        sortKeys.add(new RowSorter.SortKey(2, SortOrder.ASCENDING));
        sorter.setSortKeys(sortKeys);
        clientTable.setRowSorter(sorter);
        clientTable.getColumnModel().getColumn(0).setPreferredWidth(30);
        clientTable.getColumnModel().getColumn(1).setPreferredWidth(80);
        clientTable.getColumnModel().getColumn(2).setPreferredWidth(70);
        clientTable.getColumnModel().getColumn(3).setPreferredWidth(80);

        // CREATE ACCOUNT
        createAccType = new JLabel("Type: ");
        createAccMoney = new JLabel("Money: ");
        createAccClientId = new JLabel("Client ID: ");
        createAccTypeText = new JTextField();
        createAccMoneyText = new JTextField();
        createAccClientIdText = new JTextField();
        doCreateAccountButton = new JButton("Create account");

        // EDIT ACCOUNT
        editAccId = new JLabel("ID: ");
        editAccType = new JLabel("Type: ");
        editAccMoney = new JLabel("Money: ");
        editAccClientId = new JLabel("Client ID: ");
        editAccIdText = new JTextField();
        editAccTypeText = new JTextField();
        editAccMoneyText = new JTextField();
        editAccClientIdText = new JTextField();
        doEditAccountButton = new JButton("Edit account by ID");

        // VIEW ACCOUNTS
        Object[] accountHeaders = {"ID", "Type", "Money", "Date created", "Client ID"};
        accountTableModel = new DefaultTableModel(accountHeaders, 0);
        accountTable = new JTable(accountTableModel);
        TableRowSorter<TableModel> sorter2 = new TableRowSorter<>(accountTable.getModel());
        List<RowSorter.SortKey> sortKeys2 = new ArrayList<>(25);
        sortKeys2.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
        sortKeys2.add(new RowSorter.SortKey(1, SortOrder.ASCENDING));
        sortKeys2.add(new RowSorter.SortKey(2, SortOrder.ASCENDING));
        sorter2.setSortKeys(sortKeys2);
        accountTable.setRowSorter(sorter2);
        accountTable.getColumnModel().getColumn(0).setPreferredWidth(30);
        accountTable.getColumnModel().getColumn(1).setPreferredWidth(50);
        accountTable.getColumnModel().getColumn(2).setPreferredWidth(50);
        accountTable.getColumnModel().getColumn(3).setPreferredWidth(80);
        accountTable.getColumnModel().getColumn(4).setPreferredWidth(30);

        // DELETE ACCOUNT
        deleteAccId = new JLabel("Account ID: ");
        deleteAccIdText = new JTextField();
        doDeleteAccountButton = new JButton("Delete account");

        // TRANSFER MONEY
        srcAcc = new JLabel("Source ID: ");
        destAcc = new JLabel("Destination ID: ");
        transferSum = new JLabel("Money: ");
        srcAccText = new JTextField();
        destAccText = new JTextField();
        transferSumText = new JTextField();
        doTransfer = new JButton("Transfer");

        // PROCESS BILL
        billNo = new JLabel("Bill number: ");
        payingAcc = new JLabel("Pay with account ID: ");
        billValue = new JLabel("Sum to pay: ");
        billNoText = new JTextField();
        payingAccText = new JTextField();
        billValueText = new JTextField();
        payBill = new JButton("Pay bill");
    }

    // Action listener setters
    public void setCreateClientListener(ActionListener l) {
        createClientButton.addActionListener(l);
    }

    public void setEditClientListener(ActionListener l) {
        editClientButton.addActionListener(l);
    }

    public void setViewClientsListener(ActionListener l) {
        viewClientsButton.addActionListener(l);
    }

    public void setCreateAccountListener(ActionListener l) {
        createAccountButton.addActionListener(l);
    }

    public void setEditAccountListener(ActionListener l) {
        editAccountButton.addActionListener(l);
    }

    public void setViewAccountsListener(ActionListener l) {
        viewAccountsButton.addActionListener(l);
    }

    public void setDeleteAccountListener(ActionListener l) {
        deleteAccountButton.addActionListener(l);
    }

    public void setTransferListener(ActionListener l) {
        transferButton.addActionListener(l);
    }

    public void setProcessBillListener(ActionListener l) {
        processBillButton.addActionListener(l);
    }

    public void setDoCreateClientListener(ActionListener l) {
        doCreateClientButton.addActionListener(l);
    }

    public void setDoEditClientListener(ActionListener l) {
        doEditClientButton.addActionListener(l);
    }

    public void setDoCreateAccountListener(ActionListener l) {
        doCreateAccountButton.addActionListener(l);
    }

    public void setDoEditAccountListener(ActionListener l) {
        doEditAccountButton.addActionListener(l);
    }

    public void setDoDeleteAccountListener(ActionListener l) {
        doDeleteAccountButton.addActionListener(l);
    }

    public void setDoTransferListener(ActionListener l) {
        doTransfer.addActionListener(l);
    }

    public void setPayBillListener(ActionListener l) {
        payBill.addActionListener(l);
    }

    // JTextField getters
    public String getCreateNameText() {
        return createNameText.getText();
    }

    public String getCreateAddressText() {
        return createAddressText.getText();
    }

    public String getCreatePncText() {
        return createPncText.getText();
    }

    public String getCreateAccTypeText() {
        return createAccTypeText.getText();
    }

    public String getCreateAccMoneyText() {
        return createAccMoneyText.getText();
    }

    public String getCreateAccClientIdText() {
        return createAccClientIdText.getText();
    }

    public String getEditIdText() {
        return editIdText.getText();
    }

    public String getEditNameText() {
        return editNameText.getText();
    }

    public String getEditAddressText() {
        return editAddressText.getText();
    }

    public String getEditPncText() {
        return editPncText.getText();
    }

    public String getEditAccIdText() {
        return editAccIdText.getText();
    }

    public String getEditAccTypeText() {
        return editAccTypeText.getText();
    }

    public String getEditAccMoneyText() {
        return editAccMoneyText.getText();
    }

    public String getEditAccClientIdText() {
        return editAccClientIdText.getText();
    }

    public String getDeleteAccIdText() {
        return deleteAccIdText.getText();
    }

    public String getSrcAccText() {
        return srcAccText.getText();
    }

    public String getDestAccText() {
        return destAccText.getText();
    }

    public String getTransferSumText() {
        return transferSumText.getText();
    }

    public String getBillNoText() {
        return billNoText.getText();
    }

    public String getPayingAccText() {
        return payingAccText.getText();
    }

    public String getBillValueText() {
        return billValueText.getText();
    }

    // Panel getters
    public JPanel getCreateClientPanel() {
        return createClientPanel;
    }

    public JPanel getCreateAccPanel() {
        return createAccPanel;
    }

    public JPanel getEditClientPanel() {
        return editClientPanel;
    }

    public JScrollPane getViewClientsPanel() {
        return viewClientsPanel;
    }

    public JPanel getEditAccPanel() {
        return editAccPanel;
    }

    public JPanel getDeleteAccPanel() {
        return deleteAccPanel;
    }

    public JPanel getTransferPanel() {
        return transferPanel;
    }

    public JPanel getProcessBillPanel() {
        return processBillPanel;
    }

    public JScrollPane getViewAccountsPanel() {
        return viewAccountsPanel;
    }

    /**
     * Method used to set currently visible panel in layeredPane
     *
     * @param panel The panel to be shown
     */
    public void setLayeredPane(Component panel) {
        layeredPane.removeAll();
        layeredPane.add(panel);
        layeredPane.repaint();
        layeredPane.revalidate();
    }

    public DefaultTableModel getClientTableModel() {
        return clientTableModel;
    }

    public DefaultTableModel getAccountTableModel() {
        return accountTableModel;
    }

    /**
     * Method used to add a row to the client table
     *
     * @param row The row
     */
    public void addClientRow(Object[] row) {
        clientTableModel.addRow(row);
    }

    /**
     * Method used to add a row to the account table
     *
     * @param row The row
     */
    public void addAccountRow(Object[] row) {
        accountTableModel.addRow(row);
    }


}

