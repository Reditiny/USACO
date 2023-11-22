import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class HoofPaperScissors {
    static int N;
    // 各个手势的可能对应含义
    static char[][] rules = new char[][]{{'H','P','S'},{'P','S','H'},{'S','H','P'},{'H','S','P'},{'P','H','S'},{'S','P','H'}};
    static int[][] gestures;
    static int ans = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("hps.in"));
        PrintWriter pw = new PrintWriter("hps.out");
        N = Integer.parseInt(r.readLine());
        gestures = new int[N][2];
        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(r.readLine());
            gestures[i][0] = Integer.parseInt(st.nextToken()) - 1;
            gestures[i][1] = Integer.parseInt(st.nextToken()) - 1;
        }
        for(char[] rule : rules){
            int curAns = 0;
            for(int[] gesture: gestures){
                curAns += check(rule, gesture);
            }
            ans = Math.max(ans, curAns);
        }
        pw.println(ans);
        pw.close();
    }

    /**
     * 检查当前规则下，当前手势,第一头牛是否赢
     */
    private static int check(char[] rule, int[] gesture){
        char cow1 = rule[gesture[0]];
        char cow2 = rule[gesture[1]];
        if((cow1 == 'H' && cow2 == 'S') || (cow1 == 'P' && cow2 == 'H') || (cow1 == 'S' && cow2 == 'P')){
            return 1;
        }
        return 0;
    }
}