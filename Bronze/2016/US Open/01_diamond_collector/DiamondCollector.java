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
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("diamond.in"));
        PrintWriter pw = new PrintWriter("diamond.out");
        StringTokenizer st = new StringTokenizer(r.readLine());
        List<Integer> diamonds = new ArrayList<>();
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        for (int i = 0; i < n; i++) {
            diamonds.add(Integer.parseInt(r.readLine()));
        }
        diamonds.sort(Integer::compareTo);
        int ans = 0;
        int j = 0;
        // 双指针 虽然 for 和 while 两层循环但时间复杂度为 O(n)
        // 因为在 i 的遍历过程中 j 始终只会增加并不会回溯到 0
        for (int i = 0; i < n; i++) {
            while (j < n && diamonds.get(j) - diamonds.get(i) <= k) {
                j++;
            }
            ans = Math.max(ans, j - i);
        }
        pw.println(ans);
        pw.close();
    }
}
