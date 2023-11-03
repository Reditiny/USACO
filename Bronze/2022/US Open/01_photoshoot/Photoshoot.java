import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Red
 * @version 1.0
 */
public class Photoshoot {
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int n = Integer.parseInt(r.readLine());
        String cows = r.readLine();
        // 翻转奇数个牛并不会有效果，原本在偶数位的G翻转之后仍在偶数位，原本不在的仍然不在
        // 例如 GHHHGH 有一个G在偶数位上，翻转任意奇数个字符，偶数位上的G数量不会改变
        // 翻转偶数个牛时，奇偶位会改变，因此从数量上看和每两个翻转一次是等价的
        // 例如翻转4个    1234                1234
        //              GHHHGH -> HHHGGH == GHHHGH -> HGHHGH
        //              4321                2143
        int ans = 0;
        // 从后往前遍历，每次只考虑两个字符
        for (int c = n - 2; c >= 0; c -= 2) {
            String sub = cows.substring(c, c + 2);
            if (sub.charAt(0) == sub.charAt(1)) {
                continue;
            }
            // 对于 GH 字符，遍历到它时如果翻转了偶数次，那么它仍然是 GH ，所以需要翻转一次
            // 对于 HG 字符，遍历到它时如果翻转了奇数次，那么它会变成 GH ，所以需要翻转一次
            if (("GH".equals(sub) && ans % 2 == 0) ||
                    ("HG".equals(sub) && ans % 2 == 1)) {
                ans++;
            }
        }
        pw.println(ans);
        pw.close();
    }
}
