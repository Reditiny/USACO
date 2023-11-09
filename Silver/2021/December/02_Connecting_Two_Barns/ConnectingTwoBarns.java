import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class ConnectingTwoBarns {
    static int t, n, m;
    static List<List<Integer>> graph;
    // 记录每个节点所在的连通图 -1 表示未访问 0 1 2 ... 表示连通图的编号
    static int[] visited;
    // 记录每个连通图的节点集合
    static List<List<Integer>> groups;


    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        t = Integer.parseInt(r.readLine());
        for (int i = 0; i < t; i++) {
            StringTokenizer st = new StringTokenizer(r.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            graph = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                graph.add(new ArrayList<>());
            }
            for (int j = 0; j < m; j++) {
                st = new StringTokenizer(r.readLine());
                int a = Integer.parseInt(st.nextToken()) - 1;
                int b = Integer.parseInt(st.nextToken()) - 1;
                graph.get(a).add(b);
                graph.get(b).add(a);
            }
            // 遍历图 遍历的同时确定每个点所在的连通图
            // 例如 [0,0,1,0,0,1,1,2,2,3]  相同的数值表示在同一个连通图内
            visited = new int[n];
            Arrays.fill(visited, -1);
            int groupNum = 0;
            for (int j = 0; j < n; j++) {
                if (visited[j] == -1) {
                    dfsMarkGroupNum(j, groupNum);
                    groupNum++;
                }
            }
            // 构造每个连通图的节点集合且节点为升序
            // 例如 visited = [0,0,1,0,0,1,1,2,2,3] 可以得到 groups = [[0,1,3,4],[2,5,6],[7,8],[9]]
            groups = new ArrayList<>();
            for (int j = 0; j < groupNum; j++) {
                groups.add(new ArrayList<>());
            }
            for (int j = 0; j < n; j++) {
                groups.get(visited[j]).add(j);
            }
            // 计算每个连通图到两个barn所在连通图的距离
            long[] distanceFromBarn1 = distanceFromBarn(groups.get(visited[0]));
            long[] distanceFromBarn2 = distanceFromBarn(groups.get(visited[n - 1]));
            long ans = Long.MAX_VALUE;
            for (int j = 0; j < groupNum; j++) {
                long cost = distanceFromBarn1[j] * distanceFromBarn1[j] + distanceFromBarn2[j] * distanceFromBarn2[j];
                ans = Math.min(ans, cost);
            }
            pw.println(ans);
        }
        pw.close();
    }

    /**
     * dfs遍历的同时给节点标记连通图编号
     */
    static void dfsMarkGroupNum(int i, int groupNum) {
        if (visited[i] != -1) {
            return;
        }
        visited[i] = groupNum;
        for (int j = 0; j < graph.get(i).size(); j++) {
            dfsMarkGroupNum(graph.get(i).get(j), groupNum);
        }
    }

    /**
     * 计算每个连通图到指定barn的距离
     * 可以使用双指针因为所有连通图都是升序的
     */
    static long[] distanceFromBarn(List<Integer> barn) {
        long[] distances = new long[groups.size()];
        Arrays.fill(distances, Integer.MAX_VALUE);
        int barnIndex = 0;
        for (int i = 0; i < n; i++) {
            int dist = Math.abs(barn.get(barnIndex) - i);
            while (barnIndex < barn.size() - 1 && Math.abs(barn.get(barnIndex + 1) - i) < dist) {
                barnIndex++;
            }
            distances[visited[i]] = Math.min(dist, distances[visited[i]]);
        }
        return distances;
    }


}