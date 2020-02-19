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

//Keeps track of important game information between each turn
public interface GameInfo {
    
    //Keeps track of whose turn it is to make a move, updates string turn
    public void turn();
    
    //Checks to see if king is being attacked by enemy piece
    public boolean in_check(int i, int j, String color);
    
    //no moves and king in check
    public boolean inCheckmate(String color);
    
    //only kings are left
    public boolean indraw();
    
    //no moves and king is not in check
    public boolean inStalemate(String color);
    
    //checks all game ending criteria
    public boolean allGood(String color);
    
}
