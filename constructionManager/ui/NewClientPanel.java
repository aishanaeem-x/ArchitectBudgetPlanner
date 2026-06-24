package ui;
import model.*;
import service.*;
import javax.swing.*;
import java.awt.*;
import model.Client;

public class NewClientPanel extends JPanel {
    private Mainframe frame;
    private ClientService clientService;
    private JTextField nameField;
    private JTextField contactField;
    private JButton submitButton;

    public NewClientPanel(Mainframe frame, ClientService clientService) {
        this.frame = frame;
        this.clientService = clientService;

        setBackground(new Color(245, 235, 220));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(Box.createRigidArea(new Dimension(0, 60)));

        JLabel heading = new JLabel("New Client");
        heading.setFont(new Font("SansSerif", Font.BOLD, 24));
        heading.setForeground(new Color(80, 40, 10));
        heading.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(heading);

        this.add(Box.createRigidArea(new Dimension(0, 30)));

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setFont(new Font("SansSerif", Font.PLAIN, 15));
        nameLabel.setForeground(new Color(80, 40, 10));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(nameLabel);

        this.add(Box.createRigidArea(new Dimension(0, 5)));

        nameField = new JTextField();
        nameField.setFont(new Font("SansSerif", Font.PLAIN, 15));
        nameField.setBackground(new Color(255, 250, 240));
        nameField.setForeground(Color.BLACK);
        nameField.setBorder(BorderFactory.createLineBorder(new Color(139, 69, 19), 1));
        nameField.setMaximumSize(new Dimension(280, 35));
        nameField.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(nameField);

        this.add(Box.createRigidArea(new Dimension(0, 18)));

        JLabel contactLabel = new JLabel("Contact (11 digits, starting with 03)");
        contactLabel.setFont(new Font("SansSerif", Font.PLAIN, 15));
        contactLabel.setForeground(new Color(80, 40, 10));
        contactLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(contactLabel);

        this.add(Box.createRigidArea(new Dimension(0, 5)));

        contactField = new JTextField();
        contactField.setFont(new Font("SansSerif", Font.PLAIN, 15));
        contactField.setBackground(new Color(255, 250, 240));
        contactField.setForeground(Color.BLACK);
        contactField.setBorder(BorderFactory.createLineBorder(new Color(139, 69, 19), 1));
        contactField.setMaximumSize(new Dimension(280, 35));
        contactField.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(contactField);

        this.add(Box.createRigidArea(new Dimension(0, 30)));

        submitButton = new JButton("Next →");
        submitButton.setFont(new Font("SansSerif", Font.PLAIN, 16));
        submitButton.setBackground(new Color(139, 69, 19));
        submitButton.setForeground(Color.WHITE);
        submitButton.setOpaque(true);
        submitButton.setBorderPainted(false);
        submitButton.setPreferredSize(new Dimension(160, 42));
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        submitButton.setFocusPainted(false);
        this.add(submitButton);

        submitButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String contact = contactField.getText().trim();

            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Name cannot be empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // contact validation — must be 11 digits starting with 03
            if (!contact.matches("03\\d{9}")) {
                JOptionPane.showMessageDialog(this,
                    "Contact must be 11 digits and start with 03 (e.g. 03001234567).",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            clientService.addClient(name, contact);

            Client newClient = clientService.getAllClients()
                    .get(clientService.getAllClients().size() - 1);

            frame.setCurrentClient(newClient);
            frame.showPanel("Budget Input");
        });
    }
    @Override
    public void setVisible(boolean visible) {
      if (visible) {
        nameField.setText("");
        contactField.setText("");
      }
    super.setVisible(visible);
    }
}
