import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.*;

/**
 * @author Red
 * @version 1.0
 */
public class Hoofball {
    static int N;
    static int[] cows;
    static int[] receiveCount;
    static int[] nextCow;

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("hoofball.in"));
        PrintWriter pw = new PrintWriter("hoofball.out");
        N = Integer.parseInt(r.readLine());
        cows = new int[N];
        receiveCount = new int[N];
        nextCow = new int[N];
        StringTokenizer st = new StringTokenizer(r.readLine());
        for (int i = 0; i < N; i++) {
            cows[i] = Integer.parseInt(st.nextToken());
        }
        // 记录每头牛的传球目标 计算每头牛接到不同球的次数
        for (int i = 0; i < N; i++) {
            int next = pass(i);
            nextCow[i] = next;
            receiveCount[next]++;
        }

        int ans = 0;
        for (int i = 0; i < N; i++) {
            // 如果这头牛不会接到其他牛的球，那么需要给它一颗球
            if (receiveCount[i] == 0) {
                ans++;
            }
            // 两头牛只会接到彼此的球，那么需要给其中一头牛一颗球
            if (i < nextCow[i] && nextCow[nextCow[i]] == i
                    && receiveCount[i] == 1 && receiveCount[nextCow[i]] == 1) {
                ans++;
            }
        }
        pw.println(ans);
        pw.close();
    }

    /**
     * 找到 cow 传球的目标
     */
    public static int pass(int cow) {
        int leftCow = -1;
        int rightCow = -1;
        int leftDistance = 1000;
        int rightDistance = 1000;
        // 找到 cow 左右两边最近的牛
        for (int i = 0; i < N; i++) {
            if (cows[i] < cows[cow] && cows[cow] - cows[i] < leftDistance) {
                leftCow = i;
                leftDistance = cows[cow] - cows[i];
            }
            if (cows[i] > cows[cow] && cows[i] - cows[cow] < rightDistance) {
                rightCow = i;
                rightDistance = cows[i] - cows[cow];
            }
        }
        if (leftDistance <= rightDistance) {
            return leftCow;
        }
        return rightCow;
    }
}
