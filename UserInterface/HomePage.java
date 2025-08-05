package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class HomePage extends JFrame {

    @SuppressWarnings("unused")
    public HomePage() {
        setTitle("Home Page - Student Registration System");
        setSize(595, 842);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());

        JLabel logoLabel = new JLabel();
        ImageIcon logoIcon = new ImageIcon("assets/logo.png");
        Image image = logoIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        logoLabel.setIcon(new ImageIcon(image));
        logoLabel.setHorizontalAlignment(JLabel.CENTER);

        JLabel welcomeLabel = new JLabel("Welcome to the Student Registration System", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));

        headerPanel.add(logoLabel, BorderLayout.NORTH);
        headerPanel.add(welcomeLabel, BorderLayout.SOUTH);

        add(headerPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new FlowLayout());

        JLabel chooseLabel = new JLabel("Choose Registration Type:");
        JComboBox<String> typeDropdown = new JComboBox<>(new String[] {
                "Select...", "Bachelor Student", "Master Student", "Extension Student"
        });

        typeDropdown.addActionListener((ActionEvent e) -> {
            String selection = (String) typeDropdown.getSelectedItem();
            if ("Bachelor Student".equals(selection)) {
                new BachelorForm();
                dispose();
            } else if ("Master Student".equals(selection)) {
                new MasterForm();
                dispose();
            } else if ("Extension Student".equals(selection)) {
                new ExtensionForm();
                dispose();
            }
        });

        centerPanel.add(chooseLabel);
        centerPanel.add(typeDropdown);

        add(centerPanel, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
