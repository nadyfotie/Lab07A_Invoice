import javax.swing.*;

public class InvoiceRunner {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InvoiceFrame().setVisible(true));
    }
}

