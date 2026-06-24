package ui;
import javax.swing.*;

import model.*;

import java.awt.*;
import service.*;

public class Mainframe extends JFrame{
    CardLayout cardLayout;
    JPanel mainPanel;
    private Client currentClient;
    private plan currentPlan;

    HomePanel homePanel;
    NewClientPanel newClientPanel;
    PlanResultPanel planResultPanel;
    ClientHistoryPanel clientHistoryPanel;
    BudgetInputPanel budgetInputPanel;

    ClientService clientService;
    PlanService planService;
    BudgetCalculator budgetCalculator; 

    public Mainframe(){
        super("ArchitectBudgetPlanner");
        setSize(800, 350);
        setMinimumSize(new Dimension(700, 580));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        clientService= new ClientService();
        planService= new PlanService();
        budgetCalculator= new BudgetCalculator();
        cardLayout= new CardLayout();
        mainPanel=new JPanel(cardLayout);

        homePanel=new HomePanel(this);
        newClientPanel= new NewClientPanel(this,clientService);
        budgetInputPanel= new BudgetInputPanel(this,budgetCalculator,planService);
        planResultPanel= new PlanResultPanel(this);
        clientHistoryPanel=new ClientHistoryPanel(this,clientService,planService);
    
        mainPanel.add("home", homePanel);
        mainPanel.add("New Client", newClientPanel);
        mainPanel.add("Budget Input", budgetInputPanel);
        mainPanel.add("planResult", planResultPanel);
        mainPanel.add("history", clientHistoryPanel);

        this.add(mainPanel);
        setVisible(true);
    }
    
    public void showPanel(String name) {
      if (name.equals("planResult")) {
        planResultPanel.loadPlan(currentPlan);
        }
       if (name.equals("history")) {
        clientHistoryPanel.refreshClients();
        }
    cardLayout.show(mainPanel, name);
    }
    public void setCurrentClient(Client client) {
    this.currentClient = client;
    }
    public Client getCurrentClient() {
    return currentClient;
    }
    public void setCurrentPlan(plan plan) {
    this.currentPlan = plan;
    }

    public plan getCurrentPlan() {
    return currentPlan;
    }

}
