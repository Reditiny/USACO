import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class MilkFactory {
    static List<List<Integer>> graph = new ArrayList<List<Integer>>();

    static HashSet<Integer> targetStations = new HashSet<Integer>();

    static int ans;

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("factory.in"));
        PrintWriter pw = new PrintWriter("factory.out");
        int n = Integer.parseInt(r.readLine());
        ans = n;
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<Integer>());
            targetStations.add(i);
        }

        for (int i = 1; i < n; i++) {
            StringTokenizer st = new StringTokenizer(r.readLine());
            int station1 = Integer.parseInt(st.nextToken()) - 1;
            int station2 = Integer.parseInt(st.nextToken()) - 1;
            graph.get(station1).add(station2);
        }
        // 对于每个工厂，都进行一次广度优先搜索，得到从该工厂可以到达的所有工厂
        // 最终的交集即为目标工厂
        for (int i = 0; i < n; i++) {
            HashSet<Integer> reachedStation = bfs(i);
            targetStations.retainAll(reachedStation);
            if (targetStations.size() == 0) {
                break;
            }
        }
        if (targetStations.size() == 0) {
            pw.println("-1");
        } else {
            targetStations.forEach(station -> ans = Math.min(ans, station));
            pw.println(ans + 1);
        }
        pw.close();
    }

    /**
     * 从 start 开始进行广度优先搜索
     * 返回值为从 start 开始可以到达的所有工厂
     */
    static HashSet<Integer> bfs(int start) {
        HashSet<Integer> visited = new HashSet<Integer>();
        visited.add(start);
        List<Integer> queue = new ArrayList<Integer>();
        queue.add(start);
        while (!queue.isEmpty()) {
            int cur = queue.remove(0);
            for (int neighbor : graph.get(cur)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        return visited;
    }
}
