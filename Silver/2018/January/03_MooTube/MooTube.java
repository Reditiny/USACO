import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class MooTube {
    static int N, Q;
    static List<List<List<Integer>>> graph = new ArrayList<>();
    static boolean[] visited;
    static int k, v;
    static int ans;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader("mootube.in"));
        PrintWriter pw = new PrintWriter("mootube.out");
        StringTokenizer st = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(reader.readLine());
            int p = Integer.parseInt(st.nextToken()) - 1;
            int q = Integer.parseInt(st.nextToken()) - 1;
            int r = Integer.parseInt(st.nextToken());
            List<Integer> edge1 = new ArrayList<>();
            edge1.add(q);
            edge1.add(r);
            graph.get(p).add(edge1);
            List<Integer> edge2 = new ArrayList<>();
            edge2.add(p);
            edge2.add(r);
            graph.get(q).add(edge2);
        }
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(reader.readLine());
            k = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken()) - 1;
            visited = new boolean[N];
            ans = 0;
            dfs(v);
            pw.println(ans);
        }
        pw.close();
    }

    /**
     * 深度优先搜索遍历相关度足够的节点
     */
    static void dfs(int start) {
        if (visited[start]) {
            return;
        }
        visited[start] = true;
        for (int i = 0; i < graph.get(start).size(); i++) {
            if (graph.get(start).get(i).get(1) >= k && !visited[graph.get(start).get(i).get(0)]) {
                ans++;
                dfs(graph.get(start).get(i).get(0));
            }
        }
    }

}