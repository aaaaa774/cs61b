
/**
 * @author q
 * @create 2020-08-27 12:37
 */
public class OffByN {
    int n = 0;
    public OffByN(int N){
        n = N;
    }


    public boolean equalChars(char x, char y){
        return Math.abs(x-y) == n;
    }

}
