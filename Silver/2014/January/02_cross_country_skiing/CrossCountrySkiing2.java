 import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
 import java.util.*;

 /**
 * @author Red
 * @version 1.0
 */
public class CrossCountrySkiing2 {
    static int M, N, D;
    static int[][] grid;
    static List<Edge> edges = new ArrayList<Edge>();
    static HashSet<Point> spanningPoints = new HashSet<>();
     static HashSet<Point> allPoints = new HashSet<>();
    static HashSet<Point> wayPoints = new HashSet<>();
    static UF uf;
    static int[][] moves = new int[][]{{1, 0}, {0, 1}};
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("ccski.in"));
        PrintWriter pw = new PrintWriter("ccski.out");
        StringTokenizer st = new StringTokenizer(r.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        grid = new int[M][N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(r.readLine());
            for (int j = 0; j < N; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(r.readLine());
            for (int j = 0; j < N; j++) {
                if (Integer.parseInt(st.nextToken()) == 1) {
                    wayPoints.add(new Point(i, j));
                }
            }
        }
        // 构造边集
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                for (int s = 0; s < 2; s++) {
                    int nextI = i + moves[s][0];
                    int nextJ = j + moves[s][1];
                    // 从当前点出发只需要考虑向右和向下的边
                    // 因为向左和向上的边会在其之前的点考虑
                    if (nextI >= 0 && nextI < M && nextJ >= 0 && nextJ < N) {
                        Point point1 = new Point(i, j);
                        Point point2 = new Point(nextI, nextJ);
                        edges.add(new Edge(point1, point2, Math.abs(grid[i][j] - grid[nextI][nextJ])));
                        allPoints.add(point1);
                        allPoints.add(point2);
                    }
                }
            }
        }
        // 构造并查集
        uf = new UF(allPoints);
        // 遍历边构造最小生成树
        edges.sort((a, b) -> a.weight - b.weight);
        for (int i = 0; i < edges.size(); i++) {
            D = edges.get(i).weight;
            Point point1 = edges.get(i).point1;
            Point point2 = edges.get(i).point2;
            // 并查集构造最小生成树
            if (!uf.connected(point1, point2)) {
                uf.union(point1, point2);
            }
            // 每次加入一条边后判断是否所有的 wayPoints 都连通
            // 原克鲁斯卡尔算法只需要遍历完所有的边即可，此时因为要找停止条件
            // 所以需要每次加入一条边后判断，此处复杂度由 wayPoints.size() 决定
            if (wayPointsConnected()) {
                break;
            }
        }
        pw.println(D);
        pw.close();
    }

    static boolean wayPointsConnected(){
        for(Point p : wayPoints){
            if(!uf.connected(p,wayPoints.iterator().next())){
                return false;
            }
        }
        return true;
    }

    private static class UF{
        Map<Point,Point> map = new HashMap<>();

        public UF(Set<Point> points){
            for(Point p : points){
                map.put(p,p);
            }
        }
        public Point find(Point p){
            if(map.get(p) == p){
                return p;
            }
            Point root = find(map.get(p));
            map.put(p,root);
            return root;
        }
        public void union(Point p1, Point p2){
            Point root1 = find(p1);
            Point root2 = find(p2);
            if(root1 != root2){
                map.put(root1,root2);
            }
        }
        public boolean connected(Point p1, Point p2){
            return find(p1) == find(p2);
        }
    }
    private static class Edge{
        public Point point1;
        public Point point2;
        public int weight;
        public Edge(Point point1, Point point2, int weight){
            this.point1 = point1;
            this.point2 = point2;
            this.weight = weight;
        }
    }
    private static class Point{
        int i;
        int j;
        public Point(int i, int j){
            this.i = i;
            this.j = j;
        }
        @Override
        public boolean equals(Object o){
            if(o instanceof Point){
                Point p = (Point)o;
                return p.i == i && p.j == j;
            }
            return false;
        }
        @Override
        public int hashCode(){
            return i * 100000 + j;
        }
    }
}
