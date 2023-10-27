import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class CowGymnastics2 {
    static HashSet<List<Integer>> ansPairs;

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("gymnastics.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("gymnastics.out"));
        StringTokenizer st = new StringTokenizer(r.readLine());
        int k = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int[][] cows = new int[k][n];
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(r.readLine());
            for (int j = 0; j < n; j++) {
                cows[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 每场比赛都找所有<胜，负>组合 最后找得到交集
        for (int t = 0; t < k; t++) {
            HashSet<List<Integer>> curPairs = new HashSet<List<Integer>>();
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    List<Integer> pair = new ArrayList<Integer>();
                    pair.add(cows[t][i]);
                    pair.add(cows[t][j]);
                    curPairs.add(pair);
                }
            }
            if (ansPairs == null) {
                ansPairs = curPairs;
            } else {
                ansPairs.retainAll(curPairs);
            }
        }
        pw.println(ansPairs.size());
        pw.close();
    }
}
