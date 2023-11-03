import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class SwapitySwap {
    static int n;
    static int k;
    static int a1, a2, b1, b2;
    static int[] cows;

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("swap.in"));
        PrintWriter pw = new PrintWriter("swap.out");
        StringTokenizer st = new StringTokenizer(r.readLine());
        n = Integer.parseInt(st.nextToken());
        cows = new int[n];
        for (int i = 0; i < n; i++) {
            cows[i] = i + 1;
        }
        k = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(r.readLine());
        a1 = Integer.parseInt(st.nextToken()) - 1;
        a2 = Integer.parseInt(st.nextToken()) - 1;
        st = new StringTokenizer(r.readLine());
        b1 = Integer.parseInt(st.nextToken()) - 1;
        b2 = Integer.parseInt(st.nextToken()) - 1;
        // 寻找周期 即经过 cycle 次操作后，序列回到原来的状态
        int cycle = 0;
        while (true) {
            exercise();
            cycle++;
            if (isSame()) {
                break;
            }
        }
        // 最后仅需要进行 k % cycle 次操作即可
        k %= cycle;
        for (int i = 0; i < k; i++) {
            exercise();
        }
        for (int i = 0; i < n; i++) {
            pw.println(cows[i]);
        }
        pw.close();
    }

    /**
     * 判断当前序列是否与初始序列相同
     */
    private static boolean isSame() {
        for (int i = 0; i < n; i++) {
            if (cows[i] != i + 1) {
                return false;
            }
        }
        return true;
    }

    /**
     * 进行一次操作
     */
    static void exercise() {
        reverse(a1, a2);
        reverse(b1, b2);
    }

    /**
     * 将序列中的 start 到 end 之间的元素进行反转
     */
    static void reverse(int start, int end) {
        int temp;
        while (start < end) {
            temp = cows[start];
            cows[start] = cows[end];
            cows[end] = temp;
            start++;
            end--;
        }
    }
}
