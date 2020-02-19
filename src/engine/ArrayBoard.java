/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;



import java.util.Stack;

/**
 *
 * @author joshgingerich
 */
public class ArrayBoard implements Board {
    static int[][] arrayBoard;
    static int[] wking = new int[] {0,4};
    static int[] bking = new int[] {7,4};
    static int num_moves; 
    static Stack history = new Stack(); //keeps a record of all moves during the game
    static Stack recentCapture = new Stack();
    
    
    public ArrayBoard() {
        this.newBoard();
    }
    
    @Override
    public void newBoard() {
        arrayBoard = new int[][] {
            {5,2,3,9,4,3,2,5},
            {1,1,1,1,1,1,1,1},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {-1,-1,-1,-1,-1,-1,-1,-1},
            {-5,-2,-3,-9,-4,-3,-2,-5}    
        };
    }
    
    @Override
    public void printBoard() {
        for (int i=7;i>-1;i--) {
            for (int j=0;j<8;j++) {
                System.out.print(get(i,j));
                if (get(i,j) >= 0) {
                    System.out.print(" ");
                }
            }
            System.out.println("");
        }
    }
    
    //gets piece on a specific square
    @Override
    public int get(int i, int j) {
        return arrayBoard[i][j];
    }
    
    //places a piece on the board by putting its int at i row, j col
    @Override
    public void put(int k, int i, int j) {
        arrayBoard[i][j] = k;
        if(get(i,j)==4) { wking[0] = i; wking[1] = j; }
        else if(get(i,j)==-4) { bking[0] = i; bking[1] = j; }
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
