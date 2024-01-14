import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class RotateAndShift3 {
    static int N, K, T;
    static int[] A;
    static int[] pos;
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(r.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(r.readLine());
        A = new int[K];
        for (int i = 0; i < K; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        pos = new int[N];
        for (int i = 0; i < N; i++) {
            pos[i] = i;
        }

        int period = 0;
        Set<String> visited = new HashSet<>();

        while (period < T) {
            String curPos = posToString();
            if (visited.contains(curPos)) {
                break;
            }
            visited.add(curPos);
            rotateAndShift();
            period++;
        }

        T %= period;
        while (T > 0) {
            rotateAndShift();
            T--;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++){
            sb.append(pos[i]).append(" ");
        }
        pw.println(sb.toString().trim());
        pw.close();
    }

    private static void rotateAndShift() {
        int temp = pos[A[A.length - 1]];
        for (int i = A.length - 1; i > 0; i--) {
            pos[A[i]] = pos[A[i - 1]];
        }
        pos[A[0]] = temp;
        for (int i = 0; i < A.length; i++) {
            A[i] = (A[i] + 1) % pos.length;
        }
    }

    /**
     * 将 pos 数组转换为字符串，节省存入 HashSet 的空间
     * 如果没有这个函数，会导致内存超限
     */
    private static String posToString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++){
            sb.append(pos[i]);
        }
        return sb.toString();
    }
}
