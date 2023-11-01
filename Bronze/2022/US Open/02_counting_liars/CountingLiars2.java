import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Red
 * @version 1.0
 */
public class CountingLiars2 {
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
        gCows.sort(Integer::compareTo);
        lCows.sort(Integer::compareTo);
        int ans = Integer.MAX_VALUE;
        // 每个 gCows[i] 的元素二分法找到 lCows 中第一个大于它的元素得到正确区间
        for (int i = 0; i < gCows.size(); i++) {
            int lLairs = bisect(lCows, gCows.get(i));
            int gLairs = gCows.size() - i - 1;
            ans = Math.min(ans, lLairs + gLairs);
        }
        for (int i = 0; i < lCows.size(); i++) {
            int lLairs = i;
            int gLairs = gCows.size() - bisect(gCows, lCows.get(i));
            ans = Math.min(ans, lLairs + gLairs);
        }
        pw.println(ans);
        pw.close();
    }

    /**
     * 二分查找 list 中第一个大于 target 的元素的下标
     */
    public static int bisect(List<Integer> list, int target) {
        int l = 0, r = list.size();
        while (l < r) {
            int mid = (l + r) >> 1;
            if (list.get(mid) <= target) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }
}
