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
public interface MoveDecider {
    
    //Should return the first move as a string given a string of moves
    public String firstMove(String moves);
    
    //Should find a good move for white using minimax algorithm
    public String minimaxWhite(String node, int alpha, int beta, int depth, String minimax);
    
    //Should find a good move for black using minimax algorithm
    public String minimaxBlack(String node, int alpha, int beta, int depth, String minimax);
    
    //Should find a move for black using minimax algorithm with alpha-beta pruning,
    //specified by depth, and return the move as a string
    public String alphaBetaBlack(String node, int alpha, int beta, int depth, String minimax);
    
    //Should find a move for white using minimax algorithm with alpha-beta pruning,
    //specified by depth, and return the move as a string
    public String alphaBetaWhite(String node, int alpha, int beta, int depth, String minimax);
    
}
