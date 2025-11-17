package Jeopardy;

import java.util.List;
import java.awt.*;
import javax.swing.*;

public class MainGameFrame extends JFrame{

    private JPanel mainPanel;
    private final GameEngine engine;
    private JLabel currentPlayerLabel;
    private JLabel scoresLabel;


    public MainGameFrame(List<Category> categories, List<Player> players){
        mainPanel = new JPanel(new GridLayout(1,categories.size()));
        setTitle("Jeopardy");
        setSize(1920,1080);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        engine = new GameEngine(players, categories);
        engine.setFrame(this);

        for(Category c:categories){
            CategoryPanel cPanel = new CategoryPanel(c, engine);
            mainPanel.add(cPanel);
        }

        add(mainPanel);

        JPanel statusPanel = new JPanel(new GridLayout(2,1));
        currentPlayerLabel = new JLabel();
        scoresLabel = new JLabel();

        statusPanel.add(currentPlayerLabel);
        statusPanel.add(scoresLabel);

        add(statusPanel, BorderLayout.SOUTH);

        updateStatusDisplay(); 

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

    public void updateStatusDisplay() {
        Player p = engine.getCurrentPlayer();

        currentPlayerLabel.setText("Current Player: " + p.getName());

        StringBuilder sb = new StringBuilder("Scores: ");
        for (Player player : engine.getPlayers()) {
            sb.append(player.getName())
            .append("=")
            .append(player.getScore())
            .append("   ");
        }

        scoresLabel.setText(sb.toString());
    }

}
