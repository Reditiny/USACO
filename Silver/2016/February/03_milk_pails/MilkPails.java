import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.*;

/**
 * @author Red
 * @version 1.0
 */
public class MilkPails {
    static int x, y, k, m;
    static HashSet<List<Integer>> possibleCombination = new HashSet<>();

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("pails.in"));
        PrintWriter pw = new PrintWriter("pails.out");
        StringTokenizer st = new StringTokenizer(r.readLine());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        possibleCombination.add(Arrays.asList(0, 0));
        // 从当前可能的状态集合出发操作一次得到下一个可能的状态集合，重复 k 次
        for (int i = 0; i < k; i++) {
            HashSet<List<Integer>> temp = new HashSet<>();
            for (List<Integer> cur : possibleCombination) {
                List<List<Integer>> coms = nextCombination(cur);
                temp.addAll(coms);
            }
            possibleCombination = temp;
        }
        int ans = m;
        for (List<Integer> cur : possibleCombination) {
            ans = Math.min(ans, Math.abs(cur.get(0) + cur.get(1) - m));
        }
        pw.println(ans);
        pw.close();
    }

    /**
     * 从当前状态得到下一个状态 只有6种可能
     * 1. 倒空 x
     * 2. 倒空 y
     * 3. 倒满 x
     * 4. 倒满 y
     * 5. x 倒入 y    分两种情况：x + y > y 时候 y 倒满，否则 x 倒空
     * 6. y 倒入 x    分两种情况：x + y > x 时候 x 倒满，否则 y 倒空
     */
    static List<List<Integer>> nextCombination(List<Integer> cur) {
        List<List<Integer>> coms = new ArrayList<>();
        List<Integer> com1 = Arrays.asList(0, cur.get(1));
        List<Integer> com2 = Arrays.asList(cur.get(0), 0);
        List<Integer> com3 = Arrays.asList(x, cur.get(1));
        List<Integer> com4 = Arrays.asList(cur.get(0), y);
        List<Integer> com5;
        if (cur.get(0) + cur.get(1) > x) {
            com5 = Arrays.asList(x, cur.get(1) - (x - cur.get(0)));
        } else {
            com5 = Arrays.asList(cur.get(0) + cur.get(1), 0);
        }
        List<Integer> com6;
        if (cur.get(0) + cur.get(1) > y) {
            com6 = Arrays.asList(cur.get(0) - (y - cur.get(1)), y);
        } else {
            com6 = Arrays.asList(0, cur.get(0) + cur.get(1));
        }
        coms.add(com1);
        coms.add(com2);
        coms.add(com3);
        coms.add(com4);
        coms.add(com5);
        coms.add(com6);
        return coms;
    }
}