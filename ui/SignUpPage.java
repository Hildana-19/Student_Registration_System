package ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.*;

public class SignUpPage extends JFrame {

    private JTextField usernameField;
    private JTextField emailField;
    private JTextField phoneField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private HashMap<String, String> userDatabase;

    public SignUpPage(HashMap<String, String> userDatabase) {
        this.userDatabase = userDatabase;
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Student Registration System");
        setSize(595, 842);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel welcomeLabel = new JLabel("Register as an Admin");
        welcomeLabel.setBounds(100, 30, 400, 70);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(welcomeLabel);

        JLabel nameLabel = new JLabel("Admin Name:");
        nameLabel.setBounds(50, 100, 150, 30);
        nameLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        add(nameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(200, 100, 200, 30);
        add(usernameField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 150, 150, 30);
        emailLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(200, 150, 200, 30);
        add(emailField);

        JLabel phoneLabel = new JLabel("Phone Number:");
        phoneLabel.setBounds(50, 200, 150, 30);
        phoneLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        add(phoneLabel);

        
        phoneField = new JTextField();
        phoneField.setBounds(200, 200, 200, 30);
        add(phoneField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 250, 150, 30);
        passwordLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(200, 250, 200, 30);
        add(passwordField);

        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordLabel.setBounds(50, 300, 150, 30);
        confirmPasswordLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        add(confirmPasswordLabel);

        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(200, 300, 200, 30);
        add(confirmPasswordField);

        JButton signupButton = new JButton("Signup");
        signupButton.setBounds(200, 350, 100, 30);
        signupButton.setBackground(new java.awt.Color(0, 200, 0));
        add(signupButton);

        JLabel labelLogin = new JLabel("Already have an account? Login here");
        labelLogin.setBounds(150, 400, 300, 30);
        add(labelLogin);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(200, 430, 100, 30);
        loginButton.setBackground(new java.awt.Color(0, 200, 0));
        add(loginButton);

        signupButton.addActionListener(e -> {
            dispose();
            new HomePage();
        });
        

        setVisible(true);

        loginButton.addActionListener(e -> {
            dispose();
            new LoginPage();
        });
        
    }

    private class SignUpAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = usernameField.getText();
            String email = emailField.getText();
            String phone = phoneField.getText(); // Use String here, no int parsing
            String password = new String(passwordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());

            if (!isValidUsername(username)) {
                JOptionPane.showMessageDialog(null, "username must be 3-20 characters and contain only letters.");
                return;
            }
            if (!isValidEmail(email)) {
                JOptionPane.showMessageDialog(null, " Email must start with a letter and contain '@' and '.'");
                return;
            }
            if (!isValidPhoneNumber(phone)) {
                JOptionPane.showMessageDialog(null, "phone number Must be 10 digits and start with '09'.");
                return;
            }
            if (!isValidPassword(password)) {
                JOptionPane.showMessageDialog(null, "password! Must be at least 8 characters long and include uppercase, lowercase, number, and special character.");
                return;
            }
            if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(null, "Passwords do not match!");
                return;
            }
            if (userDatabase.containsKey(username)) {
                JOptionPane.showMessageDialog(null, "Username already exists!");
                return;
            }

            userDatabase.put(username, password);
            JOptionPane.showMessageDialog(null, "Sign Up Successful!");
            

            dispose();
            
        }

        private boolean isValidUsername(String username) {

            if (username.length() <= 3 && username.length() > 20) {
                return false;
            }
            for (char c : username.toCharArray()) {
                if (!Character.isLetter(c)) {
                    return false;
                }
            }
            return true;
        }

        private boolean isValidEmail(String email) {
            if (!email.contains("@") || !email.contains(".")) {
                return false;
            }
            if (email.length() < 5) {
                return false;
            }
            int atIndex = email.indexOf('@');
            int dotIndex = email.lastIndexOf('.');

            if (atIndex < 1 || dotIndex < atIndex + 2 || dotIndex >= email.length() - 1) {
                return false;
            }

            if (!Character.isLetter(email.charAt(0))) {
                return false;
            }

            return true;
        }

        private boolean isValidPhoneNumber(String phone) {
            if (phone.length() != 10) {
                return false;
            }
            if (!phone.startsWith("09")) {
                return false;
            }
            for (int i = 2; i < 10; i++) {
                char c = phone.charAt(i);
                if (!Character.isDigit(c)) {
                    return false;
                }
            }
            return true;
        }

        private boolean isValidPassword(String password) {
            if (password.length() < 8) {
                return false;
            }

            boolean hasUpper = false;
            boolean hasLower = false;
            boolean hasNumber = false;
            boolean hasSpecial = false;

            for (int i = 0; i < password.length(); i++) {
                char c = password.charAt(i);

                if (c >= 'A' && c <= 'Z') {
                    hasUpper = true;
                } else if (c >= 'a' && c <= 'z') {
                    hasLower = true;
                } else if (c >= '0' && c <= '9') {
                    hasNumber = true;
                } else {
                    hasSpecial = true;
                }
            }

            return hasUpper && hasLower && hasNumber && hasSpecial;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HashMap<String, String> userDatabase = new HashMap<>();
            new SignUpPage(userDatabase);
        });
    }
}
