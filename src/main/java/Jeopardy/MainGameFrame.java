package Jeopardy;

import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import javax.swing.*;

public class MainGameFrame extends JFrame implements ScoreComponentLink {

    private JPanel mainPanel;
    private JPanel mainGamePanel;
    private List<CategoryPanel> categoryPanels;

    public MainGameFrame(List<Category> categories, Observer gameManager){
        mainPanel = new JPanel(new BorderLayout());
        mainGamePanel = new JPanel(new GridLayout(1,5));
        setTitle("Jeopardy");
        setSize(1920,1080);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        categoryPanels = new ArrayList<CategoryPanel>();

        for(Category c:categories){
            CategoryPanel cPanel = new CategoryPanel(c);
            mainGamePanel.add(cPanel);
            categoryPanels.add(cPanel);
        }

        mainPanel.add(mainGamePanel,BorderLayout.CENTER);

        add(mainPanel);

        setVisible(true);

        LinkObserver(gameManager);
    }


    @Override
    public void LinkObserver(Observer o) {
        for(CategoryPanel cPanel:this.categoryPanels) {
            cPanel.LinkObserver(o);
        }
    }
}