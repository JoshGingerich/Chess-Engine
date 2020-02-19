
package engine;

/*
 * @author joshgingerich
 */
import org.junit.Test;
import static org.junit.Assert.*;

public class minimaxTest {
    
        public minimaxTest() {}
        
        
        @Test
        public void testdepthCheckmate() {
            ChessGame depth1 = new ChessGame();
            depth1.board.arrayBoard = new int[][] {
                {0,0,0,0,4,3,1,5},
                {5,-9,1,1,1,1,1,1},
                {0,0,0,0,0,0,2,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,-1},
                {0,0,0,0,0,0,0,0},
                {0,-1,-1,-1,-1,-1,0,-1},
                {0,0,0,0,-4,-3,-2,-5}};
           // depth1.num_moves++;
            //depth1.turn();
            String result = depth1.choice.alphaBetaWhite("0000",-100,100,4,"max");
            System.out.println(result);
            System.out.println(depth1.choice.positions);
            assertEquals("10701000", result);
        }
        
        // DepthofSearch=1; Tree: Max 0 0 0 ... 1 ... 0 0 0
        @Test
        public void testdepth1() {
            ChessGame depth1 = new ChessGame();
            depth1.board.arrayBoard = new int[][] {
                {5,2,3,9,4,3,2,5},
                {1,1,1,1,0,0,1,1},
                {0,0,0,0,0,1,0,0},
                {0,0,0,0,0,0,-3,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,-1,-1,-1,-1,-1,-1,-1},
                {-5,-2,0,-9,-4,-3,-2,-5}};
            String result = depth1.choice.minimaxBlack("0000",-100,100,4,"max");
            System.out.println(result);
            System.out.println(depth1.choice.positions);
            assertEquals("36470", result);
        }
        
        //DepthofSearch=2; Tree: Max...  -->87
        
        
        
        @Test
        public void testdepth2() {
            ChessGame depth2 = new ChessGame();
            depth2.board.arrayBoard = new int[][] {
                {5,2,3,9,4,3,0,5},
                {1,1,1,1,1,1,1,1},
                {0,0,0,0,0,2,0,0},
                {0,0,0,0,0,0,0,-1},
                {0,0,0,0,0,0,-1,0},
                {0,0,0,0,0,0,0,0},
                {-1,-1,-1,-1,-1,-1,0,0},
                {-5,-2,-3,-9,-4,-3,-2,-5}
            };
            String result = depth2.choice.minimaxWhite("0000",-100,100,2,"max");
            System.out.println(result);
            System.out.println(depth2.choice.positions);
            assertEquals("25461",result);
        }
        
        //381 --> 348 --> 90
        @Test
        public void testdepth2number2() {
            ChessGame depth2number2 = new ChessGame();
            depth2number2.board.arrayBoard = new int[][] {
                {5,2,3,9,4,3,0,5},
                {1,1,1,1,1,1,1,1},
                {0,0,0,0,0,0,0,2},
                {0,0,0,0,0,0,-1,0},
                {0,0,0,0,0,0,-1,0},
                {0,0,0,0,0,0,0,-1},
                {-1,-1,-1,-1,-1,0,0,0},
                {-5,-2,-3,-9,-4,-3,-2,-5}};
                String result = depth2number2.choice.minimaxWhite("0000",-100,100,2,"max");
                System.out.println(result);
                System.out.println(depth2number2.choice.positions);
                assertEquals("27060",result);    
            }
            
        //410 --> 228 --> 134
        @Test
        public void testdepth3() {
            ChessGame depth3 = new ChessGame();
            depth3.board.arrayBoard = new int[][] {
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,2,0,0},
                {0,0,0,0,0,1,0,0},
                {0,0,0,0,0,0,-1,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0-1,-5,0}
            };
            String result = depth3.choice.minimaxWhite("0000",-100,100,3,"max");
            System.out.println(result);
            System.out.println(depth3.choice.positions);
            assertEquals("4556-2",result);
        }
        
        //1188 --> 511 --> 202
        @Test
        public void testdepth4() {
            ChessGame depth4 = new ChessGame();
            depth4.board.arrayBoard = new int[][] {
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,1},
                {1,0,0,0,1,0,1,1},
                {0,1,0,1,0,0,0,-1},
                {0,0,-1,0,0,0,0,0},
                {0,-1,0,-1,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0}
            };
            String result = depth4.choice.minimaxWhite("0000",-100,100,4,"max");
            System.out.println(result);
            System.out.println("number of analyzed: " + depth4.choice.positions);
            assertEquals("26374",result);
        }
        
        //662000 (2.4 sec) --> 259425 (1.5 sec) --> 9476 (.28 sec)
        @Test
        public void testdepth4capture() {
            ChessGame depth4capture = new ChessGame();
            depth4capture.board.arrayBoard = new int[][] {
            {5,2,3,9,4,3,2,5},
            {1,1,0,1,1,1,1,1},
            {0,0,0,0,0,0,0,0},
            {0,0,1,0,0,0,0,0},
            {0,-9,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {-1,-1,-1,-1,-1,-1,-1,-1},
            {-5,-2,-3,0,-4,-3,-2,-5}    
        };
        String result = depth4capture.choice.alphaBetaWhite("0000",-100,100,4,"max");
        System.out.println(result);
        System.out.println("number of analyzed: " + depth4capture.choice.positions);
        assertEquals("32419",result);
        }
        
    
}
