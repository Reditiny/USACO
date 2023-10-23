import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Red
 * @version 1.0
 */
public class Lifeguards {
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("lifeguards.in"));
        PrintWriter pw = new PrintWriter("lifeguards.out");
        List<List<Integer>> intervals = new ArrayList<List<Integer>>();
        int n = Integer.parseInt(r.readLine());
        for (int i = 0; i < n; i++) {
            String[] s = r.readLine().split(" ");
            List<Integer> interval = new ArrayList<Integer>();
            interval.add(Integer.parseInt(s[0]));
            interval.add(Integer.parseInt(s[1]));
            intervals.add(interval);
        }
        // 按照起始时间排序
        intervals.sort((o1, o2) -> o1.get(0).compareTo(o2.get(0)));
        // 计算总覆盖时间 每一个缺失后所减少的时间 需要单独处理首尾两个区间
        int gap = Math.min(intervals.get(0).get(1), intervals.get(1).get(0)) - intervals.get(0).get(0);
        int cover = intervals.get(0).get(1) - intervals.get(0).get(0);
        int lastEnd = intervals.get(0).get(1);
        for (int i = 1; i < intervals.size() - 1; i++) {
            // 对于当前区间 计算其缺失后所减少的时间
            List<Integer> curInterval = intervals.get(i);
            int gapLastEnd = lastEnd;
            int curGap = 0;
            for (int j = i + 1; j < intervals.size(); j++) {
                List<Integer> nextInterval = intervals.get(j);
                curGap += Math.max(0, Math.min(curInterval.get(1), nextInterval.get(0)) - Math.max(gapLastEnd, curInterval.get(0)));
                gapLastEnd = Math.max(gapLastEnd, nextInterval.get(1));
                if (gapLastEnd > curInterval.get(1)) {
                    break;
                }
            }
            gap = Math.min(gap, curGap);
            // 计算总覆盖时间
            if (curInterval.get(1) > lastEnd) {
                cover += curInterval.get(1) - Math.max(curInterval.get(0), lastEnd);
                lastEnd = curInterval.get(1);
            }
        }
        if (intervals.get(intervals.size() - 1).get(1) > lastEnd) {
            cover += intervals.get(intervals.size() - 1).get(1) - Math.max(intervals.get(intervals.size() - 1).get(0), lastEnd);
            gap = Math.min(gap, intervals.get(intervals.size() - 1).get(1) - lastEnd);
        } else {
            gap = 0;
        }
        pw.println(cover - gap);
        pw.close();
    }
}
