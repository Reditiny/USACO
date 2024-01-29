import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * @author red
 */
public class ShellGameDict {
    static int N;
    static int[][] rounds;
    static String[] shellIds = {"", "shell1", "shell2", "shell3"};
    static Map<String, Integer> scores = new HashMap<>();
    static int ans = 0;
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("shell.in"));
        PrintWriter pw = new PrintWriter("shell.out");

        N = Integer.parseInt(r.readLine().trim());
        rounds = new int[N][3];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(r.readLine().trim());
            for (int j = 0; j < 3; j++) {
                rounds[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        scores.put("shell1", 0);
        scores.put("shell2", 0);
        scores.put("shell3", 0);
        // 交换 round[0] 与 round[1] 并更新分数
        for (int[] round : rounds) {
            String temp = shellIds[round[0]];
            shellIds[round[0]] = shellIds[round[1]];
            shellIds[round[1]] = temp;
            scores.put(shellIds[round[2]], scores.get(shellIds[round[2]]) + 1);
            ans = Math.max(ans, scores.get(shellIds[round[2]]));
        }
        pw.println(ans);
        pw.close();
    }
}
