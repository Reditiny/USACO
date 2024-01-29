import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class RotateAndShift2 {
    static int N, K, T;
    static int[] A;
    static int[] ans;
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(r.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(r.readLine());
        ans = new int[N];
        A = new int[K + 1];
        for (int i = 0; i < K; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        A[K] = N;

        for (int i = 0; i < K; i++) {
            for (int j = A[i]; j < A[i + 1]; j++) {
                // j - A[i] + 1 时 j 会跳到 A[i + 1]
                int timeStay = j - A[i];
                int timeLeftAfterJump = T - (timeStay + 1);
                if (timeLeftAfterJump >= 0) {
                    int jumpDistance = A[i + 1] - A[i];
                    int jumpTimes = 1 + timeLeftAfterJump / jumpDistance;
                    int endingPosition = (j + jumpTimes * jumpDistance) % N;
                    ans[endingPosition] = j;
                } else {
                    ans[j] = j;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++){
            sb.append(ans[i]).append(" ");
        }
        pw.println(sb.toString().trim());
        pw.close();

        pw.close();
    }
}