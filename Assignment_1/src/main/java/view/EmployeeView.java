package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class EmployeeView extends JFrame {

    private JButton addClient;
    private JButton viewClient;
    private JButton updateClient;
    private JPanel employeePanel;
    private JLabel employeeLabel;
    private JLabel nameLabel;
    private JLabel addressLabel;
    private JLabel pncLabel;
    private JTextField nameText;
    private JTextField pncText;
    private JTextField addressText;
    private JButton addtheClient;

    private GridBagLayout gbl;
    private GridBagConstraints gbc;

    public EmployeeView() throws HeadlessException {

        // Init and setContentPane
        initializeFields();
        setSize(350, 240);
        setTitle("Employee Interface");
        setLocationRelativeTo(null);
        setContentPane(employeePanel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Add buttons
        gbc = new GridBagConstraints();
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.CENTER;
        add(employeeLabel, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.CENTER;
        add(nameLabel, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(addressLabel, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(pncLabel, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(nameText, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(addressText, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(pncText, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(addtheClient, gbc);
    }

    private void initializeFields() {

        // Init content pane
        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{100, 200};
        gbl.rowHeights = new int[]{40, 40, 30, 30, 30};
        employeePanel = new JPanel();
        employeePanel.setLayout(gbl);
        // Init components
        employeeLabel = new JLabel("Employee UI");
        nameLabel = new JLabel("Name: ");
        pncLabel = new JLabel("PNC: ");
        addressLabel = new JLabel("Address: ");
        nameText = new JTextField();
        addressText = new JTextField();
        pncText = new JTextField();
        addtheClient = new JButton("Add client");
    }

    public void setAddListener(ActionListener l){
        addtheClient.addActionListener(l);
    }

    public String getNameText(){
        return nameText.getText();
    }

    public String getAddressText(){
        return addressText.getText();
    }

    public String getPNCText(){
        return pncText.getText();
    }

}

