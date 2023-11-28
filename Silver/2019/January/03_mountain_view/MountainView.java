import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class MountainView {
    static int N;
    // 存储山脚的坐标
    static List<List<Long>> mountains = new ArrayList<List<Long>>();
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("mountains.in"));
        PrintWriter pw = new PrintWriter("mountains.out");
        N = Integer.parseInt(r.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(r.readLine());
            List<Long> mountain = new ArrayList<Long>();
            long x = Long.parseLong(st.nextToken());
            long y = Long.parseLong(st.nextToken());
            mountain.add(x - y);
            mountain.add(x + y);
            mountains.add(mountain);
        }
        // 按照左边山脚的坐标升序排序，如果左边山脚坐标相同，按照右边山脚坐标降序排序
        mountains.sort((o1, o2) -> {
            if (o1.get(0).equals(o2.get(0))) {
                return o2.get(1).compareTo(o1.get(1));
            }
            return o1.get(0).compareTo(o2.get(0));
        });
        // 计算能看到的山峰数量
        // 所有山的坡度都是 45 度，如果 A 山遮住了 B 山，那么 B 山的山脚一定在 A 山的山脚范围内
        // 因为按左边山脚排序，所以只需要记录当前最右边的山脚坐标即可
        long rightFoot = mountains.get(0).get(1);
        int ans = 1;
        for (int i = 1; i < N; i++) {
            if (mountains.get(i).get(1) > rightFoot) {
                ans++;
                rightFoot = mountains.get(i).get(1);
            }
        }
        pw.println(ans);
        pw.close();
    }
}