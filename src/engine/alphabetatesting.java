/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;
import java.util.Scanner;

/**
 *
 * @author joshgingerich
 */
public class alphabetatesting {
    static ChessGame test;
    static ArrayBoard board;
    
    public alphabetatesting(ArrayBoard x) {
        test = new ChessGame();
        board = x;
    }
    
    public static String alphaBeta(String node, int alpha, int beta, int depth, String minimax) {  
        System.out.println(node + " " + depth);
        if(depth==0 || node.equals("")) {
            return node+(alphabetatesting.evaluation());
        }
        
        System.out.println("Number of child nodes: ");
        Scanner sc=new Scanner(System.in);
        int temp3=sc.nextInt();
        String[] p2 = new String[temp3];
        for (int i=0;i<temp3;i++) {
            p2[i] = "1111";
        }

        if(minimax.equals("max")) {
            int temp = -1000;
            for(int i=0;i<p2.length;i++) {
                int a = Character.getNumericValue(p2[i].charAt(0));
                int b = Character.getNumericValue(p2[i].charAt(1));
                int c = Character.getNumericValue(p2[i].charAt(2));
                int d = Character.getNumericValue(p2[i].charAt(3));
                board.move(a,b,c,d);
                String newNode = alphaBeta(p2[i],alpha,beta,depth-1,"min");
                int newNodeValue = (Integer.parseInt(newNode.substring(4,newNode.length())));
                board.undo();
                
                temp = java.lang.Math.max(temp, newNodeValue);
                
                if(newNodeValue==temp) { node = p2[i]+newNodeValue; }
                alpha = java.lang.Math.max(temp,alpha);
                System.out.println(alpha);
                
                if(alpha>=beta) {
                    System.out.println("true");
                    break;
                    }
                System.out.println("Best Node: " + node);
            }
            return node;
            }
        if(minimax.equals("min")) {
            int temp2 = 1000;
            for(int j=0;j<p2.length;j++) {
                int a = Character.getNumericValue(p2[j].charAt(0));
                int b = Character.getNumericValue(p2[j].charAt(1));
                int c = Character.getNumericValue(p2[j].charAt(2));
                int d = Character.getNumericValue(p2[j].charAt(3));
                board.move(a,b,c,d);
                String newNode = alphaBeta(p2[j],alpha,beta,depth-1,"max");
                int newNodeValue = (Integer.parseInt(newNode.substring(4,newNode.length())));
                board.undo();
                
                temp2 = java.lang.Math.min(temp2, newNodeValue);

                if(newNodeValue==temp2) { node = p2[j] + newNodeValue; }
                beta = java.lang.Math.min(temp2, beta);
                System.out.println(beta);
                
                if(beta<=alpha) {
                    System.out.println("true2");
                    break;
                }
                System.out.println("Best Node: " + node);
            }
            return node;
        }
        return "";
    }
    
    private static int evaluation() {
        System.out.print("What is the value?: ");
        Scanner sc2 = new Scanner(System.in); 
        return sc2.nextInt();
    }
    
    public static void main(String[] args) {
        alphabetatesting practice = new alphabetatesting(new ArrayBoard());
        System.out.print(practice.alphaBeta("0000",-1000,1000,4,"max"));
    }
}
