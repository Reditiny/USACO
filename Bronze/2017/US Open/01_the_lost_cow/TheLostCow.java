import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

import static java.lang.Math.pow;

/**
 * @author Red
 * @version 1.0
 */
public class TheLostCow {
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("lostcow.in"));
        PrintWriter pw = new PrintWriter("lostcow.out");
        StringTokenizer st = new StringTokenizer(r.readLine());
        int farmerPosition = Integer.parseInt(st.nextToken());
        int cowPosition = Integer.parseInt(st.nextToken());
        // 每次往右的跨度为 2 的偶次幂   往左跨度为 2 的奇次幂
        if ( cowPosition > farmerPosition ) {
            // 依次探查往右的最大距离直到可以发现目标
            int step = 1;
            int power = 0;
            int currentPosition = farmerPosition + 1;
            while ( cowPosition > currentPosition ) {
                // 本次往右没有发现目标 则先回到原点 再往左走 再回到原点 准备下次往右
                step += pow(2, power) + 2 * pow(2, power + 1);
                power += 2;
                step += pow(2, power);
                currentPosition  = farmerPosition + (int)pow(2, power);
            }
            step -= (currentPosition - cowPosition);
            pw.println(step);
        } else {
            int step = 4;
            int power = 1;
            int currentPosition = farmerPosition - 2;
            while ( cowPosition < currentPosition ) {
                step += pow(2, power) + 2 * pow(2, power + 1);
                power += 2;
                step += pow(2, power);
                currentPosition  = farmerPosition - (int)pow(2, power);
            }
            step -= (cowPosition - currentPosition);
            pw.println(step);
        }
        pw.close();
    }
}
