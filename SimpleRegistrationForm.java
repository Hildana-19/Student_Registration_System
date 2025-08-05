import javax.swing.*;
import java.awt.*;
import java.io.File;


public class SimpleRegistrationForm extends JFrame {
    private JTextField nameField;
    private JComboBox<String> dayCombo, monthCombo, yearCombo;
    private JTextField gmailField;
    private JTextField phoneField;
    private JComboBox<String> programCombo;
    private JLabel fileLabel;
    private File selectedFile;

    public SimpleRegistrationForm() {
        setTitle("Masters Registration Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Logo panel at the top
        JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel logoLabel = new JLabel(new ImageIcon("bits_logo.png"));
        logoPanel.add(logoLabel);
        add(logoPanel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 10, 20);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Set smaller font
        Font smallFont = new Font(Font.SANS_SERIF, Font.PLAIN, 12);

        // Name
        gbc.gridx = 0; gbc.gridy = 0;
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(smallFont);
        formPanel.add(nameLabel, gbc);
        nameField = new JTextField();
        nameField.setFont(smallFont);
        gbc.gridx = 1; gbc.gridy = 0;
        formPanel.add(nameField, gbc);

        // Date of birth
        gbc.gridx = 0; gbc.gridy = 1;
        JLabel dobLabel = new JLabel("Date of birth:");
        dobLabel.setFont(smallFont);
        formPanel.add(dobLabel, gbc);
        JPanel dobPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        dayCombo = new JComboBox<>();
        dayCombo.setFont(smallFont);
        for (int i = 1; i <= 31; i++) dayCombo.addItem(String.valueOf(i));
        dobPanel.add(dayCombo);
        monthCombo = new JComboBox<>(new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"});
        monthCombo.setFont(smallFont);
        dobPanel.add(monthCombo);
        yearCombo = new JComboBox<>();
        yearCombo.setFont(smallFont);
        int currentYear = java.time.Year.now().getValue();
        for (int i = currentYear; i >= 1900; i--) yearCombo.addItem(String.valueOf(i));
        dobPanel.add(yearCombo);
        gbc.gridx = 1; gbc.gridy = 1;
        formPanel.add(dobPanel, gbc);

        // Gmail
        gbc.gridx = 0; gbc.gridy = 2;
        JLabel gmailLabel = new JLabel("Gmail:");
        gmailLabel.setFont(smallFont);
        formPanel.add(gmailLabel, gbc);
        gmailField = new JTextField();
        gmailField.setFont(smallFont);
        gbc.gridx = 1; gbc.gridy = 2;
        formPanel.add(gmailField, gbc);

        // Phone Number
        gbc.gridx = 0; gbc.gridy = 3;
        JLabel phoneLabel = new JLabel("Phone Number:");
        phoneLabel.setFont(smallFont);
        formPanel.add(phoneLabel, gbc);
        phoneField = new JTextField();
        phoneField.setFont(smallFont);
        gbc.gridx = 1; gbc.gridy = 3;
        formPanel.add(phoneField, gbc);

        // Program Dropdown
        gbc.gridx = 0; gbc.gridy = 4;
        JLabel programLabel = new JLabel("Program:");
        programLabel.setFont(smallFont);
        formPanel.add(programLabel, gbc);
        programCombo = new JComboBox<>(new String[]{"Information Technology Systems", "Software Engineering"});
        programCombo.setFont(smallFont);
        gbc.gridx = 1; gbc.gridy = 4;
        formPanel.add(programCombo, gbc);

        // File chooser
        gbc.gridx = 0; gbc.gridy = 5;
        JLabel fileLabelLabel = new JLabel("File:");
        fileLabelLabel.setFont(smallFont);
        formPanel.add(fileLabelLabel, gbc);
        JPanel filePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        JButton fileButton = new JButton("Choose File");
        fileButton.setFont(smallFont);
        fileLabel = new JLabel("Enter your Masters document");
        fileLabel.setFont(smallFont);
        filePanel.add(fileButton);
        filePanel.add(fileLabel);
        gbc.gridx = 1; gbc.gridy = 5;
        formPanel.add(filePanel, gbc);

        // Add a small vertical gap above the button
        gbc.gridx = 0; gbc.gridy = 6;
        gbc.gridwidth = 2;
        formPanel.add(Box.createVerticalStrut(20), gbc);
        gbc.gridwidth = 1;

        gbc.gridx = 0; gbc.gridy = 7;
        gbc.gridwidth = 2;
        JButton registerButton = new JButton("Register");
        registerButton.setFont(smallFont);
        formPanel.add(registerButton, gbc);
        gbc.gridwidth = 1;

        // Show Registered Users button
        gbc.gridx = 0; gbc.gridy = 8;
        gbc.gridwidth = 2;
        JButton showUsersButton = new JButton("Show Registered Users");
        showUsersButton.setFont(smallFont);
        formPanel.add(showUsersButton, gbc);
        gbc.gridwidth = 1;

        showUsersButton.addActionListener(e -> {
            JPasswordField pf = new JPasswordField();
            int okCxl = JOptionPane.showConfirmDialog(this, pf, "Enter admin password", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (okCxl == JOptionPane.OK_OPTION) {
                String password = new String(pf.getPassword());
                if ("bits123".equals(password)) {
                    java.util.List<String[]> users = RegistrationService.getRegisteredUsers();
                    new RegisteredUsersPage(users).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Incorrect password.", "Access Denied", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        fileButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setAcceptAllFileFilterUsed(false);
            fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
                @Override
                public boolean accept(File f) {
                    return f.isDirectory() || f.getName().toLowerCase().endsWith(".pdf");
                }
                @Override
                public String getDescription() {
                    return "PDF Documents (*.pdf)";
                }
            });
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                selectedFile = fileChooser.getSelectedFile();
                fileLabel.setText(selectedFile.getName());
            }
        });

        // Add formPanel to the center of the frame
        add(formPanel, BorderLayout.CENTER);

        registerButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String gmail = gmailField.getText().trim();
            String phone = phoneField.getText().trim();
            String program = (String) programCombo.getSelectedItem();
            String day = (String) dayCombo.getSelectedItem();
            String month = (String) monthCombo.getSelectedItem();
            String year = (String) yearCombo.getSelectedItem();

            // Validation for empty fields
            if (name.isEmpty() || gmail.isEmpty() || phone.isEmpty() || selectedFile == null) {
                JOptionPane.showMessageDialog(this,
                    "Please fill in all fields and select a file.",
                    "Form Incomplete", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validation for full name (at least two words)
            if (!name.contains(" ") || name.split("\\s+").length < 2) {
                JOptionPane.showMessageDialog(this,
                    "Please enter your full name (first and last name).",
                    "Invalid Name", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validation for phone number (10 digits, starts with 07 or 09)
            if (!phone.matches("^(07|09)\\d{8}$")) {
                JOptionPane.showMessageDialog(this,
                    "Please enter a valid phone number ",
                    "Invalid Phone Number", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validation for Gmail address
            if (!gmail.endsWith("@gmail.com")) {
                JOptionPane.showMessageDialog(this,
                    "Please enter a valid Gmail address ending with @gmail.com.",
                    "Invalid Email", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int yearInt = Integer.parseInt(year);
            if (yearInt > 2005) {
                JOptionPane.showMessageDialog(this,
                    "You are under the age requirment to register.",
                    "Registration Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            RegistrationService service = new RegistrationService();
            service.register(name, day, month, year, gmail, phone, program, selectedFile);
            JOptionPane.showMessageDialog(this,
                "Registration submitted!\nSee console for details.",
                "Registration Info", JOptionPane.INFORMATION_MESSAGE);
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SimpleRegistrationForm().setVisible(true);
        });
    }
}
