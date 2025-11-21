package Jeopardy;

import javax.swing.*;

public class OptionButton implements ScoreSubject, ButtonLockSubject {

    private JButton oBtn;
    private boolean correct;
    private ScoreObserver gM;
    private ButtonLockObserver button;
    private int score;

    public OptionButton(Option o, QuestionWindow qW){
        oBtn = new JButton(o.getContent());
        score = qW.getScore();
        oBtn.addActionListener(e ->{
            if(this.correct == true)
                System.out.println("CORRECT");
            else
                System.out.println("WRONG");
            UpdateObserverScore(this.correct);
            UpdateLockObserver(button);
            qW.getDialog().dispose();
        });
    }

    public JButton getButton(){
        return oBtn;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    @Override
    public void LinkObserver(Observer o) {
        this.gM = (ScoreObserver) o;
    }

    public void LinkLockObserver(Observer o){
        this.button = (ButtonLockObserver) o;
    }

    @Override
    public void UnlinkObserver(Observer o) {

    }

    @Override
    public void UpdateObservers() {

    }

    @Override
    public void UpdateObserverScore(boolean correct) {
        if(correct)
            this.gM.UpdateScore(score);
        else
            this.gM.UpdateScore(score * -1);
    }

    @Override
    public void UpdateLockObserver(Observer o) {
        o.Update();
    }
}
