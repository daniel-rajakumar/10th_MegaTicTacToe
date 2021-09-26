package app.simpleVerson;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
*   Date: Feb 15th, 2020
 * game
 */
public class Mega_Game implements ActionListener {
    private JFrame frame;
    private JPanel panel;
    private JButton[][] table = new JButton[9][9];
    private JLabel label;
    private int count;
    private Color boxColor;

    private int width, height;
    boolean play[] = new boolean[9];
    boolean holdWinner[] = new boolean[9];
    boolean[] lmove = new boolean[9];

    public Mega_Game() {
        GUI();
    }

    public static void main(String[] args) {
        Mega_Game foo = new Mega_Game();
    }

    public void GUI() {
        int x,y;
        int bwidth,bheight;//button
        int lineBreak = 0;
        
        frame = new JFrame();
        frame.setSize(450, 450);
        frame.setSize(1550,850);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        width = frame.getWidth() - 15;
        height = frame.getHeight() - 38;
        
        
        panel = new JPanel();
        panel.setLayout(null);

        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                table[i][j] = new JButton("");
            }
        }

        for (int i = 0; i < play.length; i++) {
            play[i] = true;
        }
        
        for (int i = 0; i < holdWinner.length; i++) {
            holdWinner[i] = true;
        }



        for (int i = 0; i < table.length; i++) {// prints out all of the buttons(main)
            
            switch (i) {
                case 0: boxColor = Color.BLACK;
                break;

                case 1: boxColor = Color.DARK_GRAY;
                break;
                
                case 2: boxColor = Color.BLACK;
                break;
                
                case 3: boxColor = Color.DARK_GRAY;
                break;
                
                case 4: boxColor = Color.BLACK;
                break;
                
                case 5: boxColor = Color.DARK_GRAY;
                break;
                
                case 6: boxColor = Color.BLACK;
                break;
                
                case 7: boxColor = Color.DARK_GRAY;
                break;
                
                case 8: boxColor = Color.BLACK;
                break;
            
                default: boxColor = Color.DARK_GRAY;
                break;
            }
            
            for (int j = 0; j < table[i].length; j++) {
                table[i][j].setBackground(boxColor);
                table[i][j].setFont(new Font("", Font.BOLD, (width / 17)));
                
                //calc x and y pos (same as 3x3 tic-tac-toe)
                x = (width / 3) * (i % 3);
                y = (height / 3) * (i / 3);
                bwidth = (width/9) + 1;
                bheight = (height/9) + 1;
                //round two
                //calc button xPos
                x += (j * bwidth);
                x -= (bwidth * (j/3)) * 3;
                //calc button yPos
                y += (bheight * (j/3));
                 
                table[i][j].setBounds(x, y, bwidth, bheight);
                table[i][j].addActionListener((ActionListener)this);
            }
        }

        for (int i = 0; i < 9; i++) {// adding all the buttons to the panel
            //System.out.println(i + "-------");
            for (int j = 0; j < 9; j++) {
                panel.add(table[i][j]);
                panel.setVisible(true);
            }
        }
        frame.add(panel);
        frame.setBackground(Color.RED);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String turn, nextTurn;
        Color textColor;
        String winnerCheckMark;
        boolean nextbox = false;
        String x = "x", Player_1 = x;
        String o = "o", Player_2 = o;
        //String[][] table = new String[9][9];

        if ( (count%2) == 0) {
            turn = x;
            nextTurn = o;
            textColor = Color.RED;
        } else {
            turn = o;
            nextTurn = x;
            textColor = Color.BLUE;
        }

        for (int i = 0; i < lmove.length; i++) {
            lmove[i] = false;
        }
        

        for (int i = 0; i < table.length; i++) {
            
            boxColor = boxColor(i ,boxColor , "#000000", "#444444");
            for (int j = 0; j < table[i].length; j++) {
                if (play[i]) {
                    table[i][j].setBackground(boxColor);
                } 
            }
        }
        

        for (int i = 0; i < play.length; i++) {
            if (!(count > 99) && play[i]) {
                for (int j = 0; j < table[i].length; j++) {
                    if (e.getSource() == table[i][j]) {
                        special_Move(j);
                        if (!(table[i][j].getText() == nextTurn) && !(table[i][j].getText() == turn)) {
                            table[i][j].setText(turn);
                            table[i][j].setForeground(textColor);
                            count++;
                        }
                    } 
                }//ends if
            } 
        }         

        winners(x, o);
       
        for (int i = 0; i < play.length; i++) {
            if (!play[i] && holdWinner[i]){//if the box has a winner
                holdWinner[i] = false;

                if (turn.equals(x)) winnerCheckMark = "#770000"; 
                else winnerCheckMark = "#000077";


                for (int j = 0; j < holdWinner.length; j++) {
                    if (e.getSource() == table[i][j]) {
                        special_Move(j);
                    } 

                }


                for (int j = 0; j < table.length; j++) {
                    table[i][j].setBackground(Color.decode(winnerCheckMark));
                }

            }
        }
    }//ends actionperformed
    
    
    public boolean check_winner(String a, String b, String c) {
        boolean winner = false;
        if ((a == b) && (b == c) && (a == c)) {
            winner = true;
        }
        return winner;
    }

    public static Color boxColor (int i, Color f,String a, String b) {
            switch (i) {
                case 0: f = Color.decode(a);
                break;

                case 1: f = Color.decode(b);
                break;
                
                case 2: f = Color.decode(a);
                break;
                
                case 3: f = Color.decode(b);
                break;
                
                case 4: f = Color.decode(a);
                break;
                
                case 5: f = Color.decode(b);
                break;
                
                case 6: f = Color.decode(a);
                break;
                
                case 7: f = Color.decode(b);
                break;
                
                case 8: f = Color.decode(a);
                break;
            
                default: f = Color.decode(b);
                break;
            }
        return f;
    }

    public void winners(String x, String o) {
        //checking the winner
        for (int i = 0; i < table.length; i++) {
            //left
            if (table[i][0].getText().equals(x) || table[i][0].getText().equals(o)) {
                if (check_winner(table[i][0].getText(), table[i][1].getText(), table[i][2].getText()))    play[i] = false;
                if (check_winner(table[i][0].getText(), table[i][3].getText(), table[i][6].getText()))    play[i] = false;
                //System.out.println("1");
            }
            
            //center
            if (table[i][4].getText().equals(x) || table[i][4].getText().equals(o)) {
                if (check_winner(table[i][0].getText(), table[i][4].getText(), table[i][8].getText()))    play[i] = false;
                if (check_winner(table[i][2].getText(), table[i][4].getText(), table[i][6].getText()))    play[i] = false;
                if (check_winner(table[i][1].getText(), table[i][4].getText(), table[i][7].getText()))    play[i] = false;
                if (check_winner(table[i][3].getText(), table[i][4].getText(), table[i][5].getText()))    play[i] = false;
                //System.out.println("1");
            }
            
            //right
            if (table[i][8].getText().equals(x) || table[i][8].getText().equals(o)) {
                if (check_winner(table[i][2].getText(), table[i][5].getText(), table[i][8].getText()))    play[i] = false;
                if (check_winner(table[i][6].getText(), table[i][7].getText(), table[i][8].getText()))    play[i] = false;
                //System.out.println("1");
            }
        }
    }

    public void special_Move(int j) {
        if (!play[j]) {
            for (int j2 = 0; j2 < table[j].length; j2++) {
                for (int k = 0; k < table.length; k++) {
                    if (play[k]) {
                        boxColor = boxColor(k ,boxColor , "#666600", "#444400");
                        table[k][j2].setBackground(boxColor);
                        lmove[j2] = true;
                    }
                }
            }
        } else {
            for (int j2 = 0; j2 < table[j].length; j2++) {
                table[j][j2].setBackground(Color.decode("#666600"));
                lmove[j2] = true;
            }        
        }
    }
    
}//ends main class