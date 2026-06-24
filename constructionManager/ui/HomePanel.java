package ui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HomePanel extends JPanel {
    
    private JLabel title, line;
    private JButton NewClient, ViewHistory;
    private JPanel buttonRow;

    public HomePanel(Mainframe frame){

      setBackground(new Color(245, 235, 220));
      setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

      this.add(Box.createRigidArea(new Dimension(0, 80)));

      title = new JLabel("ARCHITECT BUDGET PLANNER");
      title.setFont(new Font("SansSerif", Font.BOLD, 30));
      title.setForeground(new Color(80, 40, 10));
      title.setAlignmentX(Component.CENTER_ALIGNMENT);
      this.add(title);

      this.add(Box.createRigidArea(new Dimension(0, 15)));

      line = new JLabel("Build with Confidence");
      line.setFont(new Font("SansSerif", Font.ITALIC, 18));
      line.setForeground(new Color(139, 90, 43));
      line.setAlignmentX(Component.CENTER_ALIGNMENT);
      this.add(line);

      this.add(Box.createRigidArea(new Dimension(0, 40)));

      NewClient = new JButton("New Client");
      NewClient.setFont(new Font("SansSerif", Font.PLAIN, 16));
      NewClient.setBackground(new Color(139, 69, 19));
      NewClient.setForeground(Color.WHITE);
      NewClient.setPreferredSize(new Dimension(160, 42));
      NewClient.setOpaque(true);
      NewClient.setBorderPainted(false);
      NewClient.setCursor(new Cursor(Cursor.HAND_CURSOR));
      NewClient.setFocusPainted(false);
      
      ViewHistory = new JButton("View History");
      ViewHistory.setFont(new Font("SansSerif", Font.PLAIN, 16));
      ViewHistory.setBackground(new Color(139, 69, 19));
      ViewHistory.setForeground(Color.WHITE);
      ViewHistory.setPreferredSize(new Dimension(160, 42));
      ViewHistory.setOpaque(true);
      ViewHistory.setBorderPainted(false);
      ViewHistory.setCursor(new Cursor(Cursor.HAND_CURSOR));
      ViewHistory.setFocusPainted(false);

      buttonRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
      buttonRow.setBackground(new Color(245, 235, 220));
      buttonRow.add(NewClient);
      buttonRow.add(ViewHistory);
      buttonRow.setAlignmentX(Component.CENTER_ALIGNMENT);
      this.add(buttonRow);

      NewClient.addActionListener(e -> frame.showPanel("New Client"));
      ViewHistory.addActionListener(e -> frame.showPanel("history"));
    }
}
