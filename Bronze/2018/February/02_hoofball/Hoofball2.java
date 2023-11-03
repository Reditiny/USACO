import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class Hoofball2 {
    static int N;
    static List<Integer> cowLocation = new ArrayList<Integer>();

    static List<Integer> passDistance = new ArrayList<Integer>();

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("hoofball.in"));
        PrintWriter pw = new PrintWriter("hoofball.out");
        N = Integer.parseInt(r.readLine());
        StringTokenizer st = new StringTokenizer(r.readLine());
        for (int i = 0; i < N; i++) {
            cowLocation.add(Integer.parseInt(st.nextToken()));
        }
        // 按位置升序排序 并找到每头牛传球的距离
        cowLocation.sort(Integer::compareTo);
        passDistance.add(cowLocation.get(1) - cowLocation.get(0));
        for (int i = 1; i < N - 1; i++) {
            passDistance.add(Math.min(cowLocation.get(i) - cowLocation.get(i - 1), cowLocation.get(i + 1) - cowLocation.get(i)));
        }
        passDistance.add(cowLocation.get(N - 1) - cowLocation.get(N - 2));
        int ans = 0;
        while (true) {
            // 找到最后一头当前传球距离最远的牛，这头牛作为起点，因为它的传球距离最远，它不会接到其他牛的传球
            int maxDistance = Collections.max(passDistance);
            if (maxDistance == -1) {
                break;
            }
            // 为什么是 lastIndexOf 而不是 indexOf
            // 对于相同的距离，右边的牛总会传给左边的牛，所以起始点应该是最右边的牛
            int maxDistanceIndex = passDistance.lastIndexOf(maxDistance);
            ans += 1;
            // 传球直到球不再传给新牛
            while (true) {
                int nextIndex = 0;
                if (passDistance.get(maxDistanceIndex) == -1) {
                    break;
                }
                if (maxDistanceIndex == 0) {
                    nextIndex = maxDistanceIndex + 1;
                } else if (maxDistanceIndex == N - 1) {
                    nextIndex = maxDistanceIndex - 1;
                } else {
                    if (cowLocation.get(maxDistanceIndex) - cowLocation.get(maxDistanceIndex - 1) <= cowLocation.get(maxDistanceIndex + 1) - cowLocation.get(maxDistanceIndex)) {
                        nextIndex = maxDistanceIndex - 1;
                    } else {
                        nextIndex = maxDistanceIndex + 1;
                    }
                }
                passDistance.set(maxDistanceIndex, -1);
                maxDistanceIndex = nextIndex;
            }
        }
        pw.println(ans);
        pw.close();
    }
}
