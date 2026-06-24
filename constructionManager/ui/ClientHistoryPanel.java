package ui;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import model.Client;
import model.plan;
import service.ClientService;
import service.PlanService;

public class ClientHistoryPanel extends JPanel {
    private Mainframe frame;
    private ClientService clientService;
    private PlanService planService;
    private DefaultListModel<String> clientListModel;
    private JList<String> clientList;
    private JTextArea planDetails;
    private JButton backButton;

    public ClientHistoryPanel(Mainframe frame, ClientService clientService, PlanService planService) {
        this.frame = frame;
        this.clientService = clientService;
        this.planService = planService;
        setBackground(new Color(245, 235, 220));
        setLayout(new BorderLayout());

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(245, 235, 220));
        JLabel title = new JLabel("Client History");
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        title.setForeground(new Color(80, 40, 10));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        titlePanel.add(title);
        this.add(titlePanel, BorderLayout.NORTH);

        clientListModel = new DefaultListModel<>();
        clientList = new JList<>(clientListModel);
        clientList.setFont(new Font("SansSerif", Font.PLAIN, 14));
        clientList.setBackground(new Color(255, 250, 240));
        clientList.setForeground(new Color(60, 30, 5));
        clientList.setSelectionBackground(new Color(139, 69, 19));
        clientList.setSelectionForeground(Color.WHITE);
        clientList.setFixedCellHeight(30);
        JScrollPane listScroll = new JScrollPane(clientList);
        listScroll.setPreferredSize(new Dimension(340, 400));
        listScroll.setBorder(BorderFactory.createLineBorder(new Color(139, 69, 19), 1));
        this.add(listScroll, BorderLayout.WEST);

        planDetails = new JTextArea();
        planDetails.setFont(new Font("Monospaced", Font.PLAIN, 13));
        planDetails.setBackground(new Color(255, 250, 240));
        planDetails.setForeground(new Color(60, 30, 5));
        planDetails.setEditable(false);
        planDetails.setLineWrap(true);
        planDetails.setWrapStyleWord(true);
        planDetails.setMargin(new Insets(10, 10, 10, 10));
        JScrollPane detailScroll = new JScrollPane(planDetails);
        detailScroll.setBorder(BorderFactory.createLineBorder(new Color(139, 69, 19), 1));
        this.add(detailScroll, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(245, 235, 220));
        backButton = new JButton("← Back to Home");
        backButton.setFont(new Font("SansSerif", Font.PLAIN, 15));
        backButton.setBackground(new Color(139, 69, 19));
        backButton.setForeground(Color.WHITE);
        backButton.setOpaque(true);
        backButton.setBorderPainted(false);
        backButton.setPreferredSize(new Dimension(180, 40));
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backButton.setFocusPainted(false);
        bottomPanel.add(backButton);
        this.add(bottomPanel, BorderLayout.SOUTH);

        backButton.addActionListener(e -> frame.showPanel("home"));

        clientList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int index = clientList.getSelectedIndex();
                if (index >= 0) {
                    Client selected = clientService.getAllClients().get(index);
                    List<plan> plans = planService.getPlansByClientId(selected.getClientID());
                    StringBuilder sb = new StringBuilder();
                    if (plans.isEmpty()) {
                        sb.append("No plans found for this client.");
                    }
                    for (plan p : plans) {
                        sb.append("Date:    ").append(p.getDate()).append("\n");
                        // CHANGED: format budget with commas
                        sb.append("Budget:  Rs. ").append(String.format("%,.0f", p.getTotalBudget())).append("\n");
                        sb.append("Type:    ").append(p.getConstructionType()).append("\n");
                        sb.append("Tier:    ").append(p.getTier()).append("\n");
                        sb.append("Verdict: ").append(p.getVerdict()).append("\n");
                        sb.append("─────────────────────────────────\n");
                    }
                    planDetails.setText(sb.toString());
                }
            }
        });
    }

    public void refreshClients() {
        clientListModel.clear();
        for (Client c : clientService.getAllClients()) {
            List<plan> plans = planService.getPlansByClientId(c.getClientID());
            if (plans.isEmpty()) {
                clientListModel.addElement(c.getName() + " — no plans yet");
            } else {
                plan latest = plans.get(plans.size() - 1);
                clientListModel.addElement(c.getName() + "  |  " + latest.getDate() + "  |  Rs. " + String.format("%,.0f", latest.getTotalBudget()));
            }
        }
        planDetails.setText("");
    }
}
