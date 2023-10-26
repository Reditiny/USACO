import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class AirCownditioningII {

    public static void main(String[] args) throws Exception {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(read.readLine());
        int cowNum = Integer.parseInt(st.nextToken());
        int acNum = Integer.parseInt(st.nextToken());
        List<List<Integer>> cows = new ArrayList<List<Integer>>();
        for (int c = 0; c < cowNum; c++) {
            st = new StringTokenizer(read.readLine());
            List<Integer> cow = new ArrayList<Integer>();
            cow.add(Integer.parseInt(st.nextToken()));
            cow.add(Integer.parseInt(st.nextToken()));
            cow.add(Integer.parseInt(st.nextToken()));
            cows.add(cow);
        }
        List<List<Integer>> acs = new ArrayList<List<Integer>>();
        for (int a = 0; a < acNum; a++) {
            st = new StringTokenizer(read.readLine());
            List<Integer> ac = new ArrayList<Integer>();
            ac.add(Integer.parseInt(st.nextToken()));
            ac.add(Integer.parseInt(st.nextToken()));
            ac.add(Integer.parseInt(st.nextToken()));
            ac.add(Integer.parseInt(st.nextToken()));
            acs.add(ac);
        }

        int minCost = Integer.MAX_VALUE;
        // 位运算遍历 每个空调用一个bit表示是否安装 遍历所有可能组合
        for (int mask = 0; mask < (1 << acNum); mask++) {
            int[] stalls = new int[101];
            int cost = 0;
            for (int a = 0; a < acNum; a++) {
                // 仅找出对应bit位为1的空调
                if ((mask & (1 << a)) != 0) {
                    for (int i = acs.get(a).get(0); i <= acs.get(a).get(1); i++) {
                        stalls[i] += acs.get(a).get(2);
                    }
                    cost += acs.get(a).get(3);
                }
            }

            boolean valid = true;
            for (List<Integer> c : cows) {
                for (int i = c.get(0); i <= c.get(1); i++) {
                    if (stalls[i] < c.get(2)) {
                        valid = false;
                        break;
                    }
                }
            }
            if (valid) {
                minCost = Math.min(minCost, cost);
            }
        }

        System.out.println(minCost);
    }
}