import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class CountingLiars {
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int n = Integer.parseInt(r.readLine());
        List<Integer> gCows = new ArrayList<>();
        List<Integer> lCows = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] strings = r.readLine().split(" ");
            if ("G".equals(strings[0])) {
                gCows.add(Integer.parseInt(strings[1]));
            } else {
                lCows.add(Integer.parseInt(strings[1]));
            }
        }
        // gCows 从小到大 lCows 从大到小排序
        gCows.sort(Integer::compareTo);
        lCows.sort((o1, o2) -> o2 - o1);
        int ans = 0x3f3f3f3f;
        int j = 0;
        // 双指针O(n) 对于指定 i 和 j 两者后面的元素为撒谎的牛
        for (int i = gCows.size() - 1; i >= 0; i--) {
            while (j < lCows.size() && gCows.get(i) <= lCows.get(j)) {
                j++;
            }
            if (j == lCows.size()) {
                ans = Math.min(ans, gCows.size() - i - 1);
                break;
            }
            ans = Math.min(ans, gCows.size() - i - 1 + lCows.size() - j);
        }
        pw.println(ans);
        pw.close();
    }
}
