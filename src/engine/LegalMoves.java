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
public class LegalMoves implements MoveGenerator {
    ArrayBoard board;
    Info status;
    
    public LegalMoves(ArrayBoard x,Info y) {
        this.board = x;
        this.status = y;
    }
    
    
    //finds all moves by searching through every piece for the current turn's color
    @Override
    public String moves(String turn) {
        String legals = "";
        int count = 0;
        int color = 1;
        if (turn.equals("black")) {
            color = -1;
        }
        for (int a=0;a<8;a++) {
            for (int b=0;b<8;b++) {
                int c = board.get(a,b);
                 if (c == 1*color) {
                   legals = legals + pawnMoves(a,b);
                 }else if(c==5*color) {
                     legals = legals + rookMoves(a, b);
                 } else if(c==3*color) {
                    legals = legals + bishopMoves(a, b);
                 } else if(c==9*color) {
                    legals = legals + queenMoves(a, b);
                 } else if(c==4*color) {
                     legals = legals + kingMoves(a, b);
                 } else if(c==2*color) {
                     legals = legals + knightMoves(a,b);
                 }
            }
        }
        return legals;
    }
    
    //prints all legals moves found
    @Override
    public void printMoves(String turn) {
        System.out.println(moves(turn));
    }
    
    //finds all legal moves for a pawn piece
    @Override
    public String pawnMoves(int i,int j) {
        String legalp = "";
        // orients direction based on color
        int dir = 1;
        if (board.get(i,j)==-1) {
            dir = -1;
        }
        
        //checks to see if one move forward works
        if (board.get(i + dir,j) == 0) {
            if(inDanger(i,j,i+dir,j)) {
            legalp = legalp + i + j + (i+dir) + j + " "; }
            //checks for both starting colors if two moves are possible
            if (i == 1 && board.get(i,j)==1 && board.get(i+2,j) == 0 && inDanger(i,j,i+2,j)) {
                legalp = legalp + i + j + (i+2) + j + " ";
            } else if (i==6 && board.get(i,j)==-1 && board.get(i-2,j)==0 && inDanger(i,j,i-2,j)) {
                legalp = legalp + i + j + (i-2) + j + " ";
             }
        }
        
        //checks both directions if taking a piece is possible
        if (j-1>=0 && board.enemyPiece(i,j,i+dir,j-1) && inDanger(i,j,i+dir,j-1)) {
            legalp = legalp + i + j + (i+dir) + (j-1) + " ";
           }
        if (j+1<=7 && board.enemyPiece(i,j,i+dir,j+1) && inDanger(i,j,i+dir,j+1)) {
            legalp = legalp + i + j + (i+dir) + (j+1) + " ";
           }
        return legalp;
        //Add in en passent later
    }
    
    @Override
    public String kingMoves(int i, int j) {
        String legalki = "";
        for (int k = i-1;k<i+2;k++) {
            for (int l = j-1;l<j+2;l++) {
                if (board.valid(i,j,k,l)) {
                    if(inDanger(i,j,k,l)) {
                    legalki = legalki + i + j + k + l + " ";
                    }
            }
            }
        }
        //castling
         /*  if(get(i,j)==4 && i==0 && j==4 && get(0,5)==0 && get(0,6)==0 && get(0,7)==5) {
                legalki = legalki + i + j + 0 + 6 + " ";
            } else if(get(i,j)==4 && i==0 && j==4 && get(0,3)==0 && get(0,2)==0 && get(0,1)==0 && get(0,0)==5) {
                legalki = legalki + i + j + 0 + 2;
            }*/
        return legalki; 
    }
    
    //finds all legal moves for a knight piece
    @Override
    public String knightMoves(int i, int j) {
        String legalkn = "";
        int[] k = new int[] {i-2,i-2,i-1,i-1,i+1,i+1,i+2,i+2};
        int[] l = new int[] {j-1,j+1,j-2,j+2,j-2,j+2,j-1,j+1};
        for (int a = 0;a<k.length;a++) {
            if(board.valid(i,j,k[a],l[a]) && inDanger(i,j,k[a],l[a])) {
               legalkn = legalkn + i + j + k[a] + l[a] + " ";
        }  
        }
        return legalkn;
    }
    
    //finds all legal moves for a rook piece
    @Override
    public String rookMoves(int i,int j) {
        String legalr = "";
        int k = i;
        int l = j;
        while (k-1>=0 && board.get(k-1,j) == 0) {
               k--;
               if(inDanger(i,j,k,j)) {
               legalr = legalr + i + j + k + j + " "; }
            }
        if (k-1>=0 && board.enemyPiece(i, j, k-1, j) && inDanger(i,j,k-1,j)) {
            k--;
            legalr = legalr + i + j + k + j + " ";
            } 
        k=i;
        while (k+1 <= 7 && board.get(k+1,j)== 0) {
            k++;
            if(inDanger(i,j,k,j)) {
            legalr = legalr + i + j + k + j + " "; }
        }
            if (k+1<=7 && board.enemyPiece(i,j,k+1,j) && inDanger(i,j,k+1,j)) {
                k++;
                legalr = legalr + i + j + k + j + " ";   
            }  
        while (l-1>=0 && board.get(i,l-1) == 0) {
            l--;
            if(inDanger(i,j,i,l)) {
            legalr = legalr + i + j + i + l + " "; }
            }
            if (l-1>=0 && board.enemyPiece(i,j,i,l-1) && inDanger(i,j,i,l-1)) {
                l--;
                legalr = legalr + i + j + i + l + " ";
            }
        l=j;
        while (l+1<=7 && board.get(i,l+1) == 0) {
            l++;
            if(inDanger(i,j,i,l)) {
            legalr = legalr + i + j + i + l + " "; }
        }
            if (l+1<=7 && board.enemyPiece(i,j,i,l+1) && inDanger(i,j,i,l+1)) {
                l++;
                legalr = legalr + i + j + i + l + " ";
            }
        return legalr;
    }
    
    //finds all legal moves for a bishop piece
    @Override
    public String bishopMoves(int i, int j) {
        String legalb = "";
        int k = i;
        int l = j;
        while (k+1<=7 && l-1>=0 && board.get(k+1,l-1) == 0) {
            k++; l--;
            if(inDanger(i,j,k,l)) {
            legalb = legalb + i + j + k + l + " ";}
        }
            if (k+1<=7 && l-1>=0 && board.enemyPiece(i, j, k+1, l-1)) {
                k++; l--;
                if(inDanger(i,j,k,l)) {
                legalb = legalb + i + j + k + l + " "; }
            }
        k=i; l=j;
        while (k-1>=0 && l-1>=0 && board.get(k-1,l-1) == 0) {
            k--;l--;
            if(inDanger(i,j,k,l)) {
            legalb = legalb + i + j + k + l + " ";}
        }
            if (k-1>=0 && l-1>=0 && board.enemyPiece(i,j,k-1,l-1)) {
                k--; l--;
                if(inDanger(i,j,k,l)) {
                legalb = legalb + i + j + k + l + " "; }
            }
        k=i; l=j;
        while (k+1<=7 && l+1<=7 && board.get(k+1,l+1) == 0) {
            k++; l++;
            if(inDanger(i,j,k,l)) {
            legalb = legalb + i + j + k + l + " ";}       
        }
            if (k+1<=7 && l+1<=7 && board.enemyPiece(i,j,k+1,l+1)) {
                k++; l++;
                if(inDanger(i,j,k,l)) {
                legalb = legalb + i + j + k + l + " ";}
            }
        k=i; l=j;
        while (k-1>=0 && l+1<=7 && board.get(k-1,l+1) == 0) {
            k--; l++;
            if(inDanger(i,j,k,l)) {
            legalb = legalb + i + j + k + l + " ";}
        }
            if (k-1>=0 && l+1<=7 && board.enemyPiece(i,j,k-1,l+1)) {
                k--; l++;
                if(inDanger(i,j,k,l)) {
                legalb = legalb + i + j + k + l + " "; }
            }
        return legalb;
    }
    
    //finds all legal moves for a queen piece, treating it as a bishop and a rook
    @Override
    public String queenMoves(int i, int j) {
        //gets rookMoves
        String legalq = "";
        legalq = legalq + rookMoves(i, j);
        legalq = legalq + bishopMoves(i, j);
        return legalq;        
    }
    
    
    //inDanger checks to make sure each move is legal (Conceptual idea from chesswiki)
    @Override
    public boolean inDanger(int i, int j, int k, int l) {
        int save = board.get(k,l);
        board.put(board.get(i, j),k,l);
        board.remove(i,j);

        int[] king = board.wking;
        int enemy = -1;
        if(status.turn.equals("black")) {
           king = board.bking;
        }

        if(status.turn.equals("black")) {
            enemy = 1;
        }
        int x = king[0];
        int y = king[1];

        //bishop movement
        int count = 1;
        for(int a=-1;a<=1;a+=2) {
            for (int b=-1;b<=1;b+=2) {
                while(board.strictValid(x+(a*count),y+(b*count))) {
                    count++;
                }
                
                if(board.onBoard(x+a*count,y+b*count) && (board.get(x+a*count,y+b*count)==3*enemy 
                   || board.get(x+a*count,y+b*count)==9*enemy)) {
                    board.put(board.get(k,l), i, j);
                    board.remove(k,l);
                    board.put(save, k, l);
                    return false;
                }
                count = 1;
            }
            count = 1;               
        }
        
        //rook movement
        for(int a=-1;a<=1;a+=2) {
            while (board.strictValid(x+a*count,y)) {
                count++;
            }
            if(board.onBoard(x+a*count,y) && (board.get(x+a*count,y)==5*enemy 
                   || board.get(x+a*count,y)==9*enemy)) {
                board.put(board.get(k,l),i,j);
                board.remove(k,l);
                board.put(save, k, l);
                return false;
            }
            count = 1;
            while (board.strictValid(x,y+a*count)) {
                count++;
            }
            if(board.onBoard(x,y+a*count) && (board.get(x,y+a*count)==5*enemy 
                   || board.get(x,y+a*count)==9*enemy)) {
                board.put(board.get(k,l),i,j);
                board.remove(k,l);
                board.put(save,k,l);
                return false;
            }
            count = 1;
        }
        
        //king movement
        for (int a = x-1;a<x+2;a++) {
            for (int b = y-1;b<y+2;b++) {
                if (board.valid(x,y,a,b)) {
                    if(board.get(a,b)==4*enemy) {
                        board.put(board.get(k,l),i,j);
                        board.remove(k,l);
                        board.put(save,k,l);
                        return  false;
                      }
                    }
            }
        }

        //knight movement
        int[] a = new int[] {x-2,x-2,x-1,x-1,x+1,x+1,x+2,x+2};
        int[] b = new int[] {y-1,y+1,y-2,y+2,y-2,y+2,y-1,y+1};
        for (int c = 0;c<a.length;c++) {
            if(board.valid(x,y,a[c],b[c])) {
                if(board.get(a[c],b[c])==2*enemy) {
                    board.put(board.get(k,l),i,j);
                    board.remove(k,l);
                    board.put(save,k,l);
                    return  false;
                }
        }  
        }
        
        //pawnmovement
        int dir = -1*enemy;
        if(board.valid(x,y,x+dir,y-1) && board.get(x+dir,y-1) == 1*enemy) {
            board.put(board.get(k,l),i,j);
            board.remove(k,l);
            board.put(save,k,l);
            return false;
        } else if(board.valid(x,y,x+dir,y+1) && board.get(x+dir,y+1)==1*enemy) {
            board.put(board.get(k,l),i,j);
            board.remove(k,l);
            board.put(save,k,l);
            return false;
        }
        
        //System.out.println("not in check");
        board.put(board.get(k,l),i,j);
        board.remove(k,l);
        board.put(save, k, l);
        return true;
    }
    
    
}
