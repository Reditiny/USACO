import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class MilkPails_Memo {
    static int X, Y, M;
    static int result = 0;
    static boolean[] visited;
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("pails.in"));
        PrintWriter pw = new PrintWriter("pails.out");
        StringTokenizer st = new StringTokenizer(r.readLine());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[M + 1];
        dfs(0);
        pw.println(result);
        pw.close();
    }

    /**
     * 深度优先搜索查找最大值
     * 通过 visited 数组记录已经访问过的状态，避免重复访问
     */
    static void dfs(int acc) {
        if (acc > M || visited[acc]) {
            return;
        }
        visited[acc] = true;
        result = Math.max(result, acc);
        dfs(acc + X);
        dfs(acc + Y);
    }
}
