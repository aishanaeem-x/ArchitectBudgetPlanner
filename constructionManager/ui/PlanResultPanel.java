package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import model.BudgetAllocation;
import model.plan;
import java.util.List;

public class PlanResultPanel extends JPanel {
    private Mainframe frame;
    private JLabel clientNameLabel;
    private JLabel budgetLabel;
    private JLabel tierLabel;
    private JLabel dateLabel;
    private JTable allocationTable;
    private DefaultTableModel tableModel;
    private JLabel verdictLabel;
    private JButton homeButton;

    public PlanResultPanel(Mainframe frame) {
        this.frame = frame;
        // CHANGED: beige background
        setBackground(new Color(245, 235, 220));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(Box.createRigidArea(new Dimension(0, 20)));

        clientNameLabel = new JLabel("Client: ");
        clientNameLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        clientNameLabel.setForeground(new Color(80, 40, 10));
        clientNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(clientNameLabel);

        this.add(Box.createRigidArea(new Dimension(0, 6)));

        budgetLabel = new JLabel("Total Budget: ");
        budgetLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        budgetLabel.setForeground(new Color(100, 60, 20));
        budgetLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(budgetLabel);

        tierLabel = new JLabel("Tier: ");
        tierLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        tierLabel.setForeground(new Color(100, 60, 20));
        tierLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(tierLabel);

        dateLabel = new JLabel("Date: ");
        dateLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        dateLabel.setForeground(new Color(100, 60, 20));
        dateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(dateLabel);

        this.add(Box.createRigidArea(new Dimension(0, 15)));

        String[] columns = {"Category", "Amount (Rs.)", "%", "Material Recommendation"};
        tableModel = new DefaultTableModel(columns, 0) {
            // CHANGED: make table cells non-editable
            public boolean isCellEditable(int row, int col) { return false; }
        };
        allocationTable = new JTable(tableModel);
        allocationTable.setFont(new Font("SansSerif", Font.PLAIN, 13));
        allocationTable.setRowHeight(26);
        // CHANGED: table colors to match beige theme
        allocationTable.setBackground(new Color(255, 250, 240));
        allocationTable.setForeground(new Color(60, 30, 5));
        allocationTable.setGridColor(new Color(200, 170, 130));
        allocationTable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 13));
        allocationTable.getTableHeader().setBackground(new Color(139, 69, 19));
        allocationTable.getTableHeader().setForeground(Color.WHITE);
        // CHANGED: alternating row colors
        allocationTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable t, Object val, boolean sel, boolean foc, int row, int col) {
                super.getTableCellRendererComponent(t, val, sel, foc, row, col);
                if (!sel) {
                    setBackground(row % 2 == 0 ? new Color(255, 250, 240) : new Color(240, 225, 200));
                    setForeground(new Color(60, 30, 5));
                }
                return this;
            }
        });

        JScrollPane scrollPane = new JScrollPane(allocationTable);
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        scrollPane.setMaximumSize(new Dimension(680, 260));
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(139, 69, 19), 1));
        this.add(scrollPane);

        this.add(Box.createRigidArea(new Dimension(0, 15)));

        verdictLabel = new JLabel("Verdict: ");
        verdictLabel.setFont(new Font("SansSerif", Font.ITALIC, 14));
        verdictLabel.setForeground(new Color(80, 40, 10));
        verdictLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(verdictLabel);

        this.add(Box.createRigidArea(new Dimension(0, 20)));

        homeButton = new JButton("Save & Return Home");
        homeButton.setFont(new Font("SansSerif", Font.PLAIN, 16));
        homeButton.setBackground(new Color(139, 69, 19));
        homeButton.setForeground(Color.WHITE);
        homeButton.setOpaque(true);
        homeButton.setBorderPainted(false);
        homeButton.setPreferredSize(new Dimension(200, 42));
        homeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        homeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        homeButton.setFocusPainted(false);
        this.add(homeButton);

        homeButton.addActionListener(e -> frame.showPanel("home"));
    }

    public void loadPlan(plan plan) {
        clientNameLabel.setText("Client: " + plan.getClientName());
        // CHANGED: format budget with commas for readability
        budgetLabel.setText("Total Budget: Rs. " + String.format("%,.0f", plan.getTotalBudget()));
        tierLabel.setText("Tier: " + plan.getTier());
        dateLabel.setText("Date: " + plan.getDate());

        tableModel.setRowCount(0);
        List<BudgetAllocation> allocations = plan.getAllocations();
        for (BudgetAllocation a : allocations) {
            tableModel.addRow(new Object[]{
                a.getCategoryName(),
                "Rs. " + String.format("%,.0f", a.getAllocatedAmount()),
                (int)a.getPercentage() + "%",
                a.getMaterialRecommendation()
            });
        }

        verdictLabel.setText("<html><i>" + plan.getVerdict() + "</i></html>");
    }
}
