import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class BackAndForth2 {
    static List<Integer> buckets1 = new ArrayList<Integer>();
    static List<Integer> buckets2 = new ArrayList<Integer>();
    static HashSet<Integer> milks = new HashSet<Integer>();

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("backforth.in"));
        PrintWriter pw = new PrintWriter("backforth.out");
        StringTokenizer st = new StringTokenizer(r.readLine());
        for (int i = 0; i < 10; i++) {
            buckets1.add(Integer.parseInt(st.nextToken()));
        }
        st = new StringTokenizer(r.readLine());
        for (int i = 0; i < 10; i++) {
            buckets2.add(Integer.parseInt(st.nextToken()));
        }
        // 情况1 拿着相同的桶走了两个来回
        milks.add(0);
        // 情况2 拿着相同的桶走了一个来回，然后又各拿一个桶
        for (Integer k1 : buckets1) {
            for (Integer k2 : buckets2) {
                milks.add(k2 - k1);
            }
        }
        // 情况3 周二周四拿了自己的不同的桶  周三周五拿了自己的不同的桶
        // 假设周三时拿了周二拿过去的桶，这时满足情况2，所以在此不做考虑，因此情况3只会拿本来就属于自己仓库的桶
        for (int i = 0; i < buckets1.size(); i++) {
            for (int j = i + 1; j < buckets1.size(); j++) {
                for (int u = 0; u < buckets2.size(); u++) {
                    for (int v = u + 1; v < buckets2.size(); v++) {
                        milks.add(buckets2.get(u) + buckets2.get(v) - buckets1.get(i) - buckets1.get(j));
                    }
                }
            }
        }
        pw.println(milks.size());
        pw.close();
    }
}
