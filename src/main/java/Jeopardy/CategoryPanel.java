package Jeopardy;

import javax.swing.*;
import java.awt.*;

public class CategoryPanel extends JPanel{

    private JPanel panel = new JPanel(new GridLayout(6,1));

    public CategoryPanel(Category c){
        JTextArea categoryTitle = new JTextArea(c.getName());

        panel.add(categoryTitle);

        for(Question q:c.getQuestions()){
            JButton questionBtn = new JButton(q.getScore()+"");
            panel.add(questionBtn);
        }
        add(panel);
    }

}
