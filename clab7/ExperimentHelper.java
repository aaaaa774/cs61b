/**
 * Created by hug.
 */
public class ExperimentHelper {

    /** Returns the internal path length for an optimum binary search tree of
     *  size N. Examples:
     *  N = 1, OIPL: 0
     *  N = 2, OIPL: 1
     *  N = 3, OIPL: 2
     *  N = 4, OIPL: 4
     *  N = 5, OIPL: 6
     *  N = 6, OIPL: 8
     *  N = 7, OIPL: 10
     *  N = 8, OIPL: 13
     */
    public static int optimalIPL(int N) {
        return (int) (N * optimalAverageDepth(N));
    }

    /** Returns the average depth for nodes in an optimal BST of
     *  size N.
     *  Examples:
     *  N = 1, OAD: 0
     *  N = 5, OAD: 1.2
     *  N = 8, OAD: 1.625
     * @return
     */
    public static double optimalAverageDepth(int N) {
        N = N + 1;
        double height = (Math.log(N) / Math.log(2)) - 1;
        if (height == Math.floor(height)) return calAverageDepth(height) / (N-1);
        double a1 = calAverageDepth(Math.floor(height));
        int tot = 0;
        for (int i = 0; i <= Math.floor(height); i++) {
            tot += Math.pow(2,i);
        }
        a1 += ((N-1) - tot) * (Math.floor(height) + 1);
        return a1 / (N-1);
    }

    private static double calAverageDepth(double h) {
        double aveDepth = 0;
        for (int i = 0; i <= h; i++){
            aveDepth = (aveDepth + i * Math.pow(2,i));
        }
        return aveDepth;
    }

    public static void main(String[] args) {
        double a = Math.log(128)/Math.log(2);
        System.out.println(optimalAverageDepth(4));
        System.out.println(optimalIPL(6));
    }
}
