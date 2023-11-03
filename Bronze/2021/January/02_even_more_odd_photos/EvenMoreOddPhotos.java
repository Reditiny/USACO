import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class EvenMoreOddPhotos {
    static int n;
    static int oddCount = 0;
    static int evenCount = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        n = Integer.parseInt(r.readLine());
        StringTokenizer st = new StringTokenizer(r.readLine());
        for (int i = 0; i < n; i++) {
            int cur = Integer.parseInt(st.nextToken());
            if (cur % 2 == 0) {
                evenCount++;
            } else {
                oddCount++;
            }
        }
        int ans = 0;
        if (evenCount > oddCount) {
            // 多出来的偶数可以和任意组合并
            ans = 2 * oddCount + 1;
        } else if (evenCount == oddCount) {
            ans = 2 * oddCount;
        } else {
            // 当奇数多时 每3个奇数可以组成 1偶+1奇
            ans = 2 * evenCount;
            // 考虑不同数量余数的情况
            int remaining = oddCount - evenCount;
            if (remaining % 3 == 0) {
                // 没有余数可以变成若干个 1偶+1奇
                ans += (remaining / 3) * 2;
            } else if (remaining % 3 == 2) {
                // 余数为2 剩的最后两个可以组成 1偶
                ans += (remaining / 3) * 2 + 1;
            } else {
                // 余数为1 此时不可能剩1个，剩1个没法组合出有效解所以应该是剩4个，故将最后4个组成 1偶
                remaining -= 4;
                ans += (remaining / 3) * 2 + 1;
            }
        }
        pw.println(ans);
        pw.close();
    }
}
