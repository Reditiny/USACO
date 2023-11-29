import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.*;

/**
 * @author Red
 * @version 1.0
 */
public class Meetings {
    static int N, L;
    static Cow[] cows;
    static int totalWeight;

    public static void main(String[] args) throws Exception {
        BufferedReader read = new BufferedReader(new FileReader("meetings.in"));
        PrintWriter pw = new PrintWriter("meetings.out");
        StringTokenizer st = new StringTokenizer(read.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        cows = new Cow[N];
        totalWeight = 0;
        for (int c = 0; c < N; c++) {
            StringTokenizer cow = new StringTokenizer(read.readLine());
            int w = Integer.parseInt(cow.nextToken());
            int x = Integer.parseInt(cow.nextToken());
            int d = Integer.parseInt(cow.nextToken());
            cows[c] = new Cow(w, x, d);
            totalWeight += w;
        }
        // 按位置排序
        Arrays.sort(cows, Comparator.comparingInt(c -> c.pos));
        // 记录向左和向右的奶牛
        List<Cow> left = new ArrayList<>();
        List<Cow> right = new ArrayList<>();
        for (Cow c : cows) {
            if (c.speed == -1) {
                left.add(c);
            } else if (c.speed == 1) {
                right.add(c);
            }
        }
        // 奶牛反弹等价于奶牛交换重量并继续前进，因此奶牛到达谷仓的时间就是离谷仓距离
        // 记录奶牛到达谷仓的时间和重量，重量的相对位置不会改变
        // 例如  1    2    3    4
        //      ->   ->   <-   <-    2和3碰撞
        //      1    2    3    4
        //      ->   <-   ->   <-    1和2碰撞  3和4碰撞
        //      1    2    3    4
        //      <-   ->   <-  ->     2和3碰撞
        //      1    2    3    4
        //      <-   <-   ->   ->
        // 由此知前面的奶牛最终都是向左的，后面的奶牛最终都是向右的
        List<int[]> weightTimes = new ArrayList<>();
        for (int c = 0; c < left.size(); c++) {
            // time of arrivial at barn & weight, respectively
            weightTimes.add(new int[] {left.get(c).pos, cows[c].weight});
        }
        for (int c = 0; c < right.size(); c++) {
            weightTimes.add(new int[] {L - right.get(c).pos,
                    cows[left.size() + c].weight});
        }
        // 按到达时间排序
        weightTimes.sort(Comparator.comparingInt(t -> t[0]));
        // 计算有一般半重量的奶牛到达谷仓的时间
        int endTime = -1;
        for (int[] barnMeeting : weightTimes) {
            totalWeight -= 2 * barnMeeting[1];
            if (totalWeight <= 0) {
                endTime = barnMeeting[0];
                break;
            }
        }
        // 计算碰撞次数 序遍历所有奶牛，为每头向左走的奶牛查看 endTime 范围内有多少只向右走的奶牛
        int meetingNum = 0;
        Queue<Integer> leftSide = new ArrayDeque<>();
        for (int c = 0; c < N; c++) {
            if (cows[c].speed == 1) {
                leftSide.add(cows[c].pos);
            } else if (cows[c].speed == -1) {
                while (!leftSide.isEmpty() && leftSide.peek() + 2 * endTime < cows[c].pos) {
                    leftSide.poll();
                }
                meetingNum += leftSide.size();
            }
        }
        pw.println(meetingNum);
        pw.close();
    }

    static class Cow {
        public int weight;
        public int pos;
        public int speed;
        public Cow(int weight, int pos, int speed) {
            this.weight = weight;
            this.pos = pos;
            this.speed = speed;
        }
    }
}