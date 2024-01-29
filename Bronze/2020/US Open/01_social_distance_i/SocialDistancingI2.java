import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Red
 * @version 1.0
 */
public class SocialDistancingI2 {
    static int N;
    static int[] largestGapInfo;
    static int answer = 0;

    static char[] s;

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("socdist1.in"));
        PrintWriter pw = new PrintWriter("socdist1.out");
        N = Integer.parseInt(r.readLine().trim());
        s = r.readLine().trim().toCharArray();

        answer = 0;

        // 1. 两头牛在同一个间隔中，该间隔一定为最大间隔
        largestGapInfo = findLargestInteriorGap(s);
        if (largestGapInfo[0] >= 3) {
            char[] tempS = s.clone();
            tempS[largestGapInfo[1] + largestGapInfo[0] / 3] = '1';
            tempS[largestGapInfo[1] + largestGapInfo[0] * 2 / 3] = '1';
            answer = Math.max(answer, findSmallestInteriorGap(tempS));
        }
        // 2. 两头牛分别在两端
        if (s[0] == '0' && s[N - 1] == '0') {
            char[] tempS = s.clone();
            tempS[0] = '1';
            tempS[N - 1] = '1';
            answer = Math.max(answer, findSmallestInteriorGap(tempS));
        }
        // 3. 一头牛在最左，一头牛在最大间隔中
        if (s[0] == '0') {
            char[] tempS = s.clone();
            tempS[0] = '1';
            answer = Math.max(answer, tryCowInLargestGap(tempS));
        }
        // 4. 一头牛在最右，一头牛在最大间隔中
        if (s[N - 1] == '0') {
            char[] tempS = s.clone();
            tempS[N - 1] = '1';
            answer = Math.max(answer, tryCowInLargestGap(tempS));
        }

        // 5. 两头牛分别在最大次大间隔（次大间隔可能是最大间隔插入之后的间隔）
        if (largestGapInfo[0] >= 2) {
            char[] tempS = s.clone();
            tempS[largestGapInfo[1] + largestGapInfo[0] / 2] = '1';
            answer = Math.max(answer, tryCowInLargestGap(tempS));
        }

        pw.println(answer);
        pw.close();
    }

    /**
     * 找到最大间隔
     */
    private static int[] findLargestInteriorGap(char[] s) {
        int gapStart = -1;
        int biggestGap = 0;
        int currentStart = -1;

        for (int i = 0; i < s.length; i++) {
            if (s[i] == '1') {
                // compare biggestGap with an existing gap
                if (currentStart != -1 && i - currentStart > biggestGap) {
                    biggestGap = i - currentStart;
                    gapStart = currentStart;
                }
                currentStart = i;
            }
        }

        return new int[]{biggestGap, gapStart};
    }

    /**
     * 找到最小间隔
     */
    private static int findSmallestInteriorGap(char[] s) {
        int smallestGap = s.length + 1;
        int currentStart = -1;

        for (int i = 0; i < s.length; i++) {
            if (s[i] == '1') {
                if (currentStart != -1 && i - currentStart < smallestGap) {
                    smallestGap = i - currentStart;
                }
                currentStart = i;
            }
        }

        return smallestGap;
    }

    /**
     * 得到在最大间隔中插入一头牛之后的间隔
     */
    private static int tryCowInLargestGap(char[] s) {
        int[] largestGapInfo = findLargestInteriorGap(s);
        if (largestGapInfo[0] >= 2) {
            s[largestGapInfo[1] + largestGapInfo[0] / 2] = '1';
            return findSmallestInteriorGap(s);
        }
        return -1;  // no gap
    }
}