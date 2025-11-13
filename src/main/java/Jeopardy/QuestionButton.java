package Jeopardy;

import javax.swing.*;

public class QuestionButton extends JButton {

    private JButton qBtn;

    public QuestionButton(Question q){
        qBtn = new JButton(q.getScore()+"");
    }
}
