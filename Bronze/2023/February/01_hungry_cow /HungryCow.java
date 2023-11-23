import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class HungryCow {
    static int N;
    static long T;
    // 记录吃了多少草
    static long ans = 0;
    // 记录有草的最后一天
    static long lastDayWithFood = 0;
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(r.readLine());
        N = Integer.parseInt(st.nextToken());
        T = Long.parseLong(st.nextToken());
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(r.readLine());
            long d = Long.parseLong(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if(lastDayWithFood < d) {
                lastDayWithFood = d - 1;
            }
            if (lastDayWithFood + b >= T){
                ans += T - lastDayWithFood;
                break;
            }
            ans += b;
            lastDayWithFood += b;
        }
        pw.println(ans);
        pw.close();
    }
}