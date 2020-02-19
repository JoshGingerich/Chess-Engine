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
public class Evaluation implements Evaluator {
    ArrayBoard board;
    
    public Evaluation(ArrayBoard x) {
        this.board = x;
    }
    
    
    @Override
    public int materialEvaluation() {
        int count = 0;
        for (int i=0;i<8;i++) {
            for (int j=0;j<8;j++) {
                if (board.get(i,j) != 4 && board.get(i, j) != -4) {
                    count = count + board.get(i, j);
                } 
                if (board.get(i,j) == 2) {
                    count++;
                }
                if (board.get(i,j) == -2) {
                    count--;
                }
            }
        }
        return count;
    }
}
