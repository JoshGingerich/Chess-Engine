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
//Specifies how the position will be evaluated
public interface Evaluator {
    
    //evaluates the position strictly on material, White+,Black-
    public int materialEvaluation();
    
}
