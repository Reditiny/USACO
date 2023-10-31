import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.*;

/**
 * @author Red
 * @version 1.0
 */
public class MilkingOrder {
    static int[] cowOrder;
    static List<Integer> cowHierarchy;

    static Map<Integer, Integer> cowToOrder = new HashMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("milkorder.in"));
        PrintWriter pw = new PrintWriter("milkorder.out");
        StringTokenizer st = new StringTokenizer(r.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        cowOrder = new int[n];
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
            cowOrder[position - 1] = cow;
            cowToOrder.put(cow, position - 1);
        }
        int lastPosition = 0;
        // hierarchy 中是否包含 cow1 会有不同的处理方案
        if (hierarchyContainCow1) {
            lastPosition = putPrefixHierarchy();
        } else {
            putAllHierarchy();
        }
        for (int i = lastPosition; i < n; i++) {
            if (cowOrder[i] == 0) {
                pw.println(i + 1);
                break;
            }
        }
        pw.close();
    }

    /**
     * 将 Hierarchy 中 1 前面的牛都按序放入队列中
     *
     * @return 1 前面最后一个牛的位置
     */
    private static int putPrefixHierarchy() {
        int lastPosition = 0;
        while (cowOrder[lastPosition] != 0) {
            lastPosition++;
        }
        for (int i = 0; i < cowHierarchy.size(); i++) {
            int curCow = cowHierarchy.get(i);
            if (curCow == 1) {
                break;
            }
            if (cowToOrder.containsKey(curCow)) {
                lastPosition = cowToOrder.get(curCow);
            } else {
                while (cowOrder[lastPosition] != 0) {
                    lastPosition++;
                }
            }
            cowOrder[lastPosition] = curCow;
        }
        return lastPosition;
    }

    /**
     * 将 Hierarchy 中所有牛都按序放入队列中
     */
    static public void putAllHierarchy() {
        int lastPosition = cowOrder.length - 1;
        while (cowOrder[lastPosition] != 0) {
            lastPosition--;
        }
        for (int i = cowHierarchy.size() - 1; i >= 0; i--) {
            int curCow = cowHierarchy.get(i);
            if (cowToOrder.containsKey(curCow)) {
                lastPosition = cowToOrder.get(curCow);
            } else {
                while (cowOrder[lastPosition] != 0) {
                    lastPosition--;
                }
            }
            cowOrder[lastPosition] = curCow;
        }
    }
}
