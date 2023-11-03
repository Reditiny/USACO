import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class SleepyCowSorting {
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("sleepy.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("sleepy.out"));
        int n = Integer.parseInt(r.readLine());
        int[] cows = new int[n];
        StringTokenizer st = new StringTokenizer(r.readLine());
        for (int i = 0; i < n; i++) {
            cows[i] = Integer.parseInt(st.nextToken());
        }
        // 对于当前序列后缀的升序序列可以不需要移动
        // 如  5 4 3 2 1 6 7 8 9 10
        // 1 6 7 8 9 10 本身已经时升序，前面的牛只需要移动进这个升序序列之中
        // 2 作为第一个不满足后缀升序的牛，它一定需要移动，且需要前面的牛移动完 2 才能移动
        // 因此需要移动的牛的个数是 n - 后缀升序牛的个数
        int endOrderCount = 1;
        int lastNumber = cows[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            if (cows[i] < lastNumber) {
                // 记录后缀升序牛的个数
                endOrderCount++;
                lastNumber = cows[i];
            } else {
                // 找到第一个不满足后缀升序的牛
                break;
            }
        }
        pw.println(n - endOrderCount);
        pw.close();
    }
}
