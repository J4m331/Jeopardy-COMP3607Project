package Jeopardy;

import javax.swing.*;

public class QuestionButton implements ScoreComponentLink, ButtonLockObserver{

    private JButton qBtn;
    private QuestionWindow qW;

    public QuestionButton(Question q){
        qBtn = new JButton(q.getScore()+"");
        this.qW = new QuestionWindow(q);
        LinkLockObserver(this);
        qBtn.addActionListener(e ->{
            this.qW.show();
        });
    }

    public JButton getButton(){
        return qBtn;
    }

    @Override
    public void LinkObserver(Observer o) {
        this.qW.LinkObserver(o);
    }

    public void LinkLockObserver(Observer o){
        this.qW.LinkLockObserver(o);
    }

    @Override
    public void Update() {
        this.qBtn.setEnabled(false);
    }
}
