package presentationLayer.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by David Katona on 18/11/2020
 */
public class LoginView extends JFrame {

    private JRadioButton employeeRadioButton;
    private JRadioButton administratorRadioButton;
    private JTextField userText;
    private JTextField passText;
    private JButton loginButton;
    private JLabel userLabel;
    private JLabel passLabel;
    private JLabel bankLabel;
    private JPanel loginPanel;

    public LoginView() throws HeadlessException {

        // Init and setContentPane
        initializeFields();
        setSize(350, 240);
        setTitle("Bank Manager");
        setLocationRelativeTo(null);
        setContentPane(loginPanel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Add buttons
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.CENTER;
        add(bankLabel, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(employeeRadioButton, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(administratorRadioButton, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(userLabel, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(userText, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(passLabel, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(passText, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.SOUTH;
        add(loginButton, gbc);

        setVisible(true);
    }

    private void initializeFields() {

        // Init content pane
        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{100, 200};
        gbl.rowHeights = new int[]{40, 40, 30, 30, 30};
        loginPanel = new JPanel();
        loginPanel.setLayout(gbl);
        // Init components
        bankLabel = new JLabel("Bank Manager");
        bankLabel.setFont(bankLabel.getFont().deriveFont(25.0f));
        ButtonGroup radioButtons = new ButtonGroup();
        employeeRadioButton = new JRadioButton("Employee");
        employeeRadioButton.setSelected(true);
        administratorRadioButton = new JRadioButton("Administrator");
        radioButtons.add(employeeRadioButton);
        radioButtons.add(administratorRadioButton);
        userLabel = new JLabel("Username: ");
        userText = new JTextField();
        passLabel = new JLabel("Password: ");
        passText = new JTextField();
        loginButton = new JButton("Login");
    }

    public String getUsername() {
        return userText.getText();
    }

    public String getPassword() {
        return passText.getText();
    }

    public void setLoginButtonListener(ActionListener loginButtonListener) {
        loginButton.addActionListener(loginButtonListener);
    }
    public void setAdminListener(ActionListener listener){
        administratorRadioButton.addActionListener(listener);
    }
    public void setEmployeeListener(ActionListener listener){
        employeeRadioButton.addActionListener(listener);
    }


}
