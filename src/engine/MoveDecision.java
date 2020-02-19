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
public class MoveDecision implements MoveDecider {
    static int positions = 0;
    ArrayBoard board;
    LegalMoves legals;
    Evaluation eval;
    Info status;
    
    public MoveDecision(ArrayBoard a, LegalMoves b, Evaluation c, Info d) {
        this.board = a;
        this.legals = b;
        this.eval = c;
        this.status = d;
    }
    
    
    @Override
    public String firstMove(String x) {
        return x.substring(0,4);
    }
    
    @Override
    public String minimaxWhite(String node, int alpha, int beta, int depth, String minimax) {
        positions++;
      
        if(depth==0) {
            return node+(eval.materialEvaluation());
        }
        
        String p1 = "";
        if(minimax.equals("max")) { p1 = legals.moves("white"); }
        if(minimax.equals("min")) { p1 = legals.moves("black"); }
        
        //this indicates one side is in checkmate and assigns values accordingly
        if(p1.equals("")) {
            int endEval;
            if(minimax.equals("max")) { endEval = -1000; }
            else { endEval = 1000; }
            return node+endEval;
        }
        
        String[] p2 = p1.split(" ");
        
        if(minimax.equals("max")) {
            int temp = -1000;
            for(int i=0;i<p2.length;i++) {
                board.stringMove(p2[i]); status.turn();
                String newNode = minimaxWhite(p2[i],alpha,beta,depth-1,"min");
                int newNodeValue = (Integer.parseInt(newNode.substring(4,newNode.length())));
                board.undo(); status.turn();
                            
                temp = java.lang.Math.max(temp, newNodeValue);
      
                if(newNodeValue==temp) { node = p2[i]+newNodeValue; }
            }
                return node;
            }
        if(minimax.equals("min")) {
            int temp2 = 1000;
            for(int j=0;j<p2.length;j++) {
                board.stringMove(p2[j]); status.turn();
                String newNode = minimaxWhite(p2[j],alpha,beta,depth-1,"max");
                int newNodeValue = (Integer.parseInt(newNode.substring(4,newNode.length())));
                board.undo(); status.turn();

                temp2 = java.lang.Math.min(temp2, newNodeValue);

                if(newNodeValue==temp2) { node = p2[j] + newNodeValue;}
            }
            return node;
        }
        return p2[0];
    }
    
    @Override
    public String minimaxBlack(String node, int alpha, int beta, int depth, String minimax) {
        positions++;
        
        if(depth==0) {
            return node+(eval.materialEvaluation());
        }
        
        String p1 = "";
        if(minimax.equals("max")) { p1 = legals.moves("black"); }
        if(minimax.equals("min")) { p1 = legals.moves("white"); }
        
        //this indicates one side is in checkmate and assigns values accordingly
        if(p1.equals("")) { 
            int endEval;
            if(minimax.equals("min")) { endEval = -1000; }
            else { endEval = 1000; }
            return node+endEval; 
        }
        
        String[] p2 = p1.split(" ");
        
        if(minimax.equals("min")) {
            int temp = -1000;
            for(int i=0;i<p2.length;i++) {
                board.stringMove(p2[i]); status.turn();
                String newNode = minimaxBlack(p2[i],alpha,beta,depth-1,"max");
                String tempNodevalue = newNode.substring(4);
                int newNodeValue = (Integer.parseInt(tempNodevalue));
                board.undo(); status.turn();
                            
                temp = java.lang.Math.max(temp, newNodeValue);
      
                if(newNodeValue==temp) { node = p2[i]+newNodeValue; }
            }
                return node;
            }
        if(minimax.equals("max")) {
            int temp2 = 1000;
            for(int j=0;j<p2.length;j++) {
                board.stringMove(p2[j]); status.turn();
                String newNode = minimaxBlack(p2[j],alpha,beta,depth-1,"min");
                String tempNodevalue = newNode.substring(4,newNode.length());
                int newNodeValue = (Integer.parseInt(tempNodevalue));
                board.undo(); status.turn();

                temp2 = java.lang.Math.min(temp2, newNodeValue);

                if(newNodeValue==temp2) { node = p2[j] + newNodeValue;}
            }
            return node;
        }
        return p2[0];
    }
    
    @Override
    public String alphaBetaWhite(String node, int alpha, int beta, int depth, String minimax) {  
        positions++;
        
        if(depth==0) {
            return node+(eval.materialEvaluation());
        }
        
        //Produces possible moves for each side, orders moves first by capturing pieces
        String p1 = "";
        if(minimax.equals("max")) { p1 = legals.moves("white"); }
        if(minimax.equals("min")) { p1 = legals.moves("black"); }
        

        if(p1.equals("")) {
            int endEval;
            if(minimax.equals("max")) { endEval = -1000; }
            else { endEval = 1000; }
            return node+endEval;
        }


        //if(p1.equals("")) { p1 = "9999"; }
            //System.out.println(p1+"p1 was here");
            
        String[] z = p1.split(" ");
        String[] p2 = new String[z.length];
        
        if(p1.equals("9999") || minimax.equals("min")){ p2=z;}
        else if(minimax.equals("max")) {
            //System.out.println(z.length);
            captureSort(z,p2);
        }

        if(minimax.equals("max")) {
            int temp = -1000;
            for(int i=0;i<p2.length;i++) {
                board.stringMove(p2[i]); status.turn();
                String newNode = alphaBetaWhite(p2[i],alpha,beta,depth-1,"min");
                int newNodeValue = (Integer.parseInt(newNode.substring(4)));
                board.undo();  status.turn();
                temp = java.lang.Math.max(temp, newNodeValue);
      
                if(newNodeValue==temp) { node = p2[i]+newNodeValue; }
                alpha = java.lang.Math.max(temp,alpha);
               
                    if(beta<alpha) {
    
                    break;
                    }
            }
            return node;
            }
        if(minimax.equals("min")) {
        //else {
            int temp2 = 1000;
            for(int j=0;j<p2.length;j++) {
                board.stringMove(p2[j]); status.turn();
                String newNode = alphaBetaWhite(p2[j],alpha,beta,depth-1,"max");
                int newNodeValue = (Integer.parseInt(newNode.substring(4)));
                board.undo(); status.turn();
                temp2 = java.lang.Math.min(temp2, newNodeValue);

                if(newNodeValue==temp2) { node = p2[j] + newNodeValue; }
                beta = java.lang.Math.min(temp2, beta);
                
                if(beta<alpha) {
                    break;
                }
           
            }
            return node;
        }
        return p2[0];
    }
    
    @Override
    public String alphaBetaBlack(String node, int alpha, int beta, int depth, String minimax) {  
        positions++;
        
        if(depth==0) {
            return node+(eval.materialEvaluation());
        }
        
        String p1 = "";
        if(minimax.equals("max")) { p1 = legals.moves("black"); }
        if(minimax.equals("min")) { p1 = legals.moves("white"); }
        
        
        if(p1.equals("")) {
            int endEval;
            if(minimax.equals("min")) { endEval = -1000; }
            else { endEval = 1000; }
            return node+endEval; 
        }
        
        String[] z = p1.split(" ");
        String[] p2 = new String[z.length];
        
        if(p1.length()==1 || minimax.equals("min")){ p2=z;}
        else if(minimax.equals("max")) {
            captureSort(z,p2);
        }     

        if(minimax.equals("min")) {
            int temp = -1000;
            for(int i=0;i<p2.length;i++) {
                board.stringMove(p2[i]); status.turn();
                String newNode = alphaBetaBlack(p2[i],alpha,beta,depth-1,"max");
                int newNodeValue = (Integer.parseInt(newNode.substring(4)));
                board.undo(); status.turn();
                temp = java.lang.Math.max(temp, newNodeValue);

                if(newNodeValue==temp) { node = p2[i]+newNodeValue; }
                alpha = java.lang.Math.max(temp,alpha);
                    if(alpha>beta) {
                    break;
                    }
            }
            return node;
            }
        if(minimax.equals("max")) {
            int temp2 = 1000;
            for(int j=0;j<p2.length;j++) {
                board.stringMove(p2[j]); status.turn();
                String newNode = alphaBetaBlack(p2[j],alpha,beta,depth-1,"min");
                int newNodeValue = (Integer.parseInt(newNode.substring(4)));
                board.undo(); status.turn();
                temp2 = java.lang.Math.min(temp2, newNodeValue);
                
                if(newNodeValue==temp2) { node = p2[j] + newNodeValue; }
                beta = java.lang.Math.min(temp2, beta);
                if(beta<alpha) {
                    break;
                }
            }
            return node;
        }
        return p2[0];
    }
    
    //organizes possible moves by placing capture moves at the front
    //improves alpha beta efficiency
    private void captureSort(String[] z, String[] p2) {
        int start=0;
        int end = z.length-1;
        for(int a=0;a<z.length;a++) {
                int x = Character.getNumericValue(z[a].charAt(2));
                int y = Character.getNumericValue(z[a].charAt(3));
                if(board.get(x,y)>0) {
                    p2[start]=z[a];
                    start++;
                }
                else { p2[end]=z[a]; end--; }
            }
    }
    
    
}
