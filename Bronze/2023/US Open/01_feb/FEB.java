import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * @author Red
 * @version 1.0
 */
public class FEB {
    static int n;
    static TreeSet<Integer> ans = new TreeSet<>();

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        n = Integer.parseInt(r.readLine());
        char[] messages = r.readLine().toCharArray();
        int minLevel = 0, maxLevel = 0;
        // 单独处理前缀的 F
        int prefixCount = 0;
        while (prefixCount < n && messages[prefixCount] == 'F') {
            prefixCount++;
        }
        maxLevel += prefixCount;
        int startIndex = 0;
        // 找到字符串中所有 "B/E + 若干F + B/E" 的子串
        while (startIndex < n) {
            if (messages[startIndex] == 'F') {
                startIndex++;
                continue;
            }
            int endIndex = startIndex + 1;
            while (endIndex < n && messages[endIndex] == 'F') {
                endIndex++;
            }
            if (endIndex == n) {
                break;
            }
            int length = endIndex - startIndex + 1;
            // 首尾相同 如 BFF...FFB
            if (messages[endIndex] == messages[startIndex]) {
                // 区间长度为偶数level最小能+1 如 BEBEBB
                // 奇数最少可不加 如 BEBEB
                if (length % 2 == 0) {
                    minLevel += 1;
                }
                // level 最多能加 length-1 个 如 BBBBB
                maxLevel += length - 1;

            } else {  // 首尾不同 如 BFF...FFE
                // 区间长度为奇数level最小能+1 如 BEBEE
                // 欧数最少可不加 如 BEBE
                if (length % 2 != 0) {
                    minLevel += 1;
                }
                // level 最多能加 length-2 个 如 BBBBE
                maxLevel += length - 2;
            }
            startIndex = endIndex;
        }
        // 单独处理后缀的 F
        int suffixCount = 0;
        while (suffixCount < n && messages[n - 1 - suffixCount] == 'F') {
            suffixCount++;
        }
        if (suffixCount == n) {
            // 字符串全是 F
            minLevel = 0;
            maxLevel = n - 1;
        } else {
            // 前后缀无论多少F，level最少可以不加，最多为F个数
            maxLevel += suffixCount;
        }
        // 如果前后缀都没有 F 则 level 的改变量为 2
        // 对于 BFE -> minLevel = 1 maxLevel = 1  EFE -> minLevel = 0 maxLevel = 2
        // BFFE = BBFE + BEFE -> minLevel = 1 maxLevel = 3
        // 依次可以推广到 "B/E + 若干F + B/E"
        List<Integer> ans = new ArrayList<>();
        if (prefixCount == 0 && suffixCount == 0) {
            for (int i = minLevel; i <= maxLevel; i += 2) {
                ans.add(i);
            }
        } else {
            for (int i = minLevel; i <= maxLevel; i++) {
                ans.add(i);
            }
        }
        pw.println(ans.size());
        ans.forEach(pw::println);
        pw.close();
    }


}
