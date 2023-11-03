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
        // 当 maxGap 较大时 总可以最少两步就放在一起 B___E______M -> E_B____M -> EMB
        // 最多需要 maxGap 步 较短一端的两头牛依次往最近的地方走  B___E______M -> EB_____M -> BE____M -> EB___M -> BE__M -> EB_M -> BEM
        // minGap == 1 时要额外考虑 B_E_____M -> BME 一步
        // minGap == 0 时要额外考虑 BE______M 只能从 maxGap 步中选取
        int minMove = Math.min(2, minGap), maxMove = maxGap;
        if (minGap == 0) {
            minMove = Math.min(2, maxGap);
        }
        pw.println(minMove);
        pw.println(maxMove);
        pw.close();
    }
}
