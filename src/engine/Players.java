/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

//import static engine.ChessGame.turn;

/**
 *
 * @author joshgingerich
 */
public class Players implements MoveMaker {
    ArrayBoard board;
    LegalMoves legals;
    Info status;
    MoveDecision choice;
    Evaluation eval;
    
    public Players(ArrayBoard x, LegalMoves y, Info z, MoveDecision a, Evaluation b) {
        this.board = x;
        this.legals = y;
        this.status = z;
        this.choice = a;
        this.eval = b;
    }
    
    @Override
    public void userMove(String s) {
        System.out.print("Possible User Moves: ");
        legals.printMoves(status.turn);
        System.out.println("User Moved "+s);
        /*if(s.equals("0406")) {
            move(0,7,0,5);
            num_moves--;
        } else if(s.equals("0402")) {
            move(0,0,0,3);
            num_moves--;
        }*/
        //move function with input
        board.stringMove(s);
        //turn function() and then allow comp to make move
        status.turn();
        if(status.allGood(status.turn)) {
            compMove();
        }
        
    }
    
    //computer finds all legal moves available, moves first one it finds, prints history and eval, checks if game is over
    @Override
    public void compMove() {
        System.out.print("Possible Comp Moves: ");
        legals.printMoves(status.turn);
        choice.positions=0;
        String temp = choice.alphaBetaBlack("0001",-1000,1000,4,"max");
        //String temp = minimaxBlack("0001",-1000,1000,4,"max");
        //String temp = choice.firstMove(legals.moves(turn));
        System.out.println("Computer Moved "+temp);
        System.out.println("Number of analyzed positions: "+choice.positions);

        board.stringMove(temp);
        status.turn();
        System.out.println("History: " + board.history);
        System.out.println("Evaluation: " + eval.materialEvaluation());
        System.out.println("--------------");
        if(status.allGood(status.turn)) {}

    }
    
}
