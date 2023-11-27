import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class Leaders {
    static int N;
    static List<Character> breeds;
    static int[] E;
    static int firstG, lastG, firstH, lastH;
    static int ans = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        N = Integer.parseInt(r.readLine());
        breeds = new ArrayList<>();
        E = new int[N];
        for (char c : r.readLine().toCharArray()) {
            breeds.add(c);
        }
        firstG = breeds.indexOf('G');
        lastG = breeds.lastIndexOf('G');
        firstH = breeds.indexOf('H');
        lastH = breeds.lastIndexOf('H');
        StringTokenizer st = new StringTokenizer(r.readLine());
        for (int i = 0; i < N; i++) {
            E[i] = Integer.parseInt(st.nextToken()) - 1;
        }
        // 因为每头牛的名单都从自己开始，所有只有可能是每个品种的第一头牛的名单包括了所有的同种牛
        // 当第一头 H 在第一头 G 之前时，如 HGHGH
        // 不妨设第一头 G 是 leader，则其名单包括了所有的 G 或 其名单包括了 H leader
        // 第二种情况不可能，因为这样的话 H leader 即不能包含所有的 H 也不能包含 G leader
        // 因此第一头 G 是 leader 只有可能是其名单包括了所有的 G
        // 如果第一头 G 不是 leader，那么后面的 G 也不可能是 leader
        if (firstG > firstH) {
            // 判断第一头 G 是否是 leader
            if (E[firstG] >= lastG) {
                // 找到 H leader
                for (int i = 0; i < firstG; i++) {
                    if (E[i] >= firstG || (i == firstH && E[firstH] >= lastH)) {
                        ans++;
                    }
                }
            }
        }else{
            if (E[firstH] >= lastH) {
                for (int i = 0; i < firstH; i++) {
                    if (E[i] >= firstH || (i == firstG && E[firstG] >= lastG)) {
                        ans++;
                    }
                }
            }
        }
        pw.println(ans);
        pw.close();
    }
}