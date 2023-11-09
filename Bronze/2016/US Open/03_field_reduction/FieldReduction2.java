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
public class FieldReduction2 {
    static int n;
    static List<List<Integer>> cowPositions = new ArrayList<>();

    static List<List<Integer>> cowsSortByX;
    static List<List<Integer>> cowsSortByY;

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("reduce.in"));
        PrintWriter pw = new PrintWriter("reduce.out");
        n = Integer.parseInt(r.readLine());
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(r.readLine());
            List<Integer> cow = new ArrayList<>();
            cow.add(Integer.parseInt(st.nextToken()));
            cow.add(Integer.parseInt(st.nextToken()));
            cowPositions.add(cow);
        }
        cowPositions.sort((a, b) -> a.get(0) - b.get(0));
        cowsSortByX = new ArrayList<>(cowPositions);
        cowPositions.sort((a, b) -> a.get(1) - b.get(1));
        cowsSortByY = new ArrayList<>(cowPositions);
        int ansArea = 40000 * 40000;
        for (int numberOfMinx = 0; numberOfMinx < 4; numberOfMinx++) {
            for (int numberOfMaxx = 0; numberOfMaxx < 4; numberOfMaxx++) {
                for (int numberOfMiny = 0; numberOfMiny < 4; numberOfMiny++) {
                    for (int numberOfMaxy = 0; numberOfMaxy < 4; numberOfMaxy++) {
                        if (numberOfMinx + numberOfMaxx + numberOfMiny + numberOfMaxy != 3) {
                            continue;
                        }
                        // 因为按 x 排序和按 y 排序移除牛的时候可能会有牛重复被移除，所以用 HashSet 去重
                        HashSet<List<Integer>> pointsRemoved = new HashSet<>();
                        // 按 x 排序移除若干头牛直接移除前 numberOfMinx 和后 numberOfMaxx
                        for (int i = 0; i < numberOfMinx; i++) {
                            pointsRemoved.add(cowsSortByX.get(i));
                        }
                        for (int i = 0; i < numberOfMaxx; i++) {
                            pointsRemoved.add(cowsSortByX.get(n - 1 - i));
                        }
                        // 按 y 排序确定要移除的剩下的若干头牛
                        // 如果牛已经在 x 排序中被移除了则跳过
                        int yStart = 0, count = 0;
                        while(count < numberOfMiny){
                            if(!pointsRemoved.contains(cowsSortByY.get(yStart))) {
                                pointsRemoved.add(cowsSortByY.get(yStart));
                                count++;
                            }
                            yStart++;
                        }
                        int yEnd = n - 1;
                        count = 0;
                        while(count < numberOfMaxy){
                            if(!pointsRemoved.contains(cowsSortByY.get(yEnd))) {
                                pointsRemoved.add(cowsSortByY.get(yEnd));
                                count++;
                            }
                            yEnd--;
                        }
                        // 当 x 和 y 的移除都完成后 numberOfMinx 位置上的牛不一定还在因为它可能在 y 排序中被移除了
                        // 同理 yStart 位置上的牛也不一定还在因为它可能在 x 排序中被移除了
                        // 所以要从这些位置开始查看牛是否还在
                        int xMin = numberOfMinx, xMax = n - 1 - numberOfMaxx;
                        int yMin = yStart, yMax = yEnd;
                        while(pointsRemoved.contains(cowsSortByX.get(xMin))) {
                            xMin++;
                        }
                        while(pointsRemoved.contains(cowsSortByX.get(xMax))) {
                            xMax--;
                        }
                        while(pointsRemoved.contains(cowsSortByY.get(yMin))) {
                            yMin++;
                        }
                        while(pointsRemoved.contains(cowsSortByY.get(yMax))) {
                            yMax--;
                        }
                        int distanceY = cowsSortByY.get(yMax).get(1) - cowsSortByY.get(yMin).get(1);
                        int distanceX = cowsSortByX.get(xMax).get(0) - cowsSortByX.get(xMin).get(0);
                        int area = distanceX * distanceY;
                        if (area < ansArea) {
                            ansArea = area;
                        }
                    }
                }
            }
        }
        pw.println(ansArea);
        pw.close();
    }


}
