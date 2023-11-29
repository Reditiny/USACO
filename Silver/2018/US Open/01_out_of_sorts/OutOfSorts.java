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
public class OutOfSorts {
    static int N;
    // 牛 [牛的编号, 牛的初始位置]
    static List<List<Integer>> cows = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("sort.in"));
        PrintWriter pw = new PrintWriter("sort.out");
        N = Integer.parseInt(r.readLine());
        for (int i = 0; i < N; i++) {
            List<Integer> cow = new ArrayList<>();
            int number = Integer.parseInt(r.readLine());
            cow.add(number);
            cow.add(i);
            cows.add(cow);
        }
        // 按牛的编号排序
        cows.sort((o1,o2)-> o1.get(0).compareTo(o2.get(0)));
        // 冒泡排序每次排序会把不在最终位置的最大的数放到其最终位置上，而其余数最多只会移动一位
        // 初始位置大于最终位置的每次循环都会往前一步，最多的步数是初始位置和最终位置的距离
        int ans = 1;
        for (int i = 0; i < N; i++) {
            ans = Math.max(ans, 1 + cows.get(i).get(1) - i);
        }
        pw.println(ans);
        pw.close();
    }
}