package Jeopardy;

import javax.swing.*;
import java.awt.*;

public class CreatePlayerPromptWindow {
    private Player player;

    public CreatePlayerPromptWindow(int i){
        //frame to hold dialog
        JFrame frame = new JFrame();
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setSize(300,300);
        frame.setVisible(false);

        JDialog dialog = new JDialog(frame, "Jeopardy", true);
        dialog.setSize(300,150);
        dialog.setLayout(new GridLayout(5,1));
        dialog.setLocationRelativeTo(null);

        dialog.add(new JLabel("Enter your name"));
        JTextArea nameField = new JTextArea("Player");
        dialog.add(nameField);

        JButton submit = new JButton("Submit");
        submit.addActionListener(e -> {
            player = new Player(i + 1, nameField.getText());
            dialog.dispose();
        });

        dialog.add(submit);

        dialog.setVisible(true);

    }

    public Player getPlayer(){
        return player;
    }
}
