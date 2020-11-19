package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AdminView extends JFrame {
    private JButton createButton;
    private JButton editButton;
    private JButton showButton;
    private JButton deleteButton;
    private JLayeredPane layeredPane;
    private JPanel createPanel;
    private JPanel editPanel;
    private JPanel showPanel;
    private JPanel deletePanel;
    private JLabel adminLabel;
    private JPanel adminPanel;

    //CREATE
    private JLabel employeeName;
    private JLabel employeePass;
    private JTextField nameText;
    private JTextField passText;
    private JButton doCreate;

    //EDIT
    private JLabel editId;
    private JLabel editName;
    private JLabel editPass;
    private JTextField editIdText;
    private JTextField editNameText;
    private JTextField editPassText;
    private JButton doEdit;

    //SHOW
    private JScrollPane scrollPane;
    private DefaultTableModel showModel;
    private JTable table;

    //DELETE
    private JLabel deleteId;
    private JTextField deleteText;
    private JButton doDelete;

    private GridBagConstraints gbc;
    private GridBagLayout gbl;


    public AdminView() throws HeadlessException {

        // Init and setContentPane
        initializeFields();
        setSize(450, 300);
        setTitle("Administrator Interface");
        setLocationRelativeTo(null);
        setContentPane(adminPanel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Add buttons
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = 4;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.CENTER;
        add(adminLabel, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(createButton, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(editButton, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(showButton, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(deleteButton, gbc);

        //Add layered pane
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(layeredPane, gbc);

        //CREATE
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        createPanel.add(employeeName, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        createPanel.add(employeePass, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        createPanel.add(nameText, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        createPanel.add(passText, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 2;
        createPanel.add(doCreate, gbc);

        //EDIT
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        editPanel.add(editId, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        editPanel.add(editIdText, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        editPanel.add(editName, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        editPanel.add(editNameText, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        editPanel.add(editPass, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        editPanel.add(editPassText, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 2;
        editPanel.add(doEdit, gbc);

        //SHOW
        scrollPane.setOpaque(false);
        scrollPane.setViewportView(table);
        layeredPane.add(scrollPane);

        //DELETE
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        deletePanel.add(deleteId, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        deletePanel.add(deleteText, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 2;
        deletePanel.add(doDelete, gbc);

    }

    private void initializeFields() {

        // Init content pane
        GridBagLayout gbl = new GridBagLayout();
        GridBagLayout gbl_create = new GridBagLayout();
        GridBagLayout gbl_edit = new GridBagLayout();
        GridBagLayout gbl_show = new GridBagLayout();
        GridBagLayout gbl_delete = new GridBagLayout();

        gbl.columnWidths = new int[]{100, 100, 100, 100};
        gbl.rowHeights = new int[]{30, 30, 100};
        adminPanel = new JPanel();
        adminPanel.setLayout(gbl);
        layeredPane = new JLayeredPane();
        layeredPane.setLayout(new CardLayout(0, 0));

        createPanel = new JPanel();
        gbl_create.columnWidths =  new int[]{200, 200};
        createPanel.setLayout(gbl_create);

        editPanel = new JPanel();
        gbl_edit.columnWidths = new int[]{200, 200};
        editPanel.setLayout(gbl_edit);

        showPanel = new JPanel();
        showPanel.setLayout(gbl_show);

        deletePanel = new JPanel();
        gbl_delete.columnWidths = new int[]{200, 200};
        deletePanel.setLayout(gbl_delete);

        // Init components
        adminLabel = new JLabel("Administrator UI");
        adminLabel.setFont(adminLabel.getFont().deriveFont(25.0f));
        createButton = new JButton("Create user");
        editButton = new JButton("Edit user");
        showButton = new JButton("Show users");
        deleteButton = new JButton("Delete user");

        //CREATE
        employeeName = new JLabel("Employee name: ");
        employeePass = new JLabel("Employee pass: ");
        nameText = new JTextField();
        passText = new JTextField();
        doCreate = new JButton("Create new employee");

        //EDIT
        editId = new JLabel("Employee ID: ");
        editName = new JLabel("Employee name: ");
        editPass = new JLabel("Employee pass: ");
        editIdText = new JTextField();
        editNameText = new JTextField();
        editPassText = new JTextField();
        doEdit = new JButton("Edit credentials of ID");

        //SHOW
        scrollPane = new JScrollPane();
        scrollPane.setPreferredSize(new Dimension(150, 150));
        Object[] headers = { "ID", "Name", "Password" };
        showModel = new DefaultTableModel(headers, 0);
        table = new JTable(showModel);
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
        List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
        sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
        sortKeys.add(new RowSorter.SortKey(1, SortOrder.ASCENDING));
        sortKeys.add(new RowSorter.SortKey(2, SortOrder.ASCENDING));
        sorter.setSortKeys(sortKeys);
        table.setRowSorter(sorter);
        table.getColumnModel().getColumn(0).setPreferredWidth(30);
        table.getColumnModel().getColumn(1).setPreferredWidth(80);
        table.getColumnModel().getColumn(2).setPreferredWidth(80);

        //DELETE
        deleteId = new JLabel("Employee ID to delete: ");
        deleteText = new JTextField();
        doDelete = new JButton("Delete employee");

        setLayeredPane(createPanel);
    }

    public void setCreateListener(ActionListener l) {
        createButton.addActionListener(l);
    }

    public void setEditListener(ActionListener l) {
        editButton.addActionListener(l);
    }

    public void setShowListener(ActionListener l) {
        showButton.addActionListener(l);
    }

    public void setDeleteListener(ActionListener l) {
        deleteButton.addActionListener(l);
    }

    public void setDoCreateListener(ActionListener l){
        doCreate.addActionListener(l);
    }

    public void setLayeredPane(Component pane) {
        layeredPane.removeAll();
        layeredPane.add(pane);
        layeredPane.repaint();
        layeredPane.revalidate();
    }

    @Override
    public JLayeredPane getLayeredPane() {
        return layeredPane;
    }

    public JPanel getCreatePanel() {
        return createPanel;
    }

    public JPanel getEditPanel() {
        return editPanel;
    }

    public JScrollPane getShowPanel() {
        return scrollPane;
    }

    public JPanel getDeletePanel() {
        return deletePanel;
    }

    public String getCreateName() {
        return nameText.getText();
    }

    public String getCreatepass(){
        return passText.getText();
    }

    public void addARow(Object[] row){
        showModel.addRow(row);
    }

    public DefaultTableModel getShowModel() {
        return showModel;
    }

}

