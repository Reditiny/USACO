import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class CowCollege {
    static List<Integer> cowFee = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int n = Integer.parseInt(r.readLine());
        StringTokenizer st = new StringTokenizer(r.readLine());

        for (int i = 0; i < n; i++) {
            cowFee.add(Integer.parseInt(st.nextToken()));
        }
        cowFee.sort((i1, i2) -> i2.compareTo(i1));
        // 此时需要使用 long 使用 int 时会有案例因为数值太大溢出而错误
        // long 相比 int 可以保存更大的数
        long maxFee = 0, optimalTuition = cowFee.get(0);
        for (int i = 0; i < cowFee.size(); i++) {
            long curFee = (long) cowFee.get(i) * (i + 1);
            // 这里的 = 是为了让相同结果时得到更小的单价
            if (curFee >= maxFee) {
                maxFee = curFee;
                optimalTuition = cowFee.get(i);
            }
        }
        pw.println(maxFee + " " + optimalTuition);
        pw.close();
    }
}
