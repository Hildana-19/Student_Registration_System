package ui;

import dao.UserDAO;
import model.User;
import util.LoggerUtil;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("unused")
public class LoginPage extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private UserDAO userDAO;

    public LoginPage() {
        userDAO = new UserDAO();
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Login - Student Registration System");
        setSize(595, 842);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel(new BorderLayout());

        JLabel logoLabel = new JLabel();
        ImageIcon logoIcon = new ImageIcon("assets/logo.png");
        Image image = logoIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        logoLabel.setIcon(new ImageIcon(image));
        logoLabel.setHorizontalAlignment(JLabel.CENTER);

        JLabel label = new JLabel("Login to your account", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 18));

        headerPanel.add(logoLabel, BorderLayout.NORTH);
        headerPanel.add(label, BorderLayout.SOUTH);
        add(headerPanel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Username:"), gbc);

        usernameField = new JTextField(15);
        gbc.gridx = 1;
        formPanel.add(usernameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Password:"), gbc);

        passwordField = new JPasswordField(15);
        gbc.gridx = 1;
        formPanel.add(passwordField, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        JButton loginBtn = new JButton("Login");
        loginBtn.setBackground(new Color(0, 128, 0));
        loginBtn.setForeground(Color.GREEN);
        formPanel.add(loginBtn, gbc);

        gbc.gridy = 3;
        JLabel signUpLabel = new JLabel("Don't have an account? Sign Up", SwingConstants.CENTER);
        signUpLabel.setForeground(Color.BLUE);
        formPanel.add(signUpLabel, gbc);

        add(formPanel, BorderLayout.CENTER);

        loginBtn.addActionListener(e -> attemptLogin());

        signUpLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        signUpLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dispose();
                new SignUpPage();
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void attemptLogin() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean valid = userDAO.validateLogin(username, password);
        if (valid) {
            LoggerUtil.log("User logged in: " + username);
            JOptionPane.showMessageDialog(this, "Login successful!");
            dispose();
            new HomePage();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginPage::new);
    }
}
