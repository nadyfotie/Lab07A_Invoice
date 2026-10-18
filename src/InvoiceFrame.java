import javax.swing.*;
import java.awt.*;

public class InvoiceFrame extends JFrame {
    private Invoice invoice;

    private JTextArea addressArea;
    private JTextArea displayArea;
    private JTextField nameField;
    private JTextField priceField;
    private JTextField qtyField;
    private JButton addButton;
    private JButton showButton;
    private JButton clearButton;

    public InvoiceFrame() {
        setTitle("Lab07A Invoice");
        setSize(550, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        add(createAddressPanel(), BorderLayout.NORTH);
        add(createDisplayPanel(), BorderLayout.CENTER);
        add(createEntryPanel(), BorderLayout.SOUTH);
    }

    private JPanel createAddressPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Customer Address"));
        addressArea = new JTextArea(3, 30);
        panel.add(new JScrollPane(addressArea), BorderLayout.CENTER);
        return panel;
    }

    private JPanel createDisplayPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Invoice"));
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
        panel.add(new JScrollPane(displayArea), BorderLayout.CENTER);
        return panel;
    }

    private JPanel createEntryPanel() {
        JPanel fields = new JPanel(new FlowLayout());
        nameField = new JTextField(12);
        priceField = new JTextField(6);
        qtyField = new JTextField(4);
        fields.add(new JLabel("Item:"));
        fields.add(nameField);
        fields.add(new JLabel("Price:"));
        fields.add(priceField);
        fields.add(new JLabel("Qty:"));
        fields.add(qtyField);

        JPanel buttons = new JPanel(new FlowLayout());
        addButton = new JButton("Add Item");
        showButton = new JButton("Show Invoice");
        clearButton = new JButton("New Invoice");
        buttons.add(addButton);
        buttons.add(showButton);
        buttons.add(clearButton);

        addButton.addActionListener(e -> addItem());
        showButton.addActionListener(e -> showInvoice());
        clearButton.addActionListener(e -> newInvoice());

        JPanel panel = new JPanel(new GridLayout(2, 1));
        panel.add(fields);
        panel.add(buttons);
        return panel;
    }

    private void addItem() {
        String name = nameField.getText().trim();
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter an item name.");
            return;
        }
        try {
            double price = Double.parseDouble(priceField.getText().trim());
            int qty = Integer.parseInt(qtyField.getText().trim());

            if (invoice == null) {
                invoice = new Invoice("INVOICE", addressArea.getText());
            }
            invoice.addItem(new LineItem(new Product(name, price), qty));

            nameField.setText("");
            priceField.setText("");
            qtyField.setText("");
            nameField.requestFocus();
            showInvoice();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Price must be a number and Qty must be a whole number.");
        }
    }

    private void showInvoice() {
        if (invoice == null) {
            JOptionPane.showMessageDialog(this, "Add at least one item first.");
            return;
        }
        String line = "==================================================\n";
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("%30s%n%n", invoice.getTitle()));
        sb.append(addressArea.getText()).append("\n\n");
        sb.append(line);
        sb.append(String.format("%-24s%6s%10s%10s%n", "Item", "Qty", "Price", "Total"));
        for (LineItem item : invoice.getItems()) {
            sb.append(String.format("%-24s%6d%10.2f%10.2f%n",
                    item.getProduct().getName(),
                    item.getQuantity(),
                    item.getProduct().getPrice(),
                    item.getTotal()));
        }
        sb.append(line);
        sb.append(String.format("AMOUNT DUE: $%.2f%n", invoice.getAmountDue()));

        displayArea.setText(sb.toString());
    }

    private void newInvoice() {
        invoice = null;
        addressArea.setText("");
        displayArea.setText("");
    }
}
