import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;

/**
 * @author Red
 * @version 1.0
 */
public class MadScientist {
    static int n;
    static char[] a;
    static char[] b;

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("breedflip.in"));
        PrintWriter pw = new PrintWriter("breedflip.out");
        n = Integer.parseInt(r.readLine());
        a = r.readLine().toCharArray();
        b = r.readLine().toCharArray();
        // 每次改变连续的不同的牛 寻找到每一段的起点终点即可
        //  |--| |---|
        // GGGGGHHGGGGGH
        // GHHHHHGHHHHGH
        int ans = 0;
        // start == false 说明正在寻找起点
        // start == true  说明找到起点，正在寻找终点
        boolean start = false;
        for (int i = 0; i < n; i++) {
            if (start && a[i] == b[i]) {
                // 找到当前的终点，准备寻找下一个起点
                start = false;
            } else if (!start && a[i] != b[i]) {
                // 找到起点，准备寻找终点
                ans++;
                start = true;
            }
        }
        pw.println(ans);
        pw.close();
    }
}
