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
public class Info implements GameInfo {
    static String turn = "white";
    ArrayBoard board;
    LegalMoves legals;
    
    public Info(ArrayBoard x) {
        this.board = x;
        this.legals = new LegalMoves(board,this);
    }
    
    //indicates wich color's turn it is
    @Override
    public void turn() {
        if (board.num_moves%2 == 0) {
            turn = "white";
        }
        else { turn = "black"; }               
    }
    
    //check to see if the king is located in the potential move squares of the enemy team
    @Override
    public boolean in_check(int i, int j, String color) {
        String enemy = "black";
        turn="black";
        if (color.equals("black")) {
            enemy = "white"; 
            turn="white";
        }
        String x = legals.moves(enemy);
        String[] splitmoves = x.split(" ");
        String enMoves = "";
        for(int m=0;m<splitmoves.length;m++) {
            enMoves = enMoves + splitmoves[m].charAt(2) + splitmoves[m].charAt(3) + " ";
        }
        turn=color;
        if(enMoves.contains(""+i+j)) { return true; }
       // System.out.println("false");
        return false;
    }
    
    //no moves and king in check
    @Override
    public boolean inCheckmate(String color) {
        int[] x = board.wking;
        if(color.equals("black")) { x=board.bking; }
        //System.out.println(this.kingSafe(x[0],x[1],x[0],x[1]));
        if(in_check(x[0],x[1],color) && legals.moves(color).equals("")) {
            System.out.println("Game Over - " + color + " in checkmate");
            return true;
        } return false; 
    }
    
    //only kings are left
    @Override
    public boolean indraw() {
        int count = 0;
        for (int a=0;a<8;a++) {
            for(int b=0;b<8;b++) {
                if(board.get(a,b)!= 0) {
                    count++;
                }
            }
        }
        if(count == 2) { return true; }
        else { return false; }
    }

    //no moves and king not in check
    @Override
    public boolean inStalemate(String color) {
        int[] x = board.wking;
        if(color.equals("black")) { x=board.bking; }
        if(in_check(x[0],x[1],color)==false && legals.moves(color).equals("")) {
            System.out.println("Stalemate");
            return true;
        }
        return false;
    }
    
    //checks all game ending criteria
    @Override
    public  boolean allGood(String color) {
        if(!inCheckmate(color) && !inStalemate(color) && !indraw()) {
            return true;
        } return false;
    }
    
}
