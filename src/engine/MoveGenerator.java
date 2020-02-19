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
public interface MoveGenerator {
    
    //prints all legals moves found
    public void printMoves(String turn);
    
    //finds all moves by searching through every piece for the current turn's color
    public String moves(String turn);
    
    //finds all legal moves for a pawn piece
    public String pawnMoves(int i,int j);
    
    //finds all legal moves for a king piece
    public String kingMoves(int i, int j);
    
    //finds all legal moves for a knight piece
    public String knightMoves(int i, int j);
    
    //finds all legal moves for a rook piece
    public String rookMoves(int i,int j);
    
    //finds all legal moves for a bishop piece
    public String bishopMoves(int i, int j);
    
    //finds all legal moves for a queen piece, treating it as a bishop and a rook
    public String queenMoves(int i, int j);
    
    //inDanger checks to make sure each move is legal by 
    //checking king safety (Conceptual idea from LogicCrazyChess & chesswiki)
    public boolean inDanger(int i, int j, int k, int l);
    
}
