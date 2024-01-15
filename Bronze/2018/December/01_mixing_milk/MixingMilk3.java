import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class MixingMilk3 {
    static int bucketCount = 3;
    static int cyclePourTimes = 100 / bucketCount;
    static HashMap<int[], Integer> statusToCount = new HashMap<>();
    static int[] bucketCapacity = new int[bucketCount];
    static int[] bucketCurMilk = new int[bucketCount];

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("mixmilk.in"));
        PrintWriter pw = new PrintWriter("mixmilk.out");
        for (int i = 0; i < bucketCount; i++) {
            StringTokenizer st = new StringTokenizer(r.readLine());
            bucketCapacity[i] = Integer.parseInt(st.nextToken());
            bucketCurMilk[i] = Integer.parseInt(st.nextToken());
        }
        int curCycle = 0;
        statusToCount.put(bucketCurMilk, curCycle);
        // 寻找周期
        int cycleSize = 100;
        while (true) {
            pour3Times();
            curCycle++;
            if(curCycle == cyclePourTimes) {
                break;
            }
            if (statusToCount.containsKey(bucketCurMilk)){
                cycleSize = statusToCount.get(bucketCurMilk) - curCycle;
                break;
            }
            statusToCount.put(bucketCurMilk, curCycle);
        }
        // cyclePourTimes - curCycle 为寻找周期后剩余的次数 cycleSize 为周期的长度
        int pourTimes = (cyclePourTimes - curCycle) % cycleSize;
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
     * 每个桶都倒一次
     * 当需要寻找周期时，要保证每次的操作一样，“第一个桶往第二桶倒”和“第二个桶往第三个桶倒”两者并不是同样的操作
     * 例如1号是空的，2号和3号都是满的，这时从1号倒入2号，或者2号倒入3号，状态是不变的，但周期明显不是1，就是因为两次的操作不一样
     * 因此选择“每个桶都倒一次”作为一次操作，这样可以保证每次的操作都是一样的
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