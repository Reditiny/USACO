import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

/**
 * @author Red
 * @version 1.0
 */
public class RotateAndShift {
    static int N, K, T;
    static int[] initCows;
    static int[] cows;
    static int[] moves;
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(r.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        cows = new int[N];
        moves = new int[K];
        for (int i = 0; i < N; i++){
            cows[i] = i;
        }
        initCows = cows.clone();
        st = new StringTokenizer(r.readLine());
        for (int i = 0; i < K; i++){
            moves[i] = Integer.parseInt(st.nextToken());
        }
        int remain = findRemain();
        for (int i = 0; i < remain; i++){
            move();
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++){
            sb.append(cows[i]).append(" ");
        }
        pw.println(sb.toString().trim());
        pw.close();
    }
    /**
     * 寻找周期并计算剩余的移动次数
     * 每 N 次移动后，moves 的状态会回到初始状态
     * 当若干个 N 后，cows 的状态也回到初始状态时
     * 说明找到了周期，并返回剩余的移动次数 T % (cycle * N)
      */
    static int findRemain(){
        int cycle = 0;
        while(true){
            // 未能在 T 步内找到周期，返回剩余的移动次数
            if ((cycle + 1) * N > T){
                return T - cycle * N;
            }else{
                for (int i = 0; i < N; i++){
                    move();
                }
                cycle++;
                if(Arrays.equals(initCows, cows)){
                    return T % (cycle * N);
                }
            }
        }
    }
    /**
     * 做一次旋转和移动
      */
    static void move(){
        int tempCow = cows[moves[0]];
        for (int i = K - 1; i > 0; i--){
            int from = moves[i];
            int to = moves[(i+1)%K];
            cows[to] = cows[from];
        }
        cows[moves[1]] = tempCow;
        for (int i = 0; i < K; i++){
            moves[i] = (moves[i] + 1) % N;
        }
    }
}