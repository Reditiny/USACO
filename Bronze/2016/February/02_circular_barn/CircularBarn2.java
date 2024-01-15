import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;

/**
 * @author Red
 * @version 1.0
 */
public class CircularBarn2 {
    static int N;
    static int[] rooms;
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("cbarn.in"));
        PrintWriter pw = new PrintWriter("cbarn.out");
        N = Integer.parseInt(r.readLine());
        // 记录每个房间牛的数量
        rooms = new int[N];

        for (int i = 0; i < N; i++) {
            rooms[i] = Integer.parseInt(r.readLine());
        }

        int result = Integer.MAX_VALUE;
        // 以每个房间为起点，计算总距离
        for (int i = 0; i < N; i++) {
            int acc = 0;
            for (int j = 0; j < N; j++) {
                acc += rooms[(i + j) % N] * j;
            }
            result = Math.min(result, acc);
        }

        pw.println(result);
        pw.close();
    }
}

