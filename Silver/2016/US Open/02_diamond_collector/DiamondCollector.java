import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class DiamondCollector {
    static int N, K;
    // 钻石
    static List<Integer> diamonds = new ArrayList<Integer>();
    static int ans = 0;
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("diamond.in"));
        PrintWriter pw = new PrintWriter("diamond.out");
        StringTokenizer st = new StringTokenizer(r.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            diamonds.add(Integer.parseInt(r.readLine()));
        }
        diamonds.sort(Integer::compareTo);
        // leftmostIndex[i] 钻石i左边最远能配对的钻石
        int[] leftmostIndex = getLeftmost();
        // leftSize[i] [0,i] 范围内钻石能组成的对数
        int[] leftSize = new int[N];
        for (int i = 0; i < N; i++) {
            leftSize[i] = i - leftmostIndex[i] + 1;
            if (i > 0) { leftSize[i] = Math.max(leftSize[i], leftSize[i - 1]); }
        }
        // rightmostIndex[i] 钻石i右边最远能配对的钻石
        int[] rightmostIndex = getRightmost();
        // rightSize[i] [i,N-1] 范围内钻石能组成的对数
        int[] rightSize = new int[N];
        for (int i = N - 1; i >= 0; i--) {
            rightSize[i] = rightmostIndex[i] - i + 1;
            if (i < N - 1) {
                rightSize[i] = Math.max(rightSize[i], rightSize[i + 1]);
            }
        }
        // 依次遍历 [0,i][i+1,N-1] 两个范围内钻石能组成的对数
        for (int i = 0; i < N - 1; i++) {
            ans = Math.max(ans, leftSize[i] + rightSize[i + 1]);
        }
        pw.println(ans);
        pw.close();
    }

    /**
     * 得到钻石的右边最远能配对的钻石
     */
    public static int[] getRightmost() {
        int[] ret = new int[N];
        int j = N - 1;
        for (int i = N - 1; i >= 0; i--) {
            while (j >= 0 && diamonds.get(j) - diamonds.get(i) > K) { j--; }
            ret[i] = j;
        }
        return ret;
    }
    /**
     * 得到钻石的左边最远能配对的钻石
     */
    public static int[] getLeftmost() {
        int[] ret = new int[N];
        int j = 0;
        for (int i = 0; i < N; i++) {
            while (j < N && diamonds.get(i) - diamonds.get(j) > K) { j++; }
            ret[i] = j;
        }
        return ret;
    }
}