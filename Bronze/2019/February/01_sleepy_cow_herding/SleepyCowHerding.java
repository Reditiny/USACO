import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class SleepyCowHerding {
    static int cowNum = 3;

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("herding.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("herding.out"));
        StringTokenizer st = new StringTokenizer(r.readLine());
        List<Integer> cows = new ArrayList<Integer>();
        for (int i = 0; i < cowNum; i++) {
            cows.add(Integer.parseInt(st.nextToken()));
        }
        cows.sort(Integer::compareTo);
        int gap1 = cows.get(1) - cows.get(0);
        int gap2 = cows.get(2) - cows.get(1);
        // 得到相邻两头牛之间的位置
        int minGap = Math.min(gap1, gap2) - 1;
        int maxGap = Math.max(gap1, gap2) - 1;
        // 当 maxGap 较大时 总可以最少两步就放在一起 1___2______3 -> 2_1____3 -> 231
        // 最多需要 maxGap 步 1___2______3 -> 2_____13 -> 23____1 -> 3___21 ....
        // minGap == 1 时要额外考虑 1_2_____3 -> 132 一步
        // minGap == 0 时要额外考虑 12______3 只能从 maxGap 步中选取
        int minMove = Math.min(2, minGap), maxMove = maxGap;
        if (minGap == 0) {
            minMove = Math.min(2, maxGap);
        }
        pw.println(minMove);
        pw.println(maxMove);
        pw.close();
    }
}
