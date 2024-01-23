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
public class FieldReductionBronzeSort {
    public static void main(String[] args) throws Exception {
        BufferedReader fin = new BufferedReader(new FileReader("reduce.in"));
        PrintWriter fout = new PrintWriter("reduce.out");

        int N = Integer.parseInt(fin.readLine());

        List<List<Integer>> cowsByX = new ArrayList<>();

        for (int i = 0; i < N; ++i) {
            String[] input = fin.readLine().split(" ");
            int x = Integer.parseInt(input[0]);
            int y = Integer.parseInt(input[1]);
            cowsByX.add(List.of(x, y));
        }

        Collections.sort(cowsByX, (cow1, cow2) -> cow1.get(0) - cow2.get(0));
        List<List<Integer>> cowsByY = new ArrayList<>(cowsByX);
        Collections.sort(cowsByY, (cow1, cow2) -> cow1.get(1) - cow2.get(1));

        List<List<Integer>> pointsToDrop = new ArrayList<>(List.of(cowsByX.get(0), cowsByX.get(N - 1), cowsByY.get(0), cowsByY.get(N - 1)));
        int answer = 40000 * 40000;

        for (List<Integer> point : pointsToDrop) {
            int minX = 40000;
            int maxX = 0;
            int minY = 40000;
            int maxY = 0;

            for (List<Integer> cow : cowsByX) {
                if (cow.equals(point)) {
                    continue;
                }
                minX = Math.min(minX, cow.get(0));
                maxX = Math.max(maxX, cow.get(0));
                minY = Math.min(minY, cow.get(1));
                maxY = Math.max(maxY, cow.get(1));
            }

            answer = Math.min(answer, (maxX - minX) * (maxY - minY));
        }

        fout.println(answer);
        fout.close();
    }
}