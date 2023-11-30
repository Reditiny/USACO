import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

/**
 * @author Red
 * @version 1.0
 */
public class RedistributingGifts3 {
    static int N;
    // 牛 -> 喜欢的礼物(包括自己本身的)
    static List<List<Integer>> preferences;
    // reachable[i][j] 表示牛i能否获得牛j的礼物
    static boolean[][] reachable;

    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        N = Integer.parseInt(r.readLine());
        preferences = new ArrayList<>();
        preferences.add(new ArrayList<>());
        for (int i = 1; i <= N; i++) {
            ArrayList<Integer> p = new ArrayList<>();
            StringTokenizer st = new StringTokenizer(r.readLine());
            for (int j = 0; j < N; j++) {
                p.add(Integer.parseInt(st.nextToken()));
            }
            while (p.get(p.size() - 1) != i) {
                p.remove(p.size() - 1);
            }
            preferences.add(p);
        }
        reachable = new boolean[N + 1][N + 1];
        for (int i=1;i<=N; i++) {
            reachable[i][i] = true;
        }
        // 遍历每一头牛，依次判断能否和自己喜欢的礼物构成环
        // 如果能构成环，那么可以获得自己喜欢的礼物
        for(int i=1; i<=N; i++) {
            for (int p : preferences.get(i)) {
                if (reachable[i][p]){
                    pw.println(p);
                    break;
                }
                visited = new boolean[N + 1];
                if (findCycle(i, p)) {
                    pw.println(p);
                    break;
                }
            }
        }
        pw.close();
    }

    /**
     * 判断是否能从 source 到 current 是否能构成环
     */
    static boolean findCycle(int source, int current) {
        // 找到环或者之前的遍历中已经确定可达
        if (source == current || reachable[source][current]) {
            return true;
        }
        if (visited[current]) {
            return false;
        }
        visited[current] = true;
        for (int next : preferences.get(current)) {
            if (findCycle(source, next)) {
                // source 有路径到 current，current -> next
                // 如果 source 和 next 构成环，那么 current 和 next 一定构成环
                reachable[current][next] = true;
                return true;
            }
        }
        reachable[current][current] = false;
        return false;
    }
}