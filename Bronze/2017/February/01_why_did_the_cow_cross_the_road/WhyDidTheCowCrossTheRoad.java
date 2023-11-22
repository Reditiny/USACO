import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class WhyDidTheCowCrossTheRoad {
    static int N;
    // 记录每头牛的位置，初始为 -1
    static int[] cowsLocation = new int[10];
    static int ans = 0;
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("crossroad.in"));
        PrintWriter pw = new PrintWriter("crossroad.out");
        N = Integer.parseInt(r.readLine());
        Arrays.fill(cowsLocation, -1);
        for (int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(r.readLine());
            int cow = Integer.parseInt(st.nextToken()) - 1;
            int location = Integer.parseInt(st.nextToken());
            if(cowsLocation[cow] != -1 && cowsLocation[cow] != location){
                ans++;
            }
            cowsLocation[cow] = location;
        }
        pw.println(ans);
        pw.close();
    }
}