package Jeopardy;

import java.util.List;
import java.awt.*;
import javax.swing.*;

public class MainGameFrame extends JFrame{

    private JPanel mainPanel;

    public MainGameFrame(List<Category> categories){
        mainPanel = new JPanel(new GridLayout(1,5));
        setTitle("Jeopardy");
        setSize(1920,1080);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        for(Category c:categories){
            CategoryPanel cPanel = new CategoryPanel(c);
            mainPanel.add(cPanel);
        }

        add(mainPanel);

        setVisible(true);
    }
}
