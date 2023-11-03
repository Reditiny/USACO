import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.*;

/**
 * @author Red
 * @version 1.0
 */
public class MilkingOrder {
    // index 为顺序位置 [index] 为牛的编号 用于寻找空位以及最后输出
    static int[] positionToCow;
    static List<Integer> cowHierarchy;
    static int n;
    // index 为牛的编号 [index] 为该牛要求的顺序位置 [index] == 0 表示该牛没有要求顺序位置
    // 用于快速查找牛所要求的顺序位置
    static int[] cowToPosition;

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("milkorder.in"));
        PrintWriter pw = new PrintWriter("milkorder.out");
        StringTokenizer st = new StringTokenizer(r.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        // 这里额外多申请一个空间是为了让默认值 0 表示该牛没有要求顺序位置
        cowToPosition = new int[n + 1];
        positionToCow = new int[n + 1];
        cowHierarchy = new ArrayList<Integer>();
        st = new StringTokenizer(r.readLine());
        boolean hierarchyContainCow1 = false;
        for (int i = 0; i < m; i++) {
            int cow = Integer.parseInt(st.nextToken());
            cowHierarchy.add(cow);
            if (cow == 1) {
                hierarchyContainCow1 = true;
            }
        }
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(r.readLine());
            int cow = Integer.parseInt(st.nextToken());
            int position = Integer.parseInt(st.nextToken());
            positionToCow[position] = cow;
            cowToPosition[cow] = position;
        }
        int lastPosition = 1;
        // hierarchy 中是否包含 cow1 会有不同的处理方案
        if (hierarchyContainCow1) {
            lastPosition = putPrefixHierarchy();
        } else {
            putAllHierarchy();
        }
        for (int i = lastPosition; i <= n; i++) {
            if (positionToCow[i] == 0) {
                pw.println(i);
                break;
            }
        }
        pw.close();
    }

    /**
     * 将 Hierarchy 中 1 前面的牛从前往后依次放入队列中
     *
     * @return 1 前面最后一个牛的位置
     */
    private static int putPrefixHierarchy() {
        int lastPosition = 1;
        for (int i = 0; i < cowHierarchy.size(); i++) {
            int curCow = cowHierarchy.get(i);
            if (cowToPosition[curCow] != 0) {
                lastPosition = cowToPosition[curCow];
            } else {
                while (positionToCow[lastPosition] != 0) {
                    lastPosition++;
                }
            }
            if (curCow == 1) {
                break;
            }
            positionToCow[lastPosition] = curCow;

        }
        return lastPosition;
    }

    /**
     * 将 Hierarchy 中所有牛从后往前依次放入队列中
     */
    static public void putAllHierarchy() {
        int lastPosition = n;
        for (int i = cowHierarchy.size() - 1; i >= 0; i--) {
            int curCow = cowHierarchy.get(i);
            if (cowToPosition[curCow] != 0) {
                lastPosition = cowToPosition[curCow];
            } else {
                while (positionToCow[lastPosition] != 0) {
                    lastPosition--;
                }
            }
            positionToCow[lastPosition] = curCow;
        }
    }
}
