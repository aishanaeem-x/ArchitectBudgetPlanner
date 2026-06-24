package main;
import javax.swing.SwingUtilities;
import ui.Mainframe;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Mainframe());
    }
}
