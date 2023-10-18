import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class TheBucketList {
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("blist.in"));
        PrintWriter pw = new PrintWriter("blist.out");
        // 记录每单位时间的桶数 时间粒度为 1
        int[] bucketCount = new int[1001];
        int n = Integer.parseInt(r.readLine());
        int max = 0;
        for (int i = 0; i < n; i++) {
            // 每只牛开始时间到结束时间的区间，记录每单位时间的桶数
            StringTokenizer st = new StringTokenizer(r.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int count = Integer.parseInt(st.nextToken());
            for (int j = start; j <= end; j++) {
                bucketCount[j] += count;
                if (bucketCount[j] > max) {
                    max = bucketCount[j];
                }
            }
        }
        pw.println(max);
        pw.close();
    }
}
