import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Red
 * @version 1.0
 */
public class WhereIsBessie {
    static int n;
    static char[][] image;
    static boolean[][] visited;
    // 用于限制当前遍历矩形的边界
    static int iMin, iMax, jMin, jMax;
    static int[][] move = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};


    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("where.in"));
        PrintWriter pw = new PrintWriter("where.out");
        n = Integer.parseInt(r.readLine());
        image = new char[n][n];
        for (int i = 0; i < n; i++) {
            String row = r.readLine();
            for (int j = 0; j < n; j++) {
                image[i][j] = row.charAt(j);
            }
        }
        // 遍历每一个矩形区域，判断是否是PCL
        List<List<Integer>> pcls = new ArrayList<>();
        for (int i1 = 0; i1 < n; i1++) {
            for (int j1 = 0; j1 < n; j1++) {
                for (int i2 = i1; i2 < n; i2++) {
                    for (int j2 = j1; j2 < n; j2++) {
                        if (isPCL(i1, j1, i2, j2)) {
                            pcls.add(Arrays.asList(i1, j1, i2, j2));
                        }
                    }
                }
            }
        }
        // PCL内部的PCL不计算在内
        int pclCount = 0;
        for (int i = 0; i < pcls.size(); i++) {
            boolean validPCL = true;
            for (int j = 0; j < pcls.size(); j++) {
                if (i == j) {
                    continue;
                }
                if (isInside(pcls.get(i), pcls.get(j))) {
                    validPCL = false;
                    break;
                }
            }
            if(validPCL){
                pclCount++;
            }
        }
        pw.println(pclCount);
        pw.close();
    }

    /**
     * 判断矩形范围是否是PCL
     * 同样的颜色记做连通，广度优先搜索获得连通块的数量
     * 当且仅当有两种颜色，其中一种颜色只有一个连通块，另一种颜色有多个连通块
     */
    static boolean isPCL(int i1, int j1, int i2, int j2) {
        iMin = i1;
        iMax = i2;
        jMin = j1;
        jMax = j2;
        visited = new boolean[n][n];
        // 广度优先搜索过程中记录每个颜色的连通块数量
        int[] regionCount = new int[26];
        for (int i = i1; i <= i2; i++) {
            for (int j = j1; j <= j2; j++) {
                if (!visited[i][j]) {
                    char currColor = image[i][j];
                    regionCount[currColor - 'A']++;
                    bfs(i, j, currColor);
                }
            }
        }
        // 根据连通块数量判断是否是PCL
        int colorCount = 0;
        boolean colorWithOneRegion = false;
        boolean colorWithMoreRegions = false;
        for (int i = 0; i < regionCount.length; i++) {
            if (regionCount[i] != 0) {
                colorCount++;
            }
            if (regionCount[i] == 1) {
                colorWithOneRegion = true;
            }
            if (regionCount[i] > 1) {
                colorWithMoreRegions = true;
            }
        }
        return (colorCount == 2 && colorWithOneRegion && colorWithMoreRegions);
    }

    /**
     * 广度优先搜索
     */
    static void bfs(int i, int j, char color) {
        if (i < iMin || j < jMin || i > iMax || j > jMax || visited[i][j] ||
                image[i][j] != color) {
            return;
        }

        visited[i][j] = true;
        for(int k = 0;k<4;k++){
            bfs(i + move[k][0], j + move[k][1], color);
        }
    }

    /**
     * 判断矩形 pcl1 是否在 pcl2 内部
     */
    static boolean isInside(List<Integer> pcl1, List<Integer> pcl2){
        return pcl1.get(0) >= pcl2.get(0) && pcl1.get(1) >= pcl2.get(1) &&
                pcl1.get(2) <= pcl2.get(2) && pcl1.get(3) <= pcl2.get(3);
    }
}
