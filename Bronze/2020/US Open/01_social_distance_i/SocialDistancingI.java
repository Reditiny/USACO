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
public class SocialDistancingI {
    static int N;
    static char[] stalls;
    static List<Integer> cowPositions;

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("socdist1.in"));
        PrintWriter pw = new PrintWriter("socdist1.out");
        N = Integer.parseInt(r.readLine());
        cowPositions = new ArrayList<>();
        stalls = new char[N];
        stalls = r.readLine().toCharArray();
        for (int i = 0; i < N; i++) {
            if (stalls[i] == '1') {
                cowPositions.add(i);
            }
        }
        if (cowPositions.size() == 0){
            pw.println(N - 1);
            pw.close();
            return;
        }
        int left = 0, right = N;
        // 二分查找最大的 d
        while (left < right) {
            int mid = (left + right + 1) / 2;
            if (check(mid)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        pw.println(left);
        pw.close();

    }

    /**
     * 检查当前 d 是否可以插入两头牛
     */
    static boolean check(int d) {
        int newCow = 2;
        // 单独检查前缀能插入几头牛 前缀只有一边有牛
        int firstDistance = cowPositions.get(0);
        if (firstDistance >= 2 * d) {
            newCow -= 2;
        } else if (firstDistance >= d) {
            newCow -= 1;
        }
        // 检查中间部分能插入几头牛同时注意本身距离也要满足条件 中间部分两边都有牛
        for (int i = 1; i < cowPositions.size(); i++) {
            int distance = cowPositions.get(i) - cowPositions.get(i - 1);
            if(distance >= 3 * d) {
                newCow -= 2;
            } else if(distance >= 2 * d){
                newCow -= 1;
            }else if(distance < d){
                return false;
            }
        }
        // 单独检查后缀能插入几头牛 后缀只有一边有牛
        int lastDistance = N -1 - cowPositions.get(cowPositions.size() - 1);
        if(lastDistance >= 2 * d){
            newCow -= 2;
        } else if(lastDistance >= d){
            newCow -= 1;
        }
        return newCow <= 0;
    }
}