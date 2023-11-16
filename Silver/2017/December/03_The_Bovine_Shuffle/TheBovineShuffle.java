import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class TheBovineShuffle {

    static int n;
    static int[] nextPosition;
    static boolean[] inCycle;

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("shuffle.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("shuffle.out"));
        n = Integer.parseInt(r.readLine());
        StringTokenizer st = new StringTokenizer(r.readLine());
        nextPosition = new int[n];
        for (int i = 0; i < n; i++) {
            nextPosition[i] = Integer.parseInt(st.nextToken()) - 1;
        }
        inCycle = new boolean[n];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (!inCycle[i]) {
                ans += countCycleSize(i);
            }
        }
        pw.println(ans);
        pw.close();
        r.close();
    }

    /**
     * 从位置 i 开始寻找环 若是环则返回环的大小
     * 例如 1 -> 3 -> 7 -> 1  每次换位时 1 3 7 位置上始终有牛
     * 例如 1 -> 3 -> 7 -> 5 -> 3  虽然有环但 1 不是起点，当遍历到 5 时环就会被计算
     */
    public static int countCycleSize(int i) {
        int cur = i;
        int count = 0;
        HashSet<Integer> cycleSet = new HashSet<Integer>();
        while (!cycleSet.contains(cur)) {
            cycleSet.add(cur);
            cur = nextPosition[cur];
            // 如果遍历的过程中发现了其他的环，那么 i 作为起点就不可能在环内
            if (inCycle[cur]) {
                return 0;
            }
        }
        // 如果环的起点是 i，那么环的大小就是 cycleSet 的大小
        if (cur == i) {
            for (int j : cycleSet) {
                inCycle[j] = true;
                count++;
            }
        }
        return count;
    }
}

