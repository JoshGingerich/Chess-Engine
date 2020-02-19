/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

/**
 * @author joshgingerich
 */
import javax.swing.*;
import java.lang.*;



public class ChessGame {
    ArrayBoard board;
    Players player;
    LegalMoves legals;
    Evaluation eval;
    Info status;
    MoveDecision choice;
    //mapBoard board;
    
    
    
    public  ChessGame() {
      this.board = new ArrayBoard();
      this.status = new Info(board);
      this.legals = new LegalMoves(board,status);
      this.eval = new Evaluation(board);
      this.choice = new MoveDecision(board,legals,eval,status);
      this.player = new Players(board, legals, status, choice, eval);
    }
    
   
    
    public static void main(String[] args) {
        // TODO code application logic here
        ChessGame practice = new ChessGame();
        JFrame y =new JFrame("ChessGame");
        y.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        y.setVisible(true);
        y.setSize(700,700);
        GUI graphics = new GUI(practice);
        y.add(graphics);
    }
    
}
