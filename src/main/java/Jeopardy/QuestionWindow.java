package Jeopardy;

import javax.swing.*;

public class QuestionWindow extends JWindow {

    private JWindow jWindow;

    public QuestionWindow(Question q){
        JTextArea Title = new JTextArea(q.getQuestion());
        for(Option o:q.getOptions()){
            JButton optionBtn = new JButton(o.getContent());
            jWindow.add(optionBtn);
        }
    }
}
