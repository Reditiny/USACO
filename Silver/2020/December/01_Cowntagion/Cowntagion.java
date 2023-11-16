import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

/**
 * @author Red
 * @version 1.0
 */
public class Cowntagion {
    static int N;
    static List<List<Integer>> graph = new ArrayList<>();
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        N = Integer.parseInt(r.readLine());
        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(r.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        visited = new boolean[N];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        visited[0] = true;
        int ans = 0;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            int count = 0;
            for (int i = 0; i < graph.get(cur).size(); i++) {
                if (!visited[graph.get(cur).get(i)]) {
                    count++;
                    queue.add(graph.get(cur).get(i));
                    visited[graph.get(cur).get(i)] = true;
                }
            }
            // 从一个节点开始感染它的所有邻居需要
            // 1. 自己的感染数至少为 1 + count
            // 2. count 天每天感染一家
            ans += growDays(count + 1) + count;
        }
        pw.println(ans);
        pw.close();
    }

    // 从 1 开始增长到 n 需要的天数
    static int growDays(int n) {
        int count = 0;
        int start = 1;
        while (start < n) {
            start *= 2;
            count++;
        }
        return count;
    }
}