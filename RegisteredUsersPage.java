import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class RegisteredUsersPage extends JFrame {
    public RegisteredUsersPage(List<String[]> users) {
        setTitle("Registered Users");
        setSize(700, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] columns = {"Name", "DOB", "Gmail", "Phone", "Program", "File", "Status"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        for (String[] user : users) {
            // Ensure all rows have a status, for backward compatibility
            if (user.length == 6) {
                String[] extended = new String[7];
                System.arraycopy(user, 0, extended, 0, 6);
                extended[6] = "Pending";
                model.addRow(extended);
            } else {
                model.addRow(user);
            }
        }
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton updateButton = new JButton("Update");
        JButton removeButton = new JButton("Remove");
        JButton approveButton = new JButton("Approve");
        buttonPanel.add(approveButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(removeButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Approve user
        approveButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                String status = (String) model.getValueAt(selectedRow, 6);
                if ("Approved".equals(status)) {
                    JOptionPane.showMessageDialog(this, "User is already approved.", "Info", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    model.setValueAt("Approved", selectedRow, 6);
                    RegistrationService.approveUser(selectedRow);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a user to approve.", "No Selection", JOptionPane.WARNING_MESSAGE);
            }
        });

        // Remove user
        removeButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to remove this user?", "Confirm Remove", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    model.removeRow(selectedRow);
                    RegistrationService.removeUser(selectedRow);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a user to remove.", "No Selection", JOptionPane.WARNING_MESSAGE);
            }
        });

        // Update user
        updateButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                String[] current = new String[model.getColumnCount()];
                for (int i = 0; i < current.length; i++) {
                    current[i] = (String) model.getValueAt(selectedRow, i);
                }
                JPanel panel = new JPanel(new GridLayout(current.length, 2));
                JTextField[] fields = new JTextField[current.length];
                for (int i = 0; i < current.length; i++) {
                    panel.add(new JLabel(model.getColumnName(i)));
                    fields[i] = new JTextField(current[i]);
                    panel.add(fields[i]);
                }
                int result = JOptionPane.showConfirmDialog(this, panel, "Update User", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    String[] updated = new String[current.length];
                    for (int i = 0; i < current.length; i++) {
                        updated[i] = fields[i].getText();
                        model.setValueAt(updated[i], selectedRow, i);
                    }
                    RegistrationService.updateUser(selectedRow, updated);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a user to update.", "No Selection", JOptionPane.WARNING_MESSAGE);
            }
        });
    }
}

