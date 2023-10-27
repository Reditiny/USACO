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
public class CowntactTracing {
    static boolean[] cowEndsInfected;
    static int n;
    static int maxT = 250;
    static int maxN = 100;
    static int[] cowX = new int[maxT + 1];
    static int[] cowY = new int[maxT + 1];

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("tracing.in"));
        PrintWriter pw = new PrintWriter("tracing.out");
        StringTokenizer st = new StringTokenizer(r.readLine());
        n = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());
        // 记录最终感染结果
        String s = r.readLine();
        cowEndsInfected = new boolean[n + 1];
        for (int x = 1; x <= n; x++) {
            cowEndsInfected[x] = (s.charAt(x - 1) == '1');
        }
        // 记录接触信息
        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(r.readLine());
            int time = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            cowX[time] = x;
            cowY[time] = y;
        }
        // 枚举所有牛作为 patient zero 以及握手次数 k
        int lowerK = maxT + 1;
        int upperK = 0;
        HashSet<Integer> candidatePatientZero = new HashSet<>();
        for (int i = 1; i <= n; i++) {
            for (int k = 0; k <= maxT + 1; k++) {    // k 可以取 maxT+1（251）时说明最终为 Infinity
                if (consistentWithData(i, k)) {
                    lowerK = Math.min(lowerK, k);
                    upperK = Math.max(upperK, k);
                    candidatePatientZero.add(i);
                }
            }
        }
        pw.print(candidatePatientZero.size() + " " + lowerK + " ");
        if (upperK == maxT + 1) {
            pw.println("Infinity");
        } else {
            pw.println(upperK);
        }
        pw.close();
    }

    /**
     * 判断 patientZero 为传播起点握手 k 次 是否能满足最终感染结果
     */
    static boolean consistentWithData(int patientZero, int k) {
        boolean[] infected = new boolean[maxN + 1];
        int[] numHandshakes = new int[maxN + 1];
        infected[patientZero] = true;
        for (int t = 0; t <= maxT; t++) {
            int x = cowX[t];
            int y = cowY[t];
            if (x > 0) {
                if (infected[x]) {
                    numHandshakes[x]++;
                }
                if (infected[y]) {
                    numHandshakes[y]++;
                }
                if (numHandshakes[x] <= k && infected[x]) {
                    infected[y] = true;
                }
                if (numHandshakes[y] <= k && infected[y]) {
                    infected[x] = true;
                }
            }
        }
        for (int i = 1; i <= n; i++) {
            if (infected[i] != cowEndsInfected[i]) {
                return false;
            }
        }
        return true;
    }
}
