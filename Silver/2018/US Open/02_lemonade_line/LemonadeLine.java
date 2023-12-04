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
public class LemonadeLine {
    static int N;
    static List<Integer> cows = new ArrayList<Integer>();
    static int ans = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("lemonade.in"));
        PrintWriter pw = new PrintWriter("lemonade.out");
        N = Integer.parseInt(r.readLine());
        StringTokenizer st = new StringTokenizer(r.readLine());
        for (int i = 0; i < N; i++) {
            cows.add(Integer.parseInt(st.nextToken()));
        }
        // 按愿意排队的长度从大到小排序
        // 这样越往后的牛越不愿意排队，可使得队长最小
        cows.sort((a, b) -> b - a);
        // 二分查找第一个 cows.get(i) > i
        // 线性查找最后一个case会超时
        int left = 0, right = N - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (cows.get(mid) >= mid) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        if (cows.get(left) >= left) {
            ans = left + 1;
        } else {
            ans = left;
        }
        pw.println(ans);
        pw.close();
    }
}