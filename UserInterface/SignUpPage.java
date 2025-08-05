package ui;

import dao.UserDAO;
import model.User;
import util.LoggerUtil;

import javax.swing.*;
import java.awt.*;

public class SignUpPage extends JFrame {

    private JTextField usernameField, emailField, phoneField;
    private JPasswordField passwordField, confirmPasswordField;
    private UserDAO userDAO;

    public SignUpPage() {
        userDAO = new UserDAO();
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Sign Up - Student Registration System");
        setSize(595, 842);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel titleLabel = new JLabel("Create a New Account");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setBounds(110, 20, 250, 30);
        add(titleLabel);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(50, 70, 100, 25);
        add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(170, 70, 200, 25);
        add(usernameField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 110, 100, 25);
        add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(170, 110, 200, 25);
        add(emailField);

        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(50, 150, 100, 25);
        add(phoneLabel);

        phoneField = new JTextField();
        phoneField.setBounds(170, 150, 200, 25);
        add(phoneField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 190, 100, 25);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(170, 190, 200, 25);
        add(passwordField);

        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordLabel.setBounds(50, 230, 120, 25);
        add(confirmPasswordLabel);

        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(170, 230, 200, 25);
        add(confirmPasswordField);

        JButton signUpBtn = new JButton("Sign Up");
        signUpBtn.setBounds(170, 280, 100, 30);
        signUpBtn.setBackground(new Color(0, 128, 0));
        signUpBtn.setForeground(Color.GREEN);
        add(signUpBtn);

        JLabel loginLabel = new JLabel("Already have an account? Login here");
        loginLabel.setBounds(120, 320, 250, 25);
        loginLabel.setForeground(Color.BLUE);
        add(loginLabel);

        signUpBtn.addActionListener(_ -> attemptSignUp());

        loginLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dispose();
                new LoginPage();
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void attemptSignUp() {
        String username = usernameField.getText().trim();
        String email = emailField.getText().trim();
        String phone = phoneField.getText().trim();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());

        // Basic validations
        if (username.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        
        if (userDAO.getUserByUsername(username) != null) {
            JOptionPane.showMessageDialog(this, "Username already exists.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        User newUser = new User(username, email, phone, password);
        boolean success = userDAO.addUser(newUser);

        if (success) {
            LoggerUtil.log("New user signed up: " + username);
            JOptionPane.showMessageDialog(this, "Sign Up Successful! Please login.");
            dispose();
            new LoginPage();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to sign up. Try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SignUpPage::new);
    }
}
