import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class SpeedingTicket {
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("speeding.in"));
        PrintWriter pw = new PrintWriter("speeding.out");
        StringTokenizer st = new StringTokenizer(r.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        // 记录每单位距离的限速 单位为 1 mile
        int[] speedLimit = new int[100];
        int start = 0;
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(r.readLine());
            int distance = Integer.parseInt(st.nextToken());
            int speed = Integer.parseInt(st.nextToken());
            for(int j = start; j < start + distance; j++) {
                speedLimit[j] = speed;
            }
            start += distance;
        }
        // 记录每单位距离的实际速度 单位为 1 mile
        int[] realSpeed = new int[100];
        start = 0;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(r.readLine());
            int distance = Integer.parseInt(st.nextToken());
            int speed = Integer.parseInt(st.nextToken());
            for(int j = start; j < start + distance; j++) {
                realSpeed[j] = speed;
            }
            start += distance;
        }
        // 以 1 mile 为单位计算最大超速值
        int max = 0;
        for(int i=0;i<100;i++){
            if(realSpeed[i]-speedLimit[i]>max){
                max=realSpeed[i]-speedLimit[i];
            }
        }
        pw.println(max);
        pw.close();
    }
}
