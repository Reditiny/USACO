import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class TheBovineShuffle2 {

    static int n;
    static int[] nextPosition;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("shuffle.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("shuffle.out"));
        n = Integer.parseInt(r.readLine());
        StringTokenizer st = new StringTokenizer(r.readLine());
        nextPosition = new int[n];
        for (int i = 0; i < n; i++) {
            nextPosition[i] = Integer.parseInt(st.nextToken()) - 1;
        }
        visited = new boolean[n];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                ans += countCycleSize(i);
            }
        }
        pw.println(ans);
        pw.close();
        r.close();
    }


    /**
     * 从位置 i 开始寻找环同时标记访问过的位置避免重复访问
     * 当遍历到环的入口时，计算出环的大小
     */
    public static int countCycleSize(int i) {
        int cur = i;
        HashSet<Integer> cycleSet = new HashSet<Integer>();
        visited[cur] = true;
        cycleSet.add(cur);
        while (true) {
            // 如果 cur 已经在 set 中，说明此处为环的入口
            cur = nextPosition[cur];
            if(cycleSet.contains(cur)){
                break;
            }
            // 如果 cur 已经被访问过，但是却没有在 set 中
            // 说明后续的点都已经访问过，环内的大小也已经计算过了
            if(visited[cur]){
                return 0;
            }
            cycleSet.add(cur);
            visited[cur] = true;
        }
        // 此时 cur 一定在环中，找到环的大小
        int count = 1;
        int next = nextPosition[cur];
        while (next != cur) {
            count++;
            next = nextPosition[next];
        }
        return count;
    }
}

