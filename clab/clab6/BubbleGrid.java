import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * @author q
 * @create 2020-08-31 14:44
 */
public class BubbleGrid {
    int[][][] parent;
    int[][] bubbles;
    int size = 0;

    public BubbleGrid(int[][] in){
        bubbles = in;
        int rows = in.length;
        int columns = in[0].length;
        parent = new int[rows][columns][2];
    }

    public int[] parent(int vertex[]){
        return parent[vertex[0]][vertex[1]];
    }

    public int[] find(int vertex[]){
        while(parent(vertex)[0] >= 0 && parent(vertex)[1] >= 0){
            int a = parent(vertex)[0];
            int b = parent(vertex)[1];
            vertex[0] = a;
            vertex[1] = b;
        }
        return vertex;
    }

    public boolean isConnected(int[] vertex1, int[] vertex2){
        if (find(vertex1)[0] == find(vertex2)[0]
                && find(vertex1)[1] == find(vertex2)[1]) return true;
        return false;
    }

    // tie vertex1 to vertex2
    public void tie(int[] vertex1, int[] vertex2){
        int[] a = Arrays.copyOf(vertex1,vertex1.length);
        int[] b = Arrays.copyOf(vertex2,vertex2.length);
        if (isConnected(a,b)) return;
        parent(vertex1)[0] = vertex2[0];
        parent(vertex1)[1] = vertex2[1];
        parent(find(vertex2))[0] -= 1;
        parent(find(vertex2))[1] -= 1;
    }

    // union all
    public void union(int grid[][]){
        // topmost
        for (int i = 0; i < grid[0].length; i++){
            if (grid[0][i] == 1) {
                for (int j = i+1; j < grid[0].length; j++){
                    if (grid[0][j] == 1) {
                        tie(new int[]{0, j}, new int[]{0, i});
                    }
                }
                break;
            }
        }
        for (int i = 1; i < grid.length; i++){
            for (int j = 0; j < grid[i].length; j++){
                if (grid[i][j] == 1){
                    if (grid[i-1][j] == 1){
                        tie(new int[]{i,j},new int[]{i-1,j});
                        continue;
                    } else if (j - 1 >= 0 && grid[i][j-1] == 1){
                        tie(new int[]{i,j},new int[]{i,j-1});
                        continue;
                    } else if (j + 1 < grid[i].length && grid[i][j+1] == 1){
                        tie(new int[]{i,j},new int[]{i,j+1});
                        continue;
                    }
                }
            }
        }
    }

    public void iniParent(){
        for (int i = 0; i < parent.length; i++){
            for (int j = 0; j < parent[0].length; j++){
                for (int k = 0; k < 2; k++){
                    parent[i][j][k] = -1;
                }
            }
        }
    }


    public Deque popBubbles(int[][] darts){
        int times = 0;
        Deque<Integer[]> deque = new ArrayDeque<>();
        for (int shoot[] : darts){
            iniParent();
            if (bubbles[shoot[0]][shoot[1]] == 0) continue;
            bubbles[shoot[0]][shoot[1]] = 0;
            int index = -1;
            for (int i = 0; i < bubbles[0].length; i++){
                if (bubbles[0][i] == 1) {
                    index = i;
                    break;
                }
            }
            if (index == -1) {
                int num = 0;
                for (int i = 0; i < bubbles.length; i++){
                    for (int j = 0; j < bubbles[0].length; j++){
                        num++;
                    }
                }
                deque.addLast(new Integer[]{times, num});
                //System.out.println(deque.toString());
                return deque;
            }
            union(bubbles);
            int n = 0;
            for (int i = 0; i < bubbles.length; i++){
                for (int j = 0; j < bubbles[0].length; j++){
                    if (bubbles[i][j] != 0) {
                        int[] r = find(new int[]{0,index});
                        parent[r[0]][r[1]] = new int[]{-999, -999};
                        if (!isConnected(new int[]{0, index}, new int[]{i, j})) {
                            n++;
                            bubbles[i][j] = 0;
                        }
                    }
                }
            }
            if (n != 0) {
                times++;
                deque.addLast(new Integer[]{times, n});
            }
        }
        return deque;
    }
}
