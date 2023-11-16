import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class ClockTree {
    static int N;
    static int[] clocks;
    static List<List<Integer>> graph;
    static int clock0Sum = 0, clock1Sum = 0, clock0Count = 0, clock1Count = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("clocktree.in"));
        PrintWriter pw = new PrintWriter("clocktree.out");
        N = Integer.parseInt(r.readLine());
        clocks = new int[N];
        graph = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(r.readLine());
        for (int i = 0; i < N; i++) {
            clocks[i] = Integer.parseInt(st.nextToken());
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(r.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        // 首先考虑一个简单的情况:图中有2个节点和一条边
        // 那么贝茜可以从其中一个房间开始，走到相邻的房间，加大时钟，再往回走，加大起始房间的时钟。
        // 在这个来回移动的过程中，两个房间的时钟读数之间的差异变化不超过1。
        // 如果开始的时钟读数相差1，贝西可以从更大的时钟阅览室开始，来回移动，直到两个时钟都指向12。
        // 如果起始时钟读数相差超过1，贝西就不可能让两个时钟都指向12
        // 如果有更多的节点，可以通过进行二部分割来类似于两节点的情况来处理
        // 因为这张图是一棵树，总是可以用0和1来对相邻节点染色，使得每个相邻节点的颜色都不同，这样就分出了0和1两组
        // 如上面提到的简单情况，现在复杂情况的两组中的时钟读数和之差不能超过1
        dfs(0, 0, -1);

        // 两组合和余数相等 从任意点开始都可以
        // 相差1 则从大的那组开始
        // 相差大于1 则无法完成
        if ((clock0Sum % 12) == (clock1Sum % 12)) {
            pw.println(N);
        } else if ((clock0Sum + 1) % 12 == (clock1Sum % 12)) {
            pw.println(clock1Count);
        } else if ((clock0Sum % 12) == ((clock1Sum + 1) % 12)) {
            pw.println(clock0Count);
        } else {
            pw.println(0);
        }
        pw.close();
    }

    /**
     * 深度优先搜索遍历每个节点 并用0和1给每个节点染色 同时记录每组节点数
     *
     * @param i      当前节点
     * @param color  当前节点的颜色
     * @param parent 当前节点的父节点
     */
    static void dfs(int i, int color, int parent) {
        if (color == 0) {
            clock0Count++;
            clock0Sum += clocks[i];
        } else {
            clock1Count++;
            clock1Sum += clocks[i];
        }
        for (int j : graph.get(i)) {
            if (j != parent) {
                // 子节点换颜色
                dfs(j, 1 - color, i);
            }
        }
    }
}