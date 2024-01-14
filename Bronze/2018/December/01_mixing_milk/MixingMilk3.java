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
        // 寻找周期 startCycle 到 endCycle 为一次循环
        int startCycle = 0;
        int endCycle = 0;
        while (true) {
            pour3Times();
            curCycle++;
            if(curCycle == cyclePourTimes) {
                break;
            }
            if (statusToCount.containsKey(bucketCurMilk)){
                startCycle = statusToCount.get(bucketCurMilk);
                endCycle = curCycle;
                break;
            }
            statusToCount.put(bucketCurMilk, curCycle);
        }
        // 前 startCycle 次操作到达循环的初始状态，然后开始 endCycle - startCycle 长度的循环
        // 最后仅需 (cyclePourTimes - startCycle) % (endCycle - startCycle) 次操作即可
        int pourTimes = (cyclePourTimes - startCycle) % (endCycle - startCycle);
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