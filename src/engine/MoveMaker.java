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
public interface MoveMaker {
    
    //Allows the user to input a move,should update turn and check to see if game is over, and call on compMove()
    public void userMove(String s);
    
    //Should make a move for the computer, update turn and check to see if game is over
    public void compMove();
    
}
