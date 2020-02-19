/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
/**
 *
 * @author joshgingerich
 */
public class mapBoard implements Board {
    static Map<Integer,Integer> mapBoard = new HashMap<Integer,Integer>();
    static int[] wking = new int[] {0,4};
    static int[] bking = new int[] {7,4};
    static int num_moves; 
    static Stack history = new Stack(); //keeps a record of all moves during the game
    static Stack recentCapture = new Stack();
    
    //White = +; Black = -;
    final int pawn = 1;
    final int knight = 2;
    final int bishop = 3;
    final int rook = 5;
    final int queen = 9;
    final int king = 4;
    
    
    public mapBoard() {
        this.newBoard();
    }
    
    @Override
    public void newBoard() {
        mapBoard.put(00, rook);mapBoard.put(01, knight);mapBoard.put(02, bishop);
        mapBoard.put(03, queen);mapBoard.put(04,king);mapBoard.put(05, bishop);
        mapBoard.put(06, knight);mapBoard.put(07, rook);
        for(int i=0;i<8;i++) { mapBoard.put(10+i, pawn); mapBoard.put(60+i,-pawn); }
        for(int a=2;a<6;a++) {
            for(int b=0;b<8;b++) {
                mapBoard.put((a*10)+b,0);}}
        mapBoard.put(70,-rook);mapBoard.put(71,-knight);mapBoard.put(72,-bishop);
        mapBoard.put(73,-queen);mapBoard.put(74,-king);mapBoard.put(75,-bishop);
        mapBoard.put(76,-knight);mapBoard.put(77,-rook);
        
    }
    
    @Override
    public void printBoard() {
        for (int i=7;i>-1;i--) {
            for (int j=0;j<8;j++) {
                System.out.print(mapBoard.get((i*10)+j));
                if (mapBoard.get((i*10)+j) >= 0) {
                    System.out.print(" ");
                }
            }
            System.out.println("");
        }
    }
    
    @Override
    public int get(int i, int j) {
        return mapBoard.get((i*10)+j);
    }
    
    @Override
    public void put(int k, int i, int j) {
        mapBoard.put((i*10)+j,k);
    }
    
    //removes a piece from the board, sets value to 0
    @Override
    public void remove(int i, int j) {
        put(0,i,j);
    }
    
    @Override
    public boolean onBoard(int i,int j) {
        if(i>=0 && i<=7 && j>=0 && j<=7) {
            return true;           
        } return false;
    }
    //determines if the first input piece is an enemy with the second input piece
    @Override
    public boolean enemyPiece(int i, int j, int x, int y) {
        //check to see if x,y is enemy of i,j
        if (get(i,j)* get(x,y) < 0) {
            return true;
        } else { return false; }
    }
    
    //valid means its on the board and either empty or an enemy piece
    @Override
    public boolean valid(int i, int j, int a, int b) {
        if (onBoard(a,b) && (get(a,b)==0 || enemyPiece(i,j,a,b))) {
            return true;
        } return false;
    }
    
    //stricValid means its on the board and a value of 0
    @Override
    public boolean strictValid(int a, int b) {
        if (onBoard(a,b) && get(a,b)==0) {
            return true;           
        }
        return false;
    }
    
    //combines put and remove to move a piece,updates king location
    @Override
    public void move(int i, int j,int x,int y) {
        //System.out.println(""+i+j+x+y);
        if(!onBoard(i,j) || !onBoard(x,y)) { throw new IllegalStateException("Invalid Move");}
        recentCapture.push(get(x,y));
        //System.out.println(recentCapture);
        put(get(i,j),x,y);
        remove(i,j);
        String promotion = "";
        if(x==0 || x==7) {
            if(get(x,y) == 1) { 
                put(9,x,y); promotion="p"; }
            else if(get(x,y) == -1) {
                put(-9,x,y); 
                promotion="p";
            }
        }
        if(get(x, y)==4) { wking[0] = x; wking[1] = y; }
        else if(get(x,y)==-4) { bking[0] = x; bking[1] = y;}
        num_moves++;
        history.push(""+i+j+x+y+promotion);
    }
    
    //takes back last move found in history
    @Override
    public void undo() {
        String temp1 = (String) history.pop();
        int a = Character.getNumericValue(temp1.charAt(2));
        int b = Character.getNumericValue(temp1.charAt(3));
        int c = Character.getNumericValue(temp1.charAt(0));
        int d = Character.getNumericValue(temp1.charAt(1));
        int temp2 = (int) recentCapture.pop();
        
        if(temp1.length()==5) { put(get(a,b)/9,c,d); }
        else { put(get(a,b),c,d); }
        
        remove(a,b);
        put(temp2,a,b);
        num_moves--;
    }
    
    @Override
    public void stringMove(String x) {
        if(x.equals("9999")) { return; }
        int a = Character.getNumericValue(x.charAt(0));
        int b = Character.getNumericValue(x.charAt(1));
        int c = Character.getNumericValue(x.charAt(2));
        int d = Character.getNumericValue(x.charAt(3));
        move(a,b,c,d);
    }
}
