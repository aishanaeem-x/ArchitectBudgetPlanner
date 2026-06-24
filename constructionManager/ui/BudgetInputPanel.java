package ui;

import javax.swing.*;
import java.awt.*;
import model.Client;
import model.plan;
import service.BudgetCalculator;
import service.PlanService;

public class BudgetInputPanel extends JPanel {
    private Mainframe frame;
    private BudgetCalculator calculatorService;
    private PlanService planService;
    private JTextField budgetField;
    private JTextField bedroomsField;
    private JTextField bathroomsField;
    private JComboBox<String> constructionTypeBox;
    private JButton submitButton;

    public BudgetInputPanel(Mainframe frame, BudgetCalculator calculatorService, PlanService planService) {
        this.frame = frame;
        this.calculatorService = calculatorService;
        this.planService = planService;

        setBackground(new Color(245, 235, 220));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(Box.createRigidArea(new Dimension(0, 40)));

        JLabel heading = new JLabel("Budget & Requirements");
        heading.setFont(new Font("SansSerif", Font.BOLD, 24));
        heading.setForeground(new Color(80, 40, 10));
        heading.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(heading);

        this.add(Box.createRigidArea(new Dimension(0, 25)));

        // Budget field
        JLabel budgetLabel = new JLabel("Total Budget (Rs.)  —  min Rs. 1,000,000");
        budgetLabel.setFont(new Font("SansSerif", Font.PLAIN, 15));
        budgetLabel.setForeground(new Color(80, 40, 10));
        budgetLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(budgetLabel);
        this.add(Box.createRigidArea(new Dimension(0, 5)));

        budgetField = new JTextField();
        budgetField.setFont(new Font("SansSerif", Font.PLAIN, 15));
        budgetField.setBackground(new Color(255, 250, 240));
        budgetField.setBorder(BorderFactory.createLineBorder(new Color(139, 69, 19), 1));
        budgetField.setMaximumSize(new Dimension(280, 35));
        budgetField.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(budgetField);

        this.add(Box.createRigidArea(new Dimension(0, 15)));

        // Bedrooms
        JLabel bedroomsLabel = new JLabel("Number of Bedrooms  (1 – 10)");
        bedroomsLabel.setFont(new Font("SansSerif", Font.PLAIN, 15));
        bedroomsLabel.setForeground(new Color(80, 40, 10));
        bedroomsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(bedroomsLabel);
        this.add(Box.createRigidArea(new Dimension(0, 5)));

        bedroomsField = new JTextField();
        bedroomsField.setFont(new Font("SansSerif", Font.PLAIN, 15));
        bedroomsField.setBackground(new Color(255, 250, 240));
        bedroomsField.setBorder(BorderFactory.createLineBorder(new Color(139, 69, 19), 1));
        bedroomsField.setMaximumSize(new Dimension(280, 35));
        bedroomsField.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(bedroomsField);

        this.add(Box.createRigidArea(new Dimension(0, 15)));

        // Bathrooms
        JLabel bathroomsLabel = new JLabel("Number of Bathrooms  (1 – 5)");
        bathroomsLabel.setFont(new Font("SansSerif", Font.PLAIN, 15));
        bathroomsLabel.setForeground(new Color(80, 40, 10));
        bathroomsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(bathroomsLabel);
        this.add(Box.createRigidArea(new Dimension(0, 5)));

        bathroomsField = new JTextField();
        bathroomsField.setFont(new Font("SansSerif", Font.PLAIN, 15));
        bathroomsField.setBackground(new Color(255, 250, 240));
        bathroomsField.setBorder(BorderFactory.createLineBorder(new Color(139, 69, 19), 1));
        bathroomsField.setMaximumSize(new Dimension(280, 35));
        bathroomsField.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(bathroomsField);

        this.add(Box.createRigidArea(new Dimension(0, 15)));

        JLabel constructionLabel = new JLabel("Construction Type");
        constructionLabel.setFont(new Font("SansSerif", Font.PLAIN, 15));
        constructionLabel.setForeground(new Color(80, 40, 10));
        constructionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(constructionLabel);
        this.add(Box.createRigidArea(new Dimension(0, 5)));

        constructionTypeBox = new JComboBox<>(new String[]{"Residential", "Commercial", "Standard"});
        constructionTypeBox.setFont(new Font("SansSerif", Font.PLAIN, 15));
        constructionTypeBox.setMaximumSize(new Dimension(280, 35));
        constructionTypeBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(constructionTypeBox);

        this.add(Box.createRigidArea(new Dimension(0, 30)));

        submitButton = new JButton("Generate Plan");
        submitButton.setFont(new Font("SansSerif", Font.PLAIN, 16));
        submitButton.setBackground(new Color(139, 69, 19));
        submitButton.setForeground(Color.WHITE);
        submitButton.setOpaque(true);
        submitButton.setBorderPainted(false);
        submitButton.setPreferredSize(new Dimension(180, 42));
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        submitButton.setFocusPainted(false);
        this.add(submitButton);

        submitButton.addActionListener(e -> {
            try {
                double budget = Double.parseDouble(budgetField.getText().trim());
                if (budget < 100000) {
                    JOptionPane.showMessageDialog(this,
                        "Budget must be at least Rs. 100,000.",
                        "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int bedrooms = Integer.parseInt(bedroomsField.getText().trim());
                if (bedrooms < 1 || bedrooms > 10) {
                    JOptionPane.showMessageDialog(this,
                        "Number of bedrooms must be between 1 and 10.",
                        "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int bathrooms = Integer.parseInt(bathroomsField.getText().trim());
                if (bathrooms < 1 || bathrooms > 5) {
                    JOptionPane.showMessageDialog(this,
                        "Number of bathrooms must be between 1 and 5.",
                        "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String constructionType = (String) constructionTypeBox.getSelectedItem();
                Client client = frame.getCurrentClient();

                plan plan = calculatorService.calculate(client, budget, constructionType, bedrooms, bathrooms);
                planService.addPlan(plan);
                frame.setCurrentPlan(plan);
                frame.showPanel("planResult");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                    "Please enter valid numbers for budget, bedrooms, and bathrooms.",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
    @Override
    public void setVisible(boolean visible) {
      if (visible) {
        budgetField.setText("");
        bedroomsField.setText("");
        bathroomsField.setText("");
        constructionTypeBox.setSelectedIndex(0);
      }
    super.setVisible(visible);
    }
}
