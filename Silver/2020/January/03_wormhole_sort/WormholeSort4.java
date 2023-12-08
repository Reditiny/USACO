import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.*;

/**
 * @author Red
 * @version 1.0
 */
public class WormholeSort4 {
    static int N, M;
    static int[] orders;
    static WormHole[] Edges;

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("wormsort.in"));
        PrintWriter pw = new PrintWriter("wormsort.out");
        StringTokenizer st = new StringTokenizer(r.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(r.readLine());
        orders = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            orders[i] = Integer.parseInt(st.nextToken());
        }
        WormHole[] edges = new WormHole[M];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(r.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            edges[i] = new WormHole(w, a, b);
        }
        // 按虫洞宽度降序排列
        Arrays.sort(edges,(e1,e2)-> e2.w - e1.w);
        UF uf = new UF(N);
        // 已经有序的牛都放入 0 号集合
        for (int i = 1; i <= N; i++) {
            if (orders[i] == i) {
                uf.union(0, i);
            }
        }
        // 如果所有牛都在 0 号集合，说明已经有序
        if (uf.count == 1) {
            pw.println(-1);
            pw.close();
            return;
        }
        // 从最大的虫洞宽度开始遍历，每次将两个牛所在的集合合并，直到所有牛都一个集合内
        for (WormHole e : edges) {
            uf.union(e.a, e.b);
            if (uf.count == 1) {
                pw.println(e.w);
                pw.close();
                return;
            }
        }
    }

    static class WormHole {
        int w, a, b;

        public WormHole(int w, int a, int b) {
            this.w = w;
            this.a = a;
            this.b = b;
        }
    }

    static class UF {
        int count;
        int[] roots;
        int[] sizes;

        public UF(int N) {
            this.count = N + 1;
            this.roots = new int[N + 1];
            this.sizes = new int[N + 1];
            for (int i = 0; i <= N; i++) {
                roots[i] = i;
                sizes[i] = 1;
            }
        }

        public int find(int id) {
            while (id != roots[id]) {
                roots[id] = roots[roots[id]];
                id = roots[id];
            }
            return id;
        }

        public void union(int id1, int id2) {
            int root1 = find(id1);
            int root2 = find(id2);
            if (root1 == root2) {
                return;
            }
            if (sizes[root1] > sizes[root2]) {
                roots[root2] = root1;
                sizes[root1] += sizes[root2];
            } else {
                roots[root1] = root2;
                sizes[root2] += sizes[root1];
            }
            count--;
        }
    }


}

