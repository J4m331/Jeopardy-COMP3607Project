package Jeopardy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Modal dialog that shows question and options and handles player's answer.
 */
public class QuestionDialog extends JDialog {
    public QuestionDialog(Window owner, Question q, Category c, GameEngine engine, JButton questionBtn){
        super(owner, "Question", ModalityType.APPLICATION_MODAL);
        setSize(600,300);
        setLocationRelativeTo(owner);
        setLayout(new BorderLayout(8,8));

        JTextArea qText = new JTextArea(q.getQuestion());
        qText.setLineWrap(true);
        qText.setWrapStyleWord(true);
        qText.setEditable(false);
        qText.setFont(new Font("Serif", Font.PLAIN, 16));
        add(new JScrollPane(qText), BorderLayout.CENTER);

        JPanel opts = new JPanel(new GridLayout(2,2,6,6));
        ButtonGroup group = new ButtonGroup();
        for(Option o : q.getOptions()){
            JRadioButton rb = new JRadioButton(o.getHeader() + ": " + o.getContent());
            rb.setActionCommand(String.valueOf(o.getHeader()));
            group.add(rb);
            opts.add(rb);
        }
        add(opts, BorderLayout.SOUTH);

        JPanel top = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton submit = new JButton("Submit");
        JButton cancel = new JButton("Cancel");
        top.add(submit);
        top.add(cancel);
        add(top, BorderLayout.NORTH);

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sel = group.getSelection() == null ? "" : group.getSelection().getActionCommand();
                if (sel.isEmpty()){
                    JOptionPane.showMessageDialog(QuestionDialog.this, "Please pick an option.", "No Selection", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                Player current = engine.getCurrentPlayer();
                engine.executePlay(current, c, q, sel.charAt(0));
                // disable the origin question button so it can't be reused
                questionBtn.setEnabled(false);
                dispose();
            }
        });

        cancel.addActionListener(a -> dispose());
    }
}
