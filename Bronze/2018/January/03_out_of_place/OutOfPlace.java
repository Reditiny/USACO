import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Red
 * @version 1.0
 */
public class OutOfPlace {
    static int n;
    static List<Integer> cows;
    static List<Integer> orderedCows;

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("outofplace.in"));
        PrintWriter pw = new PrintWriter("outofplace.out");
        n = Integer.parseInt(r.readLine());
        cows = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            cows.add(Integer.parseInt(r.readLine()));
        }
        // 最优的交换方式就是每次都把一个不在最终位置上的牛放到最终位置上(不交换其他已经在最终位置上的牛)
        orderedCows = new ArrayList<>(cows);
        orderedCows.sort(Integer::compareTo);
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (!orderedCows.get(i).equals(cows.get(i))) {
                ans++;
            }
        }
        // 有 ans 个牛不在自己最终的位置上
        // 当通过交换把 ans - 1 个牛放在最终的位置上时，最后那只牛也就已经在正确的位置了
        pw.println(ans - 1);
        pw.close();
    }
}
