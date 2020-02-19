/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

/**
 *
 * @author joshgingerich
 */

//Specifies methods needed to have a function board
public interface Board {
    
    //initializes a new board
    public void newBoard();
    
    
    //prints the board to a console
    public void printBoard();
    
    //returns a piece, represented by an int, 
    //from a specific square,represented by coordinates, on the board
    public int get(int i,int j);
    
    //places a piece on the board, given its numerical value k,
    // and then its square coordinates
    public void put(int k, int i,int j);
    
    //removes a piece from a square, represented by its coordinates
    //and sets the square to its default value
    public void remove(int i, int j);
    
    //returns true if given coordinates are on the board, false otherwise
    public boolean onBoard(int i, int j);
    
    //determines if the first input piece is an enemy with the second input piece
    public boolean enemyPiece(int i, int j, int x, int y);
    
    //valid means a,b is on the board and either empty or an enemy piece to i,j
    public boolean valid(int i, int j, int a, int b);
    
    //stricValid means its on the board and a value of 0
    public boolean strictValid(int a, int b);
    
    //moves a piecee from square ij to square xy
    //move should update history, turn, pawn promotion, and king position
    public void move(int i, int j,int x,int y);
    
    //takes back last move found in history, updates turn
    public void undo();
    
    //accepts a string argument to make a move
    public void stringMove(String move);
    
}
