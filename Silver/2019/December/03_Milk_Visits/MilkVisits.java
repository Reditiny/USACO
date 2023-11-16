import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.*;

/**
 * @author Red
 * @version 1.0
 */
public class MilkVisits {
    static int N, M;
    static String breeds;
    static List<List<Integer>> graph = new ArrayList<>();
    // 记录每个区域的id 相邻且品种相同的区域id相同 0表示未访问
    // 当起点和终点的区域id不同时 表明两者之间的路上一定有不同品种的牛
    static int[] regions;

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("milkvisits.in"));
        PrintWriter pw = new PrintWriter("milkvisits.out");
        StringTokenizer st = new StringTokenizer(r.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        breeds = r.readLine();
        for (int f = 0; f < N; f++) {
            graph.add(new ArrayList<>());
        }
        for (int f = 0; f < N - 1; f++) {
            StringTokenizer road = new StringTokenizer(r.readLine());
            int f1 = Integer.parseInt(road.nextToken()) - 1;
            int f2 = Integer.parseInt(road.nextToken()) - 1;
            graph.get(f1).add(f2);
            graph.get(f2).add(f1);
        }
        bfsWithMarkRegions();
        for (int q = 0; q < M; q++) {
            StringTokenizer query = new StringTokenizer(r.readLine());
            int a = Integer.parseInt(query.nextToken()) - 1;
            int b = Integer.parseInt(query.nextToken()) - 1;
            char c = query.nextToken().charAt(0);
            if (regions[a] == regions[b]) {
                if(breeds.charAt(a) == c){
                    pw.print(1);
                }else{
                    pw.print(0);
                }
            } else {
                pw.print(1);
            }
        }
        pw.close();
    }

    /**
     * 广度优先搜索 同时标记每个所属的区域
     */
    static void bfsWithMarkRegions() {
        int regionNum = 1;
        regions = new int[N];
        for (int f = 0; f < N; f++) {
            if (regions[f] != 0) {
                continue;
            }
            Queue<Integer> queue = new ArrayDeque<>();
            queue.add(f);
            char type = breeds.charAt(f);
            while (!queue.isEmpty()) {
                int curr = queue.poll();
                regions[curr] = regionNum;
                for (int n : graph.get(curr)) {
                    if (breeds.charAt(n) == type && regions[n] == 0) {
                        queue.add(n);
                    }
                }
            }
            regionNum++;
        }
    }
}
