package ui;

import java.awt.Font;
import java.util.HashMap;
import javax.swing.*;

public class LoginPage {

    private JTextField emailField;
    private JPasswordField passwordField;
    private HashMap<String, String> userDatabase;

    public LoginPage() {
        userDatabase = new HashMap<>();
        initializeUserDatabase();
        initializeUI();
    }

    private void initializeUserDatabase() {
        userDatabase.put("student@example.com", "password123");
        userDatabase.put("admin@example.com", "adminpass");
    }

    private void initializeUI() {
        JFrame frame = new JFrame("Student Registration System");
        frame.setSize(595, 842);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel welcomeLabel = new JLabel("Login to Account");
        welcomeLabel.setBounds(150, 30, 400, 70);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(welcomeLabel);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 100, 150, 30);
        emailLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        frame.add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(200, 100, 200, 30);
        frame.add(emailField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 150, 150, 30);
        passwordLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        frame.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(200, 150, 200, 30);
        frame.add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(200, 200, 100, 30);
        loginButton.setBackground(new java.awt.Color(0, 200, 0));
        frame.add(loginButton);

        loginButton.addActionListener(e -> {
            try {
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());

                if (userDatabase.containsKey(email)) {
                    String storedPassword = userDatabase.get(email);
                    if (storedPassword.equals(password)) {
                        JOptionPane.showMessageDialog(frame, "Login successful!");
                        new HomePage();
                        frame.dispose();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Wrong password. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Email not found. Please check your email.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "An unexpected error occurred. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace(); // For debugging
            }
        });

        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginPage());
    }
}
