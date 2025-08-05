package ui;

import javax.swing.*;
import java.awt.*;

public class MasterForm extends JFrame {

    private JTextField nameField;
    private JTextField dobField;
    private JComboBox<String> departmentDropdown;
    private JComboBox<Integer> completionYearDropdown;

    @SuppressWarnings("unused")
    public MasterForm() {
        setTitle("Master Student Registration");
        setSize(595, 842);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());
        JLabel logoLabel = new JLabel();
        ImageIcon logoIcon = new ImageIcon("assets/logo.png");
        Image image = logoIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        logoLabel.setIcon(new ImageIcon(image));
        logoLabel.setHorizontalAlignment(JLabel.CENTER);
        add(logoLabel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Name:"), gbc);
        nameField = new JTextField(15);
        gbc.gridx = 1;
        add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("Date of Birth (YYYY-MM-DD):"), gbc);
        dobField = new JTextField(15);
        gbc.gridx = 1;
        add(dobField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        add(new JLabel("Department:"), gbc);
        String[] departments = {"Cyber Security", "Information System Management"};
        departmentDropdown = new JComboBox<>(departments);
        gbc.gridx = 1;
        add(departmentDropdown, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        add(new JLabel("Year of Completion:"), gbc);
        Integer[] years = new Integer[10];
        int currentYear = java.time.Year.now().getValue();
        for (int i = 0; i < years.length; i++) years[i] = currentYear - i;
        completionYearDropdown = new JComboBox<>(years);
        gbc.gridx = 1;
        add(completionYearDropdown, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        gbc.gridwidth = 2;
        JButton submitBtn = new JButton("Submit");
        add(submitBtn, gbc);

        submitBtn.addActionListener(e -> {
            String name = nameField.getText();
            String dob = dobField.getText();
            String department = (String) departmentDropdown.getSelectedItem();
            int year = (Integer) completionYearDropdown.getSelectedItem();

            if (name.isEmpty() || dob.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields.");
                return;
            }

            JOptionPane.showMessageDialog(this, "Master Student Registered:\n" +
                    "Name: " + name + "\nDOB: " + dob + "\nDepartment: " + department + "\nYear: " + year);
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
