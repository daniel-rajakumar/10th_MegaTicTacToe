package app.simpleVerson;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Date: Feb 15th, 2020
 * game
 */
public class Game implements ActionListener {
    private JFrame frame;
    private JPanel panel;
    private JButton[] table = new JButton[9];
    private JLabel label;
    private int count;

    private int width, height;
    boolean play = true;

    public Game() {
        GUI();
    }

    public static void main(String[] args) {
        Game foo = new Game();
    }

    public void GUI() {
        frame = new JFrame();
        frame.setSize(450, 450);
        // frame.setSize(1550,850);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        width = frame.getWidth() - 13;
        height = frame.getHeight() - 34;
        
        panel = new JPanel();
        panel.setLayout(null);

        label = new JLabel("tic-tac-toe");
        label.setBounds(10, 20, 80, 25);

        for (int i = 0; i < table.length; i++) {
            table[i] = new JButton(" ");
        }

        for (int i = 0; i < table.length; i++) {// prints out all of the buttons(main)
            table[i].setBackground(Color.BLACK);
            table[i].setFont(new Font("SansSerif", Font.BOLD, (width / 5)));
            table[i].setBounds((width / 3) * (i % 3), (height / 3) * (i / 3), (width / 3), (height / 3));
            table[i].addActionListener((ActionListener)this);
        }

        System.out.println(width);

        for (int i = 0; i < table.length; i++) {// adding all the buttons to the panel
            panel.add(table[i]);
        }
        // panel.add(label);
        frame.add(panel);
        frame.setBackground(Color.RED);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String turn;
        Color textColor;
        String x = "x", Player_1 = x;
        String o = "o", Player_2 = o;
        String[] label = new String[9];
        count++;
        
        if ((count%2) == 1) {
            turn = x;
            textColor = Color.RED;
        }else {
            turn = o;
            textColor = Color.BLUE;
        }
        
        for (int i = 0; i < table.length; i++) {
            label[i] = table[i].getText();
        
            
        }
        
        //checking the winner
        //for (int i = 0; i < table.length; i++) {
            //left
            if (label[0].equals(x) || label[0].equals(o)) {
                if (check_winner(label[0], label[1], label[2]))    play = false;
                if (check_winner(label[0], label[3], label[6]))    play = false;
            }
            
            //center
            if (label[4].equals(x) || label[4].equals(o)) {
                if (check_winner(label[0], label[4], label[8]))    play = false;
                if (check_winner(label[2], label[4], label[6]))    play = false;
                if (check_winner(label[1], label[4], label[7]))    play = false;
                if (check_winner(label[3], label[4], label[5]))    play = false;
            }
            
            //right
            if (label[8].equals(x) || label[8].equals(o)) {
                if (check_winner(label[2], label[5], label[8]))    play = false;
                if (check_winner(label[6], label[7], label[8]))    play = false;
            }
        //}
        
        if (!(count > 99) && play) {
            for (int i = 0; i < table.length; i++) {
                if (e.getSource() == table[i]) {
                    table[i].setText(turn);
                    table[i].setForeground(textColor);
                }//ends if
            }//ends forloop
        }        
        
        if (!play) System.out.println("Game Over ");
            
    }//ends actionperformed
    
    public boolean check_winner(String a, String b, String c) {
        boolean winner = false;
        if ((a == b) && (b == c) && (a == c)) {
            winner = true;
        }
        return winner;
    }
}//ends main class