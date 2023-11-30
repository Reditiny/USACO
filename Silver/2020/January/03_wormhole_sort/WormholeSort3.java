import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class WormholeSort3 {
    static int n, m;
    static boolean ordered = true;
    static int[] orders;
    static List<Integer> widths = new ArrayList<>();
    static HashMap<Integer,List<int[]>> graph = new HashMap<>();
    static UnionFind uf;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("wormsort.in"));
        PrintWriter pw = new PrintWriter("wormsort.out");
        StringTokenizer st = new StringTokenizer(r.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        orders = new int[n +  1];
        st = new StringTokenizer(r.readLine());
        for (int i = 1; i <= n; i++) {
            orders[i] = Integer.parseInt(st.nextToken());
            if (i != orders[i]) {
                ordered = false;
            }
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(r.readLine());
            int a =Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph.putIfAbsent(a,new ArrayList<>());
            graph.putIfAbsent(b,new ArrayList<>());
            graph.get(a).add(new int[]{b, w});
            graph.get(b).add(new int[]{a, w});
            widths.add(w);
        }
        // 已经有序
        if (ordered) {
            pw.println(-1);
            pw.close();
            return;
        }
        // 按 weight 从小到大排序
        widths.sort(Integer::compareTo);
        // 二分查找最小的 width
        int left = 0, right = m - 1;
        while (left < right){
            int mid = (left + right) / 2;
            if (isValid(widths.get(mid))){
                left = mid + 1;
            }else {
                right = mid - 1;
            }
        }
        if (isValid(widths.get(left))){
            pw.println(widths.get(left));
        }else {
            pw.println(widths.get(left - 1));
        }
        pw.close();
    }

    /**
     * 检查当前的 minWidth 是否满足条件
     */
    static boolean isValid(int minWidth) {
        uf = new UnionFind(n + 1);
        visited = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                dfs(i, minWidth, uf.find(i));
            }
        }
        // 并查集判断是否连通
        for (int i = 1; i <= n; i++) {
            if (!uf.connected(i, orders[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * 深度优先搜索遍历并标记连通图序号
     */
    static void dfs(int current, int minWidth, int label) {
        if (visited[current]) {
            return;
        }
        visited[current] = true;
        uf.union(current, label);
        for (int[] next : graph.get(current)) {
            if (minWidth <= next[1]) {
                dfs(next[0], minWidth, label);
            }
        }
    }

}

class UnionFind {
    int[] regions;

    public UnionFind(int n) {
        regions = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            regions[i] = i;
        }
    }

    public int find(int x) {
        if (regions[x] == x) {
            return x;
        }
        return regions[x] = find(regions[x]);
    }

    public void union(int x, int y) {
        regions[find(x)] = find(y);
    }

    public boolean connected(int x, int y) {
        return find(x) == find(y);
    }
}