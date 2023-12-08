import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

/**
 * @author Red
 * @version 1.0
 */
public class RedistributingGifts {
    static int n;
    // 牛 -> 喜欢的礼物(包括自己本身的) 正向边
    static List<List<Integer>> graph = new ArrayList<>();
    // 礼物 -> 喜欢自己的牛 反向边
    static List<List<Integer>> revGraph = new ArrayList<>();
    static boolean[] visited1;
    static boolean[] visited2;
    static HashSet<Integer> stronglyConnectedComponents = new HashSet<>();
    static int[] ans;

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        n = Integer.parseInt(r.readLine());
        visited1 = new boolean[n];
        visited2 = new boolean[n];
        ans = new int[n];
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
            revGraph.add(new ArrayList<>());
        }
        // 牛->礼物 作为图中的有向边
        // 记录每个牛喜欢的礼物 同时记录反向边
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(r.readLine());
            for (int j = 0; j < n; j++) {
                graph.get(i).add(Integer.parseInt(st.nextToken()) - 1);
            }
            while (graph.get(i).get(graph.get(i).size() - 1) != i) {
                graph.get(i).remove(graph.get(i).size() - 1);
            }
            for (int j : graph.get(i)) {
                revGraph.get(j).add(i);
            }
        }
        // 只有当两头牛都有边到对方的礼物才能满足要求
        // 所以需要找到强连通分量
        Kosaraju();
        for (int i : ans) {
            pw.println(i + 1);
        }
        pw.close();
    }

    /**
     * Kosaraju's 算法计算强连通分量
     * 算法详解参见 https://blog.csdn.net/level_code/article/details/124859262 https://www.modb.pro/db/180268
     * 1. 根据原图dfs，记录dfs结束的顺序，并将顺序反转
     * 2. 反向图dfs，从起点开始，记录所有能到达的点，这些点就是一个强连通分量
     * 3. 一个强连通分量内牛可以直接获得自己喜欢的礼物
     */
    static void Kosaraju() {
        // 1 原图进行dfs得到顺序后反转
        List<Integer> order = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!visited1[i]) {
                dfs1(i, order);
            }
        }
        Collections.reverse(order);
        // 2 反向图进行dfs,每次可以得到一个强连通分量
        for (int i : order) {
            if (!visited2[i]) {
                dfs2(i);
            }
            // 3 对于强连通分量 如果自己喜欢的礼物在强连通分量内，那么就可以直接获得
            for (int k : stronglyConnectedComponents) {
                for (int j : graph.get(k)) {
                    if (stronglyConnectedComponents.contains(j)) {
                        ans[k] = j;
                        break;
                    }
                }
            }
            stronglyConnectedComponents.clear();
        }
    }

    static void dfs1(int i, List<Integer> order) {
        if (visited1[i]) {
            return;
        }
        visited1[i] = true;
        for (int j : graph.get(i)) {
            dfs1(j, order);
        }
        order.add(i);
    }

    static void dfs2(int i) {
        if (visited2[i]) {
            return;
        }
        visited2[i] = true;
        stronglyConnectedComponents.add(i);
        for (int j : revGraph.get(i)) {
            dfs2(j);
        }
    }
}