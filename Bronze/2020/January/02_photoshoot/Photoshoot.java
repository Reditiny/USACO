import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class Photoshoot {
    static int N;
    static int[] a;
    static int[] b;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("photo.in"));
        PrintWriter pw = new PrintWriter("photo.out");
        N = Integer.parseInt(r.readLine());
        StringTokenizer st = new StringTokenizer(r.readLine());
        a = new int[N];

        b = new int[N - 1];
        for (int i = 0; i < N - 1; i++) {
            b[i] = Integer.parseInt(st.nextToken());
        }
        // N 个未知数 N - 1 个方程
        // 可以得到无穷多解
        // b[i] = a[i] + a[i + 1]
        // a[i] = b[i - 1] - a[i - 1]
        // 当确定a[0]时即可确定所有的 a[i]
        // 1 <= a[0] <= N
        for (int a0 = 1; a0 <= N; a0++) {
            makeAByA0(a0);
            if (checkA()) {
                break;
            }
        }
        StringBuilder ans = new StringBuilder();
        for (int j = 0; j < N; j++) {
            ans.append(a[j]).append(" ");
        }
        pw.println(ans.substring(0, ans.length() - 1));
        pw.close();
    }

    /**
     * 根据 a[0] 计算出所有的 a[i]
     */
    static void makeAByA0(int a0) {
        a[0] = a0;
        for (int i = 1; i < N; i++) {
            a[i] = b[i - 1] - a[i - 1];
        }
    }

    /**
     * 检查 a[i] 是否满足条件
     */
    static boolean checkA() {
        visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            if (a[i] <= 0 || a[i] > N || visited[a[i] - 1]) {
                return false;
            }
            visited[a[i] - 1] = true;
        }
        return true;
    }
}