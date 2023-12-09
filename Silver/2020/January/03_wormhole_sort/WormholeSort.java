import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.*;

/**
 * @author Red
 * @version 1.0
 */
public class WormholeSort {
    static int n, m;
    static List<List<Integer>> wormholes = new ArrayList<>();
    // 存储需要排序的节点的图，当所有需要排序的节点都在图中且图连通时，那就可以通过 wormhole 完成排序
    static List<List<Integer>> graph = new ArrayList<>();
    // needSort 记录每个节点是否需要排序，用于判断是否将节点放入图中，需要排序的节点才放入图
    static boolean[] needSort;
    // visited 配合 needSort 使用
    // needSort 中为 true 的节点不在最终位置上，需要用 wormhole 遍历到
    // 所以遍历完后要求对应 visited 为 true
    static boolean[] visited;
    // 需要排序的节点最初都不在图内，随着 wormhole 的遍历，将节点放入图中
    // 当所有节点都在图中时，即 inOfGraph 全为 true 才开始 dfs
    static boolean[] inGraph;


    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("wormsort.in"));
        PrintWriter pw = new PrintWriter("wormsort.out");
        StringTokenizer st = new StringTokenizer(r.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        needSort = new boolean[n];
        inGraph = new boolean[n];
        Arrays.fill(needSort,true);
        visited = new boolean[n];
        st = new StringTokenizer(r.readLine());
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
            if (i != Integer.parseInt(st.nextToken()) - 1) {
                needSort[i] = true;
                inGraph[i] = false;
            }
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(r.readLine());
            List<Integer> wormhole = new ArrayList<>();
            wormhole.add(Integer.parseInt(st.nextToken()) - 1);
            wormhole.add(Integer.parseInt(st.nextToken()) - 1);
            wormhole.add(Integer.parseInt(st.nextToken()));
            wormholes.add(wormhole);
        }
        // 当 wormholes 覆盖了所有需要排序的点且所有点连通时才能满足条件
        // 按 weight 从大到小排序
        wormholes.sort((o1, o2) -> o2.get(2).compareTo(o1.get(2)));
        int ans = -1;
        boolean start = false;
        // 遍历 wormhole
        for (int i = 0; i < m; i++) {
            if (match()) {
                break;
            }
            List<Integer> wormhole = WormholeSort.wormholes.get(i);
            int a = wormhole.get(0);
            int b = wormhole.get(1);
            inGraph[a] = true;
            inGraph[b] = true;
            if(!start && allInGraph()){
                // 开始 dfs 只有当所有需要排序的元素都在图中才开始
                start = true;
            }
            ans = wormhole.get(2);
            // 只有当 wormhole 两端的元素至少有一个元素需要排序时才放入图
            if (needSort[a] || needSort[b]) {
                graph.get(a).add(b);
                graph.get(b).add(a);
            }
            if(start){
                Arrays.fill(visited, false);
                dfs(a);
            }
        }
        pw.println(ans);
        pw.close();
    }

    /**
     * 深度优先搜索遍历
     * @param i
     */
    static void dfs(int i) {
        if (visited[i]) {
            return;
        }
        visited[i] = true;
        for (int neighbor : graph.get(i)) {
            dfs(neighbor);
        }
    }

    /**
     * 当前图是否连通且覆盖所有不在最终位置元素
     */
    static boolean match() {
        for (int i = 0; i < n; i++) {
            if (needSort[i] && !visited[i]) {
                return false;
            }
        }
        return true;
    }
    static boolean allInGraph(){
        for(int i=0;i<n;i++){
            if(!inGraph[i]){
                return false;
            }
        }
        return true;
    }
}