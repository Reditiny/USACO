import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class AngryCowsSilver {
    static int N, K;
    static int[] bales;
    static int R = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("angry.in"));
        PrintWriter pw = new PrintWriter("angry.out");
        StringTokenizer st = new StringTokenizer(r.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        bales = new int[N];
        for (int i = 0; i < N; i++) {
            bales[i] = Integer.parseInt(r.readLine());
        }
        // 排序后二分查找可以引爆所有草包的最小半径 R
        Arrays.sort(bales);
        int left = 1, right = (int) 1e9;
        while (left <= right) {
            // 此处不用 (left + right) / 2 是因为加法操作可能会溢出
            int mid = left + (right - left) / 2;
            if (check(mid)) {
                R = Math.min(R, mid);
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        pw.println(R);
        pw.close();
    }

    /**
     * 检查半径为 radius 的爆炸是否可以在 K 个以内引爆所有草包
     */
    static boolean check(int radius){
        int i = 0;
        for (int cowCount = 0; cowCount < K && i < N; cowCount++) {
            // 每次爆炸范围是当前草包位置加上直径
            // 因为草包位置是左边界，所以后续草包只需要考虑右边界即可
            int destroyScope = bales[i] + radius * 2;
            while (i < N - 1 && bales[i + 1] <= destroyScope) {
                i++;
            }
            i++;
        }
        return i >= N;
    }
}



