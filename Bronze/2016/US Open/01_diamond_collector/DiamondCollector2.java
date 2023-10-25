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
public class DiamondCollector2 {
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("diamond.in"));
        PrintWriter pw = new PrintWriter("diamond.out");
        StringTokenizer st = new StringTokenizer(r.readLine());
        List<Integer> diamonds = new ArrayList<>();
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        for (int i = 0; i < n; i++) {
            diamonds.add(Integer.parseInt(r.readLine()));
        }
        int ans = 0;
        // 对于每一个 diamonds[i] 分别求 [diamonds[i],diamonds[i]+k] 和 [diamonds[i]-k,diamonds[i]] 两个范围的数量
        for (int i = 0; i < diamonds.size(); i++) {
            int floorCount = 0;
            int ceilingCount = 0;
            for (int j = 0; j < diamonds.size(); j++) {
                if (diamonds.get(j) >= diamonds.get(i) && diamonds.get(j) <= diamonds.get(i) + k) {
                    floorCount++;
                }
                if (diamonds.get(j) <= diamonds.get(i) && diamonds.get(j) >= diamonds.get(i) - k) {
                    ceilingCount++;
                }
            }
            ans = Math.max(ans, Math.max(floorCount, ceilingCount));
        }
        pw.println(ans);
        pw.close();
    }
}
