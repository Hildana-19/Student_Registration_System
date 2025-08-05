import ui.master.MasterRegistrationForm;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MasterRegistrationForm form = new MasterRegistrationForm();
            form.setVisible(true);
        });
    }
}
