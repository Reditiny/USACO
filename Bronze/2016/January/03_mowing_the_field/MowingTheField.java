import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;

/**
 * @author Red
 * @version 1.0
 */
public class MowingTheField {
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("mowing.in"));
        PrintWriter pw = new PrintWriter("mowing.out");
        int n = Integer.parseInt(r.readLine());
        // 给定草地 起点为中心 且保证足够大给定输入不会越界
        int[][] grass = new int[2001][2001];
        int x = 1000;
        int y = 1000;
        int mark = 1;    // 自增地标记每次走过的格子
        int ans = 10000;
        boolean cellMoreThanOnce = false;
        grass[x][y] = 1;
        for (int i = 0; i < n; i++) {
            String[] s = r.readLine().split(" ");
            int step = Integer.parseInt(s[1]);
            switch (s[0]) {
                case "N":
                    for (int j = 0; j < step; j++) {
                        y++;
                        mark++;
                        if (grass[x][y] != 0) {   // 值不为零说明这个格子被走过了 两次标记的差即为间隔 x
                            cellMoreThanOnce = true;
                            ans = Math.min(ans, mark - grass[x][y]);
                        }
                        grass[x][y] = mark;
                    }
                    break;
                case "S":
                    for (int j = 0; j < step; j++) {
                        y--;
                        mark++;
                        if (grass[x][y] != 0) {
                            cellMoreThanOnce = true;
                            ans = Math.min(ans, mark - grass[x][y]);
                        }
                        grass[x][y] = mark;
                    }
                    break;
                case "W":
                    for (int j = 0; j < step; j++) {
                        x--;
                        mark++;
                        if (grass[x][y] != 0) {
                            cellMoreThanOnce = true;
                            ans = Math.min(ans, mark - grass[x][y]);
                        }
                        grass[x][y] = mark;
                    }
                    break;
                case "E":
                    for (int j = 0; j < step; j++) {
                        x++;
                        mark++;
                        if (grass[x][y] != 0) {
                            cellMoreThanOnce = true;
                            ans = Math.min(ans, mark - grass[x][y]);
                        }
                        grass[x][y] = mark;
                    }
                    break;
                default:
                    break;
            }
        }
        if (cellMoreThanOnce) {
            pw.println(ans);
        } else {
            pw.println(-1);
        }
        pw.close();
    }
}
