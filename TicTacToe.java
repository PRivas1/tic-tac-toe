//Pablo Rivas TicTacToe

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.*;

public class TicTacToe implements ActionListener{
    //initalizing variables that i need access to throught program
    JButton[] buttons = new JButton[9];
    JButton resetGameButton = new JButton();
    JLabel title = new JLabel("");
    JMenuItem xWins = new JMenuItem("X: 0");
    JMenuItem oWins = new JMenuItem("O: 0");
    JMenuItem draws = new JMenuItem("Draws: 0");
    int xIntWins = 0;
    int oIntWins = 0;
    int IntDraws = 0;
    int turn = -1;
    int numTurns = 0;

    //creating gui
    TicTacToe(){
        JFrame Window = new JFrame("Tic Tac Toe");
        Window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Window.setSize(700, 775);
        Window.setVisible(true);
        Window.setLocationRelativeTo(null);

        JPanel titlePanel = new JPanel();
        title.setFont(new Font(null, 0, 40));
        titlePanel.add(title);

        JPanel gameBoard = new JPanel();
        gameBoard.setLayout(new GridLayout(3,3));

        JMenuBar bottomBar = new JMenuBar();
        JMenu scores = new JMenu("Scores");
        scores.add(xWins);
        scores.add(oWins);
        scores.add(draws);
        resetGameButton.setText("Reset");
        resetGameButton.addActionListener(this);

        bottomBar.add(scores);
        bottomBar.add(resetGameButton);
        
        //buttons
        for(int i=0; i<9; i++){
            buttons[i] = new JButton();
            gameBoard.add(buttons[i]);
            buttons[i].addActionListener(this);
            buttons[i].setFont(new Font(null, 0, 80));
        }

        Window.getContentPane().add(BorderLayout.CENTER, gameBoard);
        Window.getContentPane().add(BorderLayout.NORTH, titlePanel);
        Window.getContentPane().add(BorderLayout.SOUTH, bottomBar);
        Window.setVisible(true);

        playGame();
    }

    //main method that runs game per round
    public void playGame() {
        if(turn == 0){
            turn = 1;
        }else if(turn == 1){
            turn = 0;
        }
        
        if(turn == -1){
            Random rand = new Random();
            turn = rand.nextInt(2);
            if(numTurns != 0){ //check for first round
                resetGame();
            }
            numTurns++;
        }
        
        if(turn == 0)
            title.setText("X's Turn");
        if(turn == 1)
            title.setText("O's Turn");

        //check wins
        if(checkWin() == "X"){
            title.setText("X Wins!");
            xIntWins++;
            xWins.setText("X: "+xIntWins);
            turn = -1;
        }else if(checkWin() == "O"){
            title.setText("O Wins!");
            oIntWins++;
            oWins.setText("O: "+oIntWins);
            turn = -1;
        }else if(checkWin() == "draw"){
            title.setText("Draw!");
            IntDraws++;
            draws.setText("Draws: "+IntDraws);
            turn = -1;
        }

    }

//checks for winner or draw
public String checkWin() {
    //check row
    for(int i=0; i<3; i++){
        i *= 3;
        int checkX = 0;
        int checkO = 0;
        for(int y=0; y<3; y++){
            if(buttons[i+y].getText() == "X"){
                checkX++;
            }else if(buttons[i+y].getText() == "O"){
                checkO++;
            }

        }
        if(checkX == 3){
            for(int y=0; y<3; y++){
                buttons[i+y].setForeground(Color.ORANGE);
            }
            return "X";
        }
        if(checkO == 3){
          for(int y=0; y<3; y++){
            buttons[i+y].setForeground(Color.ORANGE);
            }
            return "O";
        }   
        
    }

    //check col
    for(int i=0; i<3; i++){
        int checkX = 0;
        int checkO = 0;
        for(int y=0; y<9; y+= 3)
            if(buttons[i+y].getText() == "X"){
                checkX++;
            }else if(buttons[i+y].getText() == "O"){
                checkO++;
        }
        if(checkX == 3){
            for(int y=0; y<9; y+=3){
                buttons[i+y].setForeground(Color.ORANGE);
            }
            return "X";
        }
        if(checkO == 3){
            for(int y=0; y<9; y+=3){
                buttons[i+y].setForeground(Color.ORANGE);
            }
            return "O";
        }
    }

    //check diag
    
    for(int i=0; i<2; i++){
        int checkX = 0;
        int checkO = 0;

        if(i==0){
            if(buttons[0].getText() == "X"){
                checkX += 1;
            }else if(buttons[0].getText() == "O"){
                checkO += 1;
            }

            if(buttons[4].getText() == "X"){
                checkX += 1;
            }else if(buttons[4].getText() == "O"){
                checkO += 1;
            }

            if(buttons[8].getText() == "X"){
                checkX += 1;
            }else if(buttons[8].getText() == "O"){
                checkO += 1;
            }

        }else{
            if(buttons[2].getText() == "X"){
                checkX += 1;
            }else if(buttons[2].getText() == "O"){
                checkO += 1;
            }

            if(buttons[4].getText() == "X"){
                checkX += 1;
            }else if(buttons[4].getText() == "O"){
                checkO += 1;
            }

            if(buttons[6].getText() == "X"){
                checkX += 1;
            }else if(buttons[6].getText() == "O"){
                checkO += 1;
            }
        }

        if(checkX == 3){
            if(i == 0){
                buttons[0].setForeground(Color.ORANGE);
                buttons[4].setForeground(Color.ORANGE);
                buttons[8].setForeground(Color.ORANGE);
            }else{
                buttons[2].setForeground(Color.ORANGE);
                buttons[4].setForeground(Color.ORANGE);
                buttons[6].setForeground(Color.ORANGE);
            }
            return "X";
        }
        if(checkO == 3){
            if(i == 0){
                buttons[0].setForeground(Color.ORANGE);
                buttons[4].setForeground(Color.ORANGE);
                buttons[8].setForeground(Color.ORANGE);
            }else{
                buttons[2].setForeground(Color.ORANGE);
                buttons[4].setForeground(Color.ORANGE);
                buttons[6].setForeground(Color.ORANGE);
            }
            return "O";
        }
    }

    //check for draw
    int filled = 0;
    for(int i=0; i<9; i++){
        if(buttons[i].getText() != ""){
            filled++;
        }
    }
    if(filled == 9){
        for(int i=0; i<9; i++){
            if(buttons[i].getText() == "X"){
                buttons[i].setForeground(Color.GRAY);
            }else{
                buttons[i].setForeground(Color.DARK_GRAY);
            }
        }
        return "draw";
    }


    return "none";
}

//resets text on buttons
public void resetGame() {
    for(int i=0; i<9; i++){
        buttons[i].setText("");
    }
}

    @Override
    public void actionPerformed(ActionEvent e) {
        //resets game from button
        if(e.getSource() == resetGameButton){
            resetGame();
            playGame();
        }

        //checks which button was pressed as long as it wasnt pressed before
        for(int i=0; i<9; i++){
            if(e.getSource() == buttons[i]){
                if(turn == 0){
                    if(buttons[i].getText() == ""){
                        buttons[i].setText("X");
                        buttons[i].setForeground(Color.RED);
                        playGame();
                    }
                }
                if(turn == 1){
                    if(buttons[i].getText() == ""){
                        buttons[i].setText("O");
                        buttons[i].setForeground(Color.BLUE);
                        playGame();
                    }
                }
                
            }
        }
    }
}
