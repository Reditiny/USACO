import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author Red
 * @version 1.0
 */
public class ContaminatedMilk {
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("badmilk.in"));
        PrintWriter pw = new PrintWriter("badmilk.out");
        StringTokenizer st = new StringTokenizer(r.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        // 记录喝牛奶的信息[人,奶,时间]
        List<List<Integer>> drinkInfo = new ArrayList<>();
        HashSet<Integer> targetMilk = new HashSet<>();
        for (int i = 0; i < d; i++) {
            st = new StringTokenizer(r.readLine());
            List<Integer> info = new ArrayList<>();
            info.add(Integer.parseInt(st.nextToken()));
            info.add(Integer.parseInt(st.nextToken()));
            info.add(Integer.parseInt(st.nextToken()));
            drinkInfo.add(info);
            targetMilk.add(info.get(1));
        }
        // 按人员编号排序 编号相同时按时间排序 为了满足后续双指针实现
        drinkInfo.sort((a, b) -> {
            if (a.get(0).equals(b.get(0))) {
                return a.get(2) - b.get(2);
            }
            return a.get(0) - b.get(0);
        });
        // 记录生病信息[人,时间]
        List<List<Integer>> sickInfo = new ArrayList<>();
        for (int i = 0; i < s; i++) {
            st = new StringTokenizer(r.readLine());
            List<Integer> info = new ArrayList<>();
            info.add(Integer.parseInt(st.nextToken()));
            info.add(Integer.parseInt(st.nextToken()));
            sickInfo.add(info);
        }
        // 按人员编号排序
        sickInfo.sort((a, b) -> a.get(0) - b.get(0));
        // 双指针遍历生病信息的同时 依次缩减坏牛奶的范围
        int j = 0;
        for (int i = 0; i < s; i++) {
            List<Integer> curSickInfo = sickInfo.get(i);
            int curSickPerson = curSickInfo.get(0);
            int curSickTime = curSickInfo.get(1);
            HashSet<Integer> candidateMilk = new HashSet<>();
            while (j < d && drinkInfo.get(j).get(0) < curSickPerson) {
                j++;
            }
            while (j < d && drinkInfo.get(j).get(0) == curSickPerson) {
                if (drinkInfo.get(j).get(2) < curSickTime) {
                    candidateMilk.add(drinkInfo.get(j).get(1));
                }
                j++;
            }
            targetMilk.retainAll(candidateMilk);
        }
        // 找到喝过坏牛奶的人
        HashSet<Integer> sickPerson = new HashSet<>();
        for (int i = 0; i < d; i++) {
            if (targetMilk.contains(drinkInfo.get(i).get(1))) {
                sickPerson.add(drinkInfo.get(i).get(0));
            }
        }
        pw.println(sickPerson.size());
        pw.close();
    }
}
