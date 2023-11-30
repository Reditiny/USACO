import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.*;

/**
 * @author Red
 * @version 1.0
 */
public class ClosingTheFarm2 {
    static int n;
    static int m;
    static List<List<Integer>> graph = new ArrayList<>();
    static boolean[] visited;
    static boolean[] closed;
    static Map<Integer, List<Integer>> regionIdToFarm = new HashMap<>();
    static Map<Integer, Integer> farmToRegionId = new HashMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("closing.in"));
        PrintWriter pw = new PrintWriter("closing.out");
        StringTokenizer st = new StringTokenizer(r.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(r.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        visited = new boolean[n];
        // 遍历图记录连通图数量
        int regionId = 0;
        for (int i = 0; i <n;i++){
            if (!visited[i]){
                regionIdToFarm.put(regionId, new ArrayList<>());
                dfs(i, regionId);
                regionId++;
            }
        }
        if (regionId == 1){
            pw.println("YES");
        } else {
            pw.println("NO");
        }
        // 依次关闭 barn
        // 遍历 barn 所在连通图中的其他 barn 构成新的连通图数量
        closed = new boolean[n];
        for (int i = 0; i < n - 1; i++) {
            int closedFarm = Integer.parseInt(r.readLine()) - 1;
            int farmRegionId = farmToRegionId.get(closedFarm);
            List<Integer> farmsInSameRegion = regionIdToFarm.get(farmRegionId);
            closed[closedFarm] = true;
            visited = closed.clone();
            regionIdToFarm.remove(farmRegionId);
            for (int farm : farmsInSameRegion){
                if (!visited[farm]){
                    regionIdToFarm.put(regionId, new ArrayList<>());
                    dfs(farm, regionId);
                    regionId++;
                }
            }
            if (regionIdToFarm.size() == 1){
                pw.println("YES");
            } else {
                pw.println("NO");
            }
        }
        pw.close();
    }

    /**
     * 深度优先搜索遍历图 并记录连通图id
     */
    static void dfs(int current, int regionId){
        if (visited[current]){
            return;
        }
        visited[current] = true;
        farmToRegionId.put(current, regionId);
        regionIdToFarm.get(regionId).add(current);
        for (int next : graph.get(current)){
            if (!visited[next]){
                dfs(next, regionId);
            }
        }

    }
}