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
        // gCows lCows 从大到小排序
        // 排序规则并不固定 不同的排序规则会影响遍历的顺序以及目标牛的区间
        gCows.sort((o1, o2) -> o2 - o1);
        lCows.sort((o1, o2) -> o2 - o1);
        int ans = Integer.MAX_VALUE;
        int j = 0;
        // 双指针O(n) 移动过程中正确的位置在[gCows.get(i),lCows.get(j)]区间
        // 双指针算法需要保证遍历过程中的单调性，才能确保 j 指针可以单方向的移动
        for (int i = 0; i < gCows.size() ; i++) {
            // i 的遍历方式要让正确区间的下限不断减小（遍历顺序与排序顺序相关）
            // 由于区间的下限是在减少因此遍历过程中区间的下限不会超过当前的上限，可以保证上限单调改变而不用从头开始遍历
            while (j < lCows.size() && gCows.get(i) <= lCows.get(j)) {
                j++;
            }
            // 此时正确位置在[11,13]区间 指针移动过程中 i 前和 j 后的牛为撒谎的牛
            //            i
            // gCows [24,11,7,5,3,1]
            // lCows [19,13,9,7,5,2]
            //            j
            if (j == lCows.size()) {
                ans = Math.min(ans, i);
                break;
            }
            ans = Math.min(ans, i + lCows.size() - j);
        }
        pw.println(ans);
        pw.close();
    }
}
