import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class DoYouKnowYourABCs {
    static List<Integer> nums = new ArrayList<Integer>();

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(r.readLine());
        for (int i = 0; i < 7; i++) {
            nums.add(Integer.parseInt(st.nextToken()));
        }
        nums.sort(Integer::compareTo);
        // 最小的两个数一定是 a 和 b 最大的数一定是 a+b+c
        int a = nums.get(0);
        int b = nums.get(1);
        int c = nums.get(6) - a - b;
        pw.println(a + " " + b + " " + c);
        pw.close();
    }
}