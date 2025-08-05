package ui;

import javax.swing.*;

class HomePage extends JFrame {
    public HomePage() {
        setTitle("Home Page");
        setSize(592, 842);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel("Welcome to Home Page!", SwingConstants.CENTER);
        add(label);

        setLocationRelativeTo(null); 
        setVisible(true);
    }
}
