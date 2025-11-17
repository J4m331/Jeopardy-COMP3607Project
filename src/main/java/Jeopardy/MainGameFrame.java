package Jeopardy;

import java.util.List;
import java.awt.*;
import javax.swing.*;

public class MainGameFrame extends JFrame{

    private JPanel mainPanel;
    private final GameEngine engine;

    public MainGameFrame(List<Category> categories, List<Player> players){
        mainPanel = new JPanel(new GridLayout(1,categories.size()));
        setTitle("Jeopardy");
        setSize(1920,1080);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        engine = new GameEngine(players, categories);

        for(Category c:categories){
            CategoryPanel cPanel = new CategoryPanel(c, engine);
            mainPanel.add(cPanel);
        }

        add(mainPanel);

        // bottom controls: show current player, generate report, exit
        JPanel controls = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton reportBtn = new JButton("Generate Report & Exit");
        
        reportBtn.addActionListener(e -> {
            engine.generateReportAndFinish();
            System.exit(0);
        });
        
        controls.add(reportBtn);

        setVisible(true);
    }
}
