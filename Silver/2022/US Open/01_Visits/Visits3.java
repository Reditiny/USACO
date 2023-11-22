import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


/**
 * @author Red
 * @version 1.0
 */
public class Visits3 {
    static int n;
    // 边集 [起点牛，目标牛，拜访时间]
    static List<List<Integer>> edges = new ArrayList<>();
    // 并查集 unionFind[i] 表示第 i 头牛所在集合
    static int[] unionFind;
    static long ans = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        n = Integer.parseInt(r.readLine());
        unionFind = new int[n];
        for (int i = 0; i < n; i++) {
            unionFind[i] = i;
            StringTokenizer st = new StringTokenizer(r.readLine());
            int nextCow = Integer.parseInt(st.nextToken()) - 1;
            int time = Integer.parseInt(st.nextToken());
            List<Integer> edge = new ArrayList<>();
            edge.add(i);
            edge.add(nextCow);
            edge.add(time);
            edges.add(edge);
        }
        // Kruskal's algorithm 克鲁斯卡尔算法求最小生成树
        // 1. 获取所有边的集合
        // 2. 按边的权重从小到大排序
        // 3. 从权重最小的边开始遍历，如果边两个顶点不在同一个集合中，就将这两个顶点合并到同一个集合中
        // 4. 判断两点是否在同一集合内使用并查集

        // 本题不是求最小生成树而是最大生成树
        // 算法思路一致，只是将边的权重从大到小排序
        edges.sort((edge1, edge2) -> edge2.get(2) - edge1.get(2));
        for(int i = 0; i < edges.size(); i++){
            List<Integer> edge = edges.get(i);
            if(!connected(edge.get(0), edge.get(1))){
                union(edge.get(0), edge.get(1));
                ans += edge.get(2);
            }
        }
        pw.println(ans);
        pw.close();
    }

    /**
     * 并查集求 i 点所在集合
     */
    static int find(int i){
        if(i != unionFind[i]){
            unionFind[i] = find(unionFind[i]);
        }
        return unionFind[i];
    }

    /**
     * 并查集连接两点所在集合
     */
    static void union(int i, int j){
        unionFind[find(i)] = find(j);
    }

    /**
     * 并查集判断两点是否在同一集合
     */
    static boolean connected(int i, int j){
        return find(i) == find(j);
    }

}
