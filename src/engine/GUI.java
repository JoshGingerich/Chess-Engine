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

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class GUI extends JPanel implements MouseMotionListener, MouseListener {
    static int mousex, mousey, newMousex, newMousey;
    int x,y;
    int size = 80;
    ChessGame init;
    
    public GUI(ChessGame init) {
        setLayout(null);
        repaint();
        this.init = init;
        
    }
    public void paintComponent(Graphics d) {
        super.paintComponent(d);
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
        this.setBackground(Color.GRAY);
        for(int a=0;a<8;a++) {
            for(int b=0;b<8;b+=2) {
                if(a%2==0){
                d.setColor(Color.WHITE);
                d.fillRect(10+(a*size),10+(b*size),size,size);
                } else {
                    d.setColor(Color.WHITE);
                    d.fillRect(90+((a-1)*size),90+(b*size),80,size);
                }
                
            }
        }
        //Code for image capture modified from https://onedrive.live.com/?authkey=%21AH2QSNVZGDSTeu4&cid=D4629BC8D856F7D5&id=D4629BC8D856F7D5%21218&parId=D4629BC8D856F7D5%21217&o=OneUp
        Image pieces = new ImageIcon("pieces.png").getImage();
        for(int i=0;i<8;i++) {
            for(int j=0;j<8;j++) {
                int a=-1,b=-1;
                switch(init.board.get(i,j)) {
                    case 1: a=5; b=0; break;
                    case 2: a=3; b=0; break;
                    case 3: a=2; b=0; break;
                    case 4: a=0; b=0; break;
                    case 5: a=4; b=0; break;
                    case 9: a=1; b=0; break;
                    case -1: a=5; b=1; break;
                    case -2: a=3; b=1; break;
                    case -3: a=2; b=1; break;
                    case -4: a=0; b=1; break;
                    case -5: a=4; b=1; break;
                    case -9: a=1; b=1; break;
                }
                if(a!=-1 || b!=-1) {
                    d.drawImage(pieces,10+(j*size),650-(i*size),(j+1)*size,650-((i+1)*size),a*70,(b+1)*70,(a+1)*70,b*70,this);
                }
            }
        }

    }
    
    //Mouse Event Code slightly modified from same url source as image capture code
    @Override
    public void mouseMoved(MouseEvent e) {}
    @Override
    public void mousePressed(MouseEvent e) {
     if(e.getX()<650 && e.getY()<650) {
         mousex=e.getX();
         mousey=e.getY();
         repaint();
     }
    }
    @Override
    public void mouseReleased(MouseEvent e) {
     if(e.getX()<650 && e.getY()<650) {
         newMousex=e.getX();
         newMousey=e.getY();
         String move = "";
         if (e.getButton()==MouseEvent.BUTTON1) {
             move = "" + (7-(mousey/size)) + mousex/size + (7-(newMousey/size)) + newMousex/size;
         }
         //System.out.println(ChessGame.moves(ChessGame.turn).contains(move));
         if(init.legals.moves(init.status.turn).contains(move)) {
            init.player.userMove(move);
         }
         repaint();
     }
    }
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    @Override
    public void mouseDragged(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
}
