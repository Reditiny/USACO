import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Red
 * @version 1.0
 */
public class AngryCows {
    static List<Integer> cows;

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("angry.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("angry.out"));
        int n = Integer.parseInt(r.readLine());
        cows = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            cows.add(Integer.parseInt(r.readLine()));
        }
        cows.sort(Integer::compareTo);
        int ans = 0;
        for (int i = 0; i < cows.size(); i++) {
            ans = Math.max(ans, explodeNumber(i));
        }
        pw.println(ans);
        pw.close();
    }

    /**
     * 计算从 start 开始爆炸最终有多少草包爆炸
     */
    public static int explodeNumber(int start) {
        return 1 + explodeLeft(start, 1) + explodeRight(start, 1);
    }

    /**
     * 递归地计算右边爆炸多少草包 因为任意起点左右两边的爆炸互不干扰所以分开计算
     * 本层递归只计算本次爆炸所引爆的草包数 并找到最远的草包的位置
     * 下层递归以该草包为爆炸起点
     */
    public static int explodeRight(int start, int radius) {
        int ans = 0;
        int right = start;
        for (int i = start + 1; i < cows.size(); i++) {
            if (cows.get(i) - cows.get(start) <= radius) {
                ans++;
                right = i;
            } else {
                break;
            }
        }
        if (right == start) {
            return 0;
        }
        return ans + explodeRight(right, radius + 1);
    }

    public static int explodeLeft(int start, int radius) {
        int ans = 0;
        int left = start;
        for (int i = start - 1; i >= 0; i--) {
            if (cows.get(start) - cows.get(i) <= radius) {
                ans++;
                left = i;
            } else {
                break;
            }
        }
        if (left == start) {
            return 0;
        }
        return ans + explodeLeft(left, radius + 1);
    }

}
