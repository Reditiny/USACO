import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class ShellGame {
    static int shellCount = 3;
    static int n;
    static int[] guesses;
    static int[][] swaps;

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("shell.in"));
        PrintWriter pw = new PrintWriter("shell.out");
        n = Integer.parseInt(r.readLine());
        swaps = new int[n][2];
        guesses = new int[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(r.readLine());
            swaps[i][0] = Integer.parseInt(st.nextToken()) - 1;
            swaps[i][1] = Integer.parseInt(st.nextToken()) - 1;
            guesses[i] = Integer.parseInt(st.nextToken()) - 1;
        }
        int ans = 0;
        for (int i = 0; i < shellCount; i++) {
            ans = Math.max(ans, score(i));
        }
        pw.println(ans);
        pw.close();

    }

    /**
     * 计算初始位置为 initialLocation 时的分数
     */
    static int score(int initialLocation) {
        boolean[] shells = new boolean[shellCount];
        int score = 0;
        shells[initialLocation] = true;
        for (int i = 0; i < n; i++) {
            swap(shells, swaps[i][0], swaps[i][1]);
            if (shells[guesses[i]]) {
                score++;
            }
        }
        return score;
    }

    static void swap(boolean[] shells, int i, int j) {
        boolean temp = shells[i];
        shells[i] = shells[j];
        shells[j] = temp;
    }
}