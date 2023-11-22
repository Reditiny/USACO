import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class MixingMilk {
    static int bucketCount = 3;
    static int cyclePourTimes = 33;
    static int[] bucketCapacity = new int[bucketCount];
    static int[] bucketInitMilk = new int[bucketCount];
    static int[] bucketCurMilk = new int[bucketCount];

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("mixmilk.in"));
        PrintWriter pw = new PrintWriter("mixmilk.out");
        for (int i = 0; i < bucketCount; i++) {
            StringTokenizer st = new StringTokenizer(r.readLine());
            bucketCapacity[i] = Integer.parseInt(st.nextToken());
            bucketInitMilk[i] = Integer.parseInt(st.nextToken());
            bucketCurMilk[i] = bucketInitMilk[i];
        }
        // 寻找周期 即经过 cycle 次操作后，序列回到原来的状态
        int cycle = 0;
        while (true) {
            pour3Times();
            cycle++;
            if (cycle == cyclePourTimes || matchInit()) {
                break;
            }
        }
        // 最后仅需要进行 k % cycle + 1 次操作即可
        int pourTimes = cyclePourTimes % cycle;
        for (int i = 0; i < pourTimes; i++) {
            pour3Times();
        }
        pour();
        for (int i = 0; i < bucketCount; i++) {
            pw.println(bucketCurMilk[i]);
        }
        pw.close();
    }

    /**
     * 判断当前桶的牛奶是否与初始状态相同
     */
    private static boolean matchInit() {
        for (int i = 0; i < bucketCount; i++) {
            if (bucketCurMilk[i] != bucketInitMilk[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 每个桶都倒一次
     */
    static void pour3Times() {
        for (int i = 0; i < bucketCount; i++) {
            int nextBucket = (i + 1) % bucketCount;
            int pourMilk = Math.min(bucketCurMilk[i], bucketCapacity[nextBucket] - bucketCurMilk[nextBucket]);
            bucketCurMilk[i] -= pourMilk;
            bucketCurMilk[nextBucket] += pourMilk;
        }
    }

    /**
     * 最后倒一次
     */
    private static void pour() {
        int pourMilk = Math.min(bucketCurMilk[0], bucketCapacity[1] - bucketCurMilk[1]);
        bucketCurMilk[0] -= pourMilk;
        bucketCurMilk[1] += pourMilk;
    }

}