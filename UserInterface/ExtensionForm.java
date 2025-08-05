package ui;

import javax.swing.*;
import java.awt.*;

public class ExtensionForm extends JFrame {

    private JTextField nameField;
    private JTextField dobField;
    private JComboBox<String> degreeLevelDropdown;
    private JComboBox<String> departmentDropdown;
    private JComboBox<Integer> completionYearDropdown;

    @SuppressWarnings("unused")
    public ExtensionForm() {
        setTitle("Extension Student Registration");
        setSize(595, 842);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout()); 
        
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
        formPanel.add(new JLabel("Name:"), gbc);
        nameField = new JTextField(15);
        gbc.gridx = 1;
        formPanel.add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        formPanel.add(new JLabel("Date of Birth (YYYY-MM-DD):"), gbc);
        dobField = new JTextField(15);
        gbc.gridx = 1;
        formPanel.add(dobField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        formPanel.add(new JLabel("Degree Level:"), gbc);
        String[] degreeLevels = {"Bachelor", "Master"};
        degreeLevelDropdown = new JComboBox<>(degreeLevels);
        gbc.gridx = 1;
        formPanel.add(degreeLevelDropdown, gbc);

        gbc.gridx = 0; gbc.gridy++;
        formPanel.add(new JLabel("Department:"), gbc);
        departmentDropdown = new JComboBox<>();
        gbc.gridx = 1;
        formPanel.add(departmentDropdown, gbc);

        degreeLevelDropdown.addActionListener(e -> updateDepartments());
        updateDepartments();

        gbc.gridx = 0; gbc.gridy++;
        formPanel.add(new JLabel("Year of Completion:"), gbc);
        Integer[] years = new Integer[10];
        int currentYear = java.time.Year.now().getValue();
        for (int i = 0; i < years.length; i++) years[i] = currentYear - i;
        completionYearDropdown = new JComboBox<>(years);
        gbc.gridx = 1;
        formPanel.add(completionYearDropdown, gbc);

        gbc.gridx = 0; gbc.gridy++;
        gbc.gridwidth = 2;
        JButton submitBtn = new JButton("Submit");
        formPanel.add(submitBtn, gbc);

        add(formPanel, BorderLayout.CENTER);

        submitBtn.addActionListener(e -> {
            String name = nameField.getText();
            String dob = dobField.getText();
            String degreeLevel = (String) degreeLevelDropdown.getSelectedItem();
            String department = (String) departmentDropdown.getSelectedItem();
            int year = (Integer) completionYearDropdown.getSelectedItem();

            if (name.isEmpty() || dob.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields.");
                return;
            }

            JOptionPane.showMessageDialog(this, "Extension Student Registered:\n" +
                    "Name: " + name + "\nDOB: " + dob + "\nDegree Level: " + degreeLevel +
                    "\nDepartment: " + department + "\nYear: " + year);
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void updateDepartments() {
        String degreeLevel = (String) degreeLevelDropdown.getSelectedItem();
        departmentDropdown.removeAllItems();
        if ("Bachelor".equals(degreeLevel)) {
            departmentDropdown.addItem("Software Engineering");
            departmentDropdown.addItem("Information Technology Systems");
        } else if ("Master".equals(degreeLevel)) {
            departmentDropdown.addItem("Cyber Security");
            departmentDropdown.addItem("Information System Management");
        }
    }
}
