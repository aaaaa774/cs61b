import org.junit.Test;

import java.util.Deque;

/**
 * @author q
 * @create 2020-08-31 15:50
 */
public class TestBubbleGrid {
    @Test
    public void testBubble(){
        int[][] bubbles = new int[][]{{1,0,0},{1,1,1},{1,1,1}};
        for (int[] i : bubbles){
            for (int j : i){
                System.out.print(j + " ");
            }
            System.out.println();
        }
        BubbleGrid bg = new BubbleGrid(bubbles);
        int[][] darts = new int[][]{{1,2},{1,1},{1,0}};
        //int[][] darts = new int[][]{{1,0}};
        Deque<Integer[]> a = bg.popBubbles(darts);
        for (Integer[] i : a){
            for (Integer j : i){
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }

}
