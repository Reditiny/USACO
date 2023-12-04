import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author Red
 * @version 1.0
 */
public class ClosestCowWins {
    static int K, M, N;
    // 记录牧场信息
    static List<Patch> patches = new ArrayList<>();
    // 记录 tastiness 前缀和
    static long[] tastinessSums;
    // 记录牛的位置
    static List<Integer> nhojCows = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());
        K = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());
        N = Integer.parseInt(tokenizer.nextToken());
        for (int j = 0; j < K; j++) {
            tokenizer = new StringTokenizer(in.readLine());
            int location = Integer.parseInt(tokenizer.nextToken());
            long tastiness = Long.parseLong(tokenizer.nextToken());
            patches.add(new Patch(location, tastiness));
        }
        // 牧场按位置从小到大排序
        patches.sort((p1,p2) -> (p1.location - p2.location));
        tastinessSums = new long[K + 1];
        for (int j = 0; j < K; j++) {
            tastinessSums[j + 1] = tastinessSums[j] + patches.get(j).tastiness;
        }
        for (int j = 0; j < M; j++) {
            nhojCows.add(Integer.parseInt(in.readLine()));
        }
        // 牛按位置从小到大排序
        nhojCows.sort((c1, c2) -> (c1 - c2));
        // 用 TreeMap 存放牛的位置和索引
        TreeMap<Integer, Integer> nhojCowsTreeMap = new TreeMap<>();
        for (int j = 0; j < M; j++) {
            nhojCowsTreeMap.put(nhojCows.get(j), j);
        }
        // 用 TreeMap 存放牧场的位置和索引
        TreeMap<Integer, Integer> patchTreeMap = new TreeMap<>();
        for (int j = 0; j < K; j++) {
            patchTreeMap.put(patches.get(j).location, j);
        }
        long[][] valueAdded = new long[3][M + 1];
        // 遍历找到草头
        for (int j = 0; j < K; j++) {
            // 获得当前 j 草地后面的第一头牛 nextNhojCow
            Map.Entry<Integer, Integer> nextNhojCowEntry = nhojCowsTreeMap.ceilingEntry(patches.get(j).location);
            int nextNhojCow = nextNhojCowEntry == null ? M : nextNhojCowEntry.getValue();
            valueAdded[2][nextNhojCow] += patches.get(j).tastiness;
            if (nextNhojCow == 0 || nextNhojCow == M) {
                // 最左边区间和最右边的区间，john仅用一头牛就可以获得所有 tastiness
                valueAdded[1][nextNhojCow] += patches.get(j).tastiness;
            } else {
                // john 的牛应该放在当前草地左右最近的两头牛之中更近的那个位置
                int johnCowLocation = Math.min(nhojCows.get(nextNhojCow), (2 * patches.get(j).location) - nhojCows.get(nextNhojCow - 1));
                int extent = (johnCowLocation + nhojCows.get(nextNhojCow) + 1) / 2;
                int farthestPatch = patchTreeMap.lowerEntry(extent).getValue();
                valueAdded[1][nextNhojCow] = Math.max(valueAdded[1][nextNhojCow], tastinessSums[farthestPatch + 1] - tastinessSums[j]);
            }
        }
        Long[] valueAddedOverall = new Long[2 * (M + 1)];
        for (int j = 0; j <= M; j++) {
            valueAddedOverall[2 * j] = valueAdded[1][j];
            valueAddedOverall[(2 * j) + 1] = valueAdded[2][j] - valueAdded[1][j];
        }
        Arrays.sort(valueAddedOverall);
        long answer = 0;
        for (int j = Math.max(0, valueAddedOverall.length - N); j < valueAddedOverall.length; j++) {
            answer += valueAddedOverall[j];
        }
        System.out.println(answer);
    }

    public static class Patch{
        final int location;
        final long tastiness;

        public Patch(int location, long tastiness) {
            this.location = location;
            this.tastiness = tastiness;
        }
    }

}