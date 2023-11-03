import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Red
 * @version 1.0
 */
public class Lifeguards3 {
    static List<List<Integer>> liveGuards;
    static int[] times = new int[1001];
    static int covered = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("lifeguards.in"));
        PrintWriter pw = new PrintWriter("lifeguards.out");
        int n = Integer.parseInt(r.readLine());
        liveGuards = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] s = r.readLine().split(" ");
            List<Integer> liveGuard = new ArrayList<>();
            liveGuard.add(Integer.parseInt(s[0]));
            liveGuard.add(Integer.parseInt(s[1]));
            liveGuards.add(liveGuard);
            for (int t = liveGuard.get(0); t < liveGuard.get(1); t++) {
                if (times[t] == 0) {
                    covered++;
                }
                times[t]++;
            }
        }
        int ans = 0;
        // 依次计算开除救生员 i 之后可以覆盖的时间
        for (int i = 0; i < liveGuards.size(); i++) {
            ans = Math.max(ans, coverTimeMissOne(i));
            if (ans == covered) {
                break;
            }
        }
        pw.println(ans);
        pw.close();
    }

    /**
     * 计算开除指定救生员后可以覆盖的时间
     */
    static int coverTimeMissOne(int missed) {
        int[] timesCopy = times.clone();
        int coveredCopy = covered;
        for (int i = liveGuards.get(missed).get(0); i < liveGuards.get(missed).get(1); i++) {
            if (timesCopy[i] == 1) {
                coveredCopy--;
            }
            timesCopy[i]--;
        }
        return coveredCopy;
    }
}
