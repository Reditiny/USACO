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
public class FieldReduction {
    static int n;
    static List<List<Integer>> cowPositions = new ArrayList<>();
    static HashSet<List<Integer>> candidates = new HashSet<>();

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
        // 四个方向找出至多 16 个候选点 使用 HashSet 去重
        cowPositions.sort((a, b) -> a.get(0) - b.get(0));
        for (int i = 0; i < 4; i++) {
            candidates.add(cowPositions.get(i));
            candidates.add(cowPositions.get(cowPositions.size() - i - 1));
        }
        cowPositions.sort((a, b) -> a.get(1) - b.get(1));
        for (int i = 0; i < 4; i++) {
            candidates.add(cowPositions.get(i));
            candidates.add(cowPositions.get(cowPositions.size() - i - 1));
        }
        // 将 HashSet 转换为 List 便于遍历
        List<List<Integer>> candidatesList = new ArrayList<>(candidates);
        // 从候选点中去除三个点计算最小面积 虽然三重循环但是candidateList.size()最大为16 因此复杂度为 O(1)
        long ansArea = 40000 * 40000;
        for (int i = 0; i < candidatesList.size(); i++) {
            for (int j = i + 1; j < candidatesList.size(); j++) {
                for (int k = j + 1; k < candidatesList.size(); k++) {
                    long[] minAndMax = findMinAndMaxWithAbsent(candidatesList, i, j, k);
                    long area = (minAndMax[1] - minAndMax[0]) * (minAndMax[3] - minAndMax[2]);
                    ansArea = Math.min(ansArea, area);
                }
            }
        }
        pw.println(ansArea);
        pw.close();
    }

    /**
     * 找出除了三个点之外的最小和最大值
     */
    public static long[] findMinAndMaxWithAbsent(List<List<Integer>> a, int index1, int index2, int index3) {
        int minX = Integer.MAX_VALUE;
        int maxX = 0;
        int minY = Integer.MAX_VALUE;
        int maxY = 0;
        for (int i = 0; i < a.size(); i++) {
            if (i == index1 || i == index2 || i == index3) {
                continue;
            }
            minX = Math.min(minX, a.get(i).get(0));
            maxX = Math.max(maxX, a.get(i).get(0));
            minY = Math.min(minY, a.get(i).get(1));
            maxY = Math.max(maxY, a.get(i).get(1));
        }
        return new long[]{minX, maxX, minY, maxY};
    }
}
