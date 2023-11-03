import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class LoadBalancing {
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("balancing.in"));
        PrintWriter pw = new PrintWriter("balancing.out");
        StringTokenizer line = new StringTokenizer(r.readLine());
        int n = Integer.parseInt(line.nextToken());
        int B = Integer.parseInt(line.nextToken());
        int[][] cows = new int[n][2];
        for (int i = 0; i < n; i++) {
            line = new StringTokenizer(r.readLine());
            cows[i][0] = Integer.parseInt(line.nextToken());
            cows[i][1] = Integer.parseInt(line.nextToken());
        }
        int ans = n;
        // 枚举所有可能的分割线
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int x = cows[i][0] + 1;
                int y = cows[j][1] + 1;
                int[] cowCount = new int[4];
                for (int[] cow : cows) {
                    if (cow[0] < x && cow[1] < y) {
                        cowCount[0]++;
                    } else if (cow[0] < x && cow[1] > y) {
                        cowCount[1]++;
                    } else if (cow[0] > x && cow[1] < y) {
                        cowCount[2]++;
                    } else {
                        cowCount[3]++;
                    }
                }
                ans = Math.min(ans, Arrays.stream(cowCount).max().getAsInt());
            }
        }
        pw.println(ans);
        pw.close();
    }
}
