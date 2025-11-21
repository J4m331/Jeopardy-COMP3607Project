package Jeopardy;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionWindow implements ScoreComponentLink, ButtonLockLink{
    private JDialog dialog;
    private List<OptionButton> options;
    private int score;

    public QuestionWindow(Question q){
        //frame to hold dialog
        JFrame frame = new JFrame();
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setSize(300,300);
        frame.setVisible(false);

        dialog = new JDialog(frame, "", true);
        dialog.setSize(500,500);
        dialog.setLayout(new GridLayout(5,1));
        dialog.setLocationRelativeTo(null);

        dialog.add(new JLabel(q.getQuestion()));
        score = q.getScore();

        options = new ArrayList<OptionButton>();

        for(Option o:q.getOptions()){
            OptionButton option = new OptionButton(o,this);
            if(o.getHeader() == q.getCorrectAnswer())
                option.setCorrect(true);
            dialog.add(option.getButton());
            options.add(option);
        }

        //dialog.setVisible(true);
    }

    public void show(){
        dialog.setVisible(true);
    }

    public JDialog getDialog(){
        return this.dialog;
    }

    public int getScore(){
        return this.score;
    }

    @Override
    public void LinkObserver(Observer o) {
        for(OptionButton oB:options)
            oB.LinkObserver(o);
    }

    @Override
    public void LinkLockObserver(Observer o) {
        for(OptionButton oB:options)
            oB.LinkLockObserver(o);
    }
}
