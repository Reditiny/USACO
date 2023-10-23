import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * @author Red
 * @version 1.0
 */
public class MilkMeasurement {
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("measurement.in"));
        PrintWriter pw = new PrintWriter("measurement.out");
        int n = Integer.parseInt(r.readLine());
        // 0 Bessie 1 Elsie 2 Mildred 按日期顺序依次记录三头牛的奶量变化量
        int[][] measurements = new int[101][3];
        for (int i = 0; i < n; i++) {
            String[] s = r.readLine().split(" ");
            switch (s[1]) {
                case "Bessie":
                    measurements[Integer.parseInt(s[0])][0] = Integer.parseInt(s[2]);
                    break;
                case "Elsie":
                    measurements[Integer.parseInt(s[0])][1] = Integer.parseInt(s[2]);
                    break;
                case "Mildred":
                    measurements[Integer.parseInt(s[0])][2] = Integer.parseInt(s[2]);
                    break;
            }
        }
        int ans = 0;
        // 0 Bessie 1 Elsie 2 Mildred 按日期顺序依次记录三头牛的奶量，每次记录后判断是否需要修改display
        int[] lastMilk = {7, 7, 7};
        boolean[] lastDisplay = {false, false, false};
        for (int i = 1; i <= 100; i++) {
            if (measurements[i][0] != 0 || measurements[i][1] != 0 || measurements[i][2] != 0) {
                int[] curMilk = lastMilk.clone();
                curMilk[0] += measurements[i][0];
                curMilk[1] += measurements[i][1];
                curMilk[2] += measurements[i][2];
                boolean[] curDisplay = makeDisplay(curMilk);
                if (!Arrays.equals(curDisplay, lastDisplay)) {
                    ans++;
                    lastDisplay = curDisplay;
                }
                lastMilk = curMilk;
            }
        }
        pw.println(ans);
        pw.close();
    }

    /**
     * 根据奶量得到当前的display
     */
    public static boolean[] makeDisplay(int[] curMilk) {
        boolean[] curOrder = new boolean[3];
        int max = Math.max(curMilk[0], Math.max(curMilk[1], curMilk[2]));
        for (int i = 0; i < 3; i++) {
            if (curMilk[i] == max) {
                curOrder[i] = true;
            }
        }
        return curOrder;
    }
}


