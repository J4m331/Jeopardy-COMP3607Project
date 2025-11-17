package Jeopardy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CategoryPanel extends JPanel{

    private JPanel panel = new JPanel(new GridLayout(6,1));
    private final Category category;
    private final GameEngine engine;

    public CategoryPanel(Category c, GameEngine engine){
        this.category = c;
        this.engine = engine;

        JTextArea categoryTitle = new JTextArea(c.getName());

        panel.add(categoryTitle);

        for(Question q:c.getQuestions()){
            JButton questionBtn = new JButton(q.getScore()+"");
            questionBtn.setEnabled(!q.isAnswered());
            questionBtn.addActionListener(new QuestionButtonListener(q, questionBtn));
            panel.add(questionBtn);
        }
        add(panel);
    }

    private class QuestionButtonListener implements ActionListener {
        private final Question question;
        private final JButton sourceBtn;
        
        public QuestionButtonListener(Question q, JButton btn){
            this.question = q;
            this.sourceBtn = btn;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            if (question.isAnswered()) {
                sourceBtn.setEnabled(false);
                return;
            }
            // Open question dialog
            QuestionDialog dialog = new QuestionDialog(SwingUtilities.getWindowAncestor(CategoryPanel.this), question, category, engine, sourceBtn);
            dialog.setVisible(true);
        }
    }
}
