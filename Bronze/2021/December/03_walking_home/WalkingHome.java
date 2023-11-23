import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class WalkingHome {
    static int T, N, K;
    static char[][] grid;
    static int ans;

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        T = Integer.parseInt(r.readLine());
        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(r.readLine());
            N = Integer.parseInt(st.nextToken());
            grid = new char[N][N];
            K = Integer.parseInt(st.nextToken());
            for (int j = 0; j < N; j++) {
                grid[j] = r.readLine().toCharArray();
            }
            ans = 0;
            if (K == 1) {
                ans = pathCountWithK1();
            } else if (K == 2) {
                ans = pathCountWithK1() + pathCountWithK2();
            } else if (K == 3) {
                ans = pathCountWithK1() + pathCountWithK2() + pathCountWithK3();
            }
            pw.println(ans);
        }
        pw.close();
    }

    /**
     * K == 1 时转弯的点只能在左下角和右上角
     */
    static int pathCountWithK1() {
        int ans = 0;
        int valid1 = 1;
        int valid2 = 1;
        for (int i = 0; i < N; i++) {
            if (grid[0][i] == 'H' || grid[i][N - 1] == 'H') {
                valid1 = 0;
            }
            if (grid[N - 1][i] == 'H' || grid[i][0] == 'H') {
                valid2 = 0;
            }
        }
        return ans + valid1 + valid2;
    }

    /**
     * K == 2 时转弯的两个点只能在对边上
     */
    static int pathCountWithK2() {
        int ans = 0;
        for (int j = 1; j < N - 1; j++) {
            int valid = 1;
            for (int i = 0; i < N; i++) {
                if (grid[i][j] == 'H') {
                    valid = 0;
                }
                if (i < j && grid[0][i] == 'H') {
                    valid = 0;
                }
                if (i > j && grid[N - 1][i] == 'H') {
                    valid = 0;
                }
            }
            ans += valid;
        }
        for (int i = 1; i < N - 1; i++) {
            int valid = 1;
            for (int j = 0; j < N; j++) {
                if (grid[i][j] == 'H') {
                    valid = 0;
                }
                if (j < i && grid[j][0] == 'H') {
                    valid = 0;
                }
                if (j > i && grid[j][N - 1] == 'H') {
                    valid = 0;
                }
            }
            ans += valid;
        }
        return ans;
    }

    /**
     * K == 3 时转弯的三个点，两个点在对边上，另一个点在这两点中间
     */
    static int pathCountWithK3() {
        int ans = 0;
        for (int i = 1; i < N - 1; i++) {
            for (int j = 1; j < N - 1; j++) {
                int valid = 0;
                if (grid[i][j] == '.') {
                    valid = 1;
                }
                for (int a = 0; a < N; a++) {
                    if (a <= i && grid[a][j] == 'H') {
                        valid = 0;
                    }
                    if (a >= i && grid[a][N - 1] == 'H') {
                        valid = 0;
                    }
                    if (a <= j && grid[0][a] == 'H') {
                        valid = 0;
                    }
                    if (a >= j && grid[i][a] == 'H') {
                        valid = 0;
                    }
                }
                ans += valid;
                valid = 0;
                if (grid[i][j] == '.') {
                    valid = 1;
                }
                for (int a = 0; a < N; a++) {
                    if (a <= i && grid[a][0] == 'H') {
                        valid = 0;
                    }
                    if (a >= i && grid[a][j] == 'H') {
                        valid = 0;
                    }
                    if (a <= j && grid[i][a] == 'H') {
                        valid = 0;
                    }
                    if (a >= j && grid[N - 1][a] == 'H') {
                        valid = 0;
                    }
                }
                ans += valid;
            }
        }
        return ans;
    }
}