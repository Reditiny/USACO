import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.*;

/**
 * @author Red
 * @version 1.0
 */
public class WormholeSort2 {
    static int n, m;
    static boolean ordered = true;
    static int[] orders;
    static List<Integer> widths = new ArrayList<>();
    static HashMap<Integer,List<int[]>> graph = new HashMap<>();
    static int[] regions;

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("wormsort.in"));
        PrintWriter pw = new PrintWriter("wormsort.out");
        StringTokenizer st = new StringTokenizer(r.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        orders = new int[n +  1];
        st = new StringTokenizer(r.readLine());
        for (int i = 1; i <= n; i++) {
            orders[i] = Integer.parseInt(st.nextToken());
            if (i != orders[i]) {
                ordered = false;
            }
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(r.readLine());
            int a =Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph.putIfAbsent(a,new ArrayList<>());
            graph.putIfAbsent(b,new ArrayList<>());
            graph.get(a).add(new int[]{b, w});
            graph.get(b).add(new int[]{a, w});
            widths.add(w);
        }
        // 已经有序
        if (ordered) {
            pw.println(-1);
            pw.close();
            return;
        }
        // 按 weight 从小到大排序
        widths.sort(Integer::compareTo);
        // 二分查找最小的 width
        int left = 0, right = m - 1;
        while (left < right){
            int mid = (left + right) / 2;
            if (isValid(widths.get(mid))){
                left = mid + 1;
            }else {
                right = mid - 1;
            }
        }
        if (isValid(widths.get(left))){
            pw.println(widths.get(left));
        }else {
            pw.println(widths.get(left - 1));
        }
        pw.close();
    }

    /**
     * 检查当前的 minWidth 是否满足条件
     */
    static boolean isValid(int minWidth) {
        regions = new int[n + 1];
        int label = 1;
        for (int i = 1; i <= n; i++) {
            if (regions[i] == 0) {
                dfs(i, minWidth, label);
                label++;
            }
        }
        // 第 i 头牛最终位置和第 i 头牛初始位置应该在同一个连通图里才能交换成功
        for (int i = 1; i <= n; i++) {
            if (regions[i] != regions[orders[i]]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 深度优先搜索遍历并标记连通图序号
     */
    static void dfs(int current, int minWidth, int label) {
        if (regions[current] > 0) {
            return;
        }
        regions[current] = label;
        for (int[] next : graph.get(current)) {
            if (minWidth <= next[1]) {
                dfs(next[0], minWidth, label);
            }
        }
    }

}