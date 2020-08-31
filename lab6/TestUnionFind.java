import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author q
 * @create 2020-08-31 1:56
 */
public class TestUnionFind {
    @Test
    public void testUnion(){
        /* for no path compression
        UnionFind uf = new UnionFind(10);
        uf.union(0,1);
        uf.union(2,1);
        System.out.println(uf.connected(0,2));;
        uf.union(7,8);
        uf.union(9,8);
        uf.union(0,7);
        System.out.println(uf.parent[1]);
        System.out.println(uf.parent[8]);
        System.out.println(uf.connected(0,5));
         */
        UnionFind uf = new UnionFind(10);
        uf.union(0,1);
        uf.union(1,2);
        System.out.println(uf.parent[4]);
        uf.union(2,3);
        System.out.println(uf.parent[1]);
    }

}
