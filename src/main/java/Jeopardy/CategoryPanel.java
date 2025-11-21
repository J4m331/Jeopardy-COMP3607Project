package Jeopardy;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class CategoryPanel extends JPanel implements ScoreComponentLink{

    private JPanel panel = new JPanel(new GridLayout(6,1));
    private List<QuestionButton> questionButtons;

    public CategoryPanel(Category c){
        JTextArea categoryTitle = new JTextArea(c.getName());
        questionButtons = new ArrayList<QuestionButton>();
        panel.add(categoryTitle);

        for(Question q:c.getQuestions()){
            QuestionButton questionBtn = new QuestionButton(q);
            panel.add(questionBtn.getButton());
            questionButtons.add(questionBtn);
        }
        add(panel);
    }

    @Override
    public void LinkObserver(Observer o) {
        for(QuestionButton qBtn:this.questionButtons)
            qBtn.LinkObserver(o);
    }
}