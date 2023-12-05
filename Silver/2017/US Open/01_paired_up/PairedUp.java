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
public class PairedUp {
    static int N;
    // 记录各种牛 [数量，产量]
    static List<int[]> cows = new ArrayList<>();
    static int ans = 0;
    public static void main(String[] args) throws Exception {
        BufferedReader io = new BufferedReader(new FileReader("pairup.in"));
        PrintWriter out = new PrintWriter("pairup.out");
        N = Integer.parseInt(io.readLine().trim());
        for (int i = 0; i < N; i++) {
            StringTokenizer tok = new StringTokenizer(io.readLine());
            int freq = Integer.parseInt(tok.nextToken());
            int amt = Integer.parseInt(tok.nextToken());
            cows.add(new int[]{freq, amt});
        }
        // 简单的方式是将所有产量都拿出来排序
        // 如题例可以得到 2 5 5 8 但这样排序的复杂会过大导致超时
        // 因此牛的数据为[数量，产量]，按产量排序
        // 题例可以得到 [1,2] [2,5] [1,8]
        cows.sort((a, b) -> a[1] - b[1]);
        // 所有牛按产量排序，只需要依次将产量最大的牛与产量最小的牛配对即可
        int left = 0, right = N - 1;
        while (left <= right) {
            // 当最大和最小都有多头牛时，可以一次性配多对
            int numPaired = Integer.min(cows.get(left)[0], cows.get(right)[0]);
            if (left == right) {
                numPaired /= 2;
            }
            ans = Integer.max(ans, cows.get(left)[1] + cows.get(right)[1]);
            cows.get(left)[0] -= numPaired;
            cows.get(right)[0] -= numPaired;
            // 数量配完后移动指针
            if (cows.get(left)[0] == 0) {
                left++;
            }
            if (cows.get(right)[0] == 0) {
                right--;
            }
        }
        out.println(ans);
        out.close();
    }

}