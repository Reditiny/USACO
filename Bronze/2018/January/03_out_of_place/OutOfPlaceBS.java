import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class OutOfPlaceBS {
    public static void main(String[] args) throws Exception {
        BufferedReader fin = new BufferedReader(new FileReader("outofplace.in"));
        PrintWriter fout = new PrintWriter("outofplace.out");

        int N = Integer.parseInt(fin.readLine());
        int[] places = new int[N];


        for (int i = 0; i < N; i++) {
            places[i] = Integer.parseInt(fin.readLine());
        }

        int bessie = 0;
        int direction = 0;

        if (places[0] > places[1]) {
            bessie = 0;
            direction = 1;
        } else if (places[N - 2] > places[N - 1]) {
            bessie = N - 1;
            direction = -1;
        } else {
            for (int i = 1; i < N; i++) {
                if (places[i - 1] > places[i]) {
                    if (places[i] < places[i - 2]) {
                        bessie = i;
                        direction = -1;
                    } else if (places[i - 1] > places[i + 1]) {
                        bessie = i - 1;
                        direction = 1;
                    }
                }
            }
        }

        int swaps;
        Set<Integer> uniqueElements = new HashSet<>();

        if (direction > 0) {
            int new_location = binarySearch(places, places[bessie], direction, bessie + 1, N - 1);
            for (int i = bessie + 1 ; i <= new_location; i++) {
                uniqueElements.add(places[i]);
            }
            swaps = uniqueElements.size();
        } else {
            int new_location = binarySearch(places, places[bessie], direction, 0, bessie - 1);
            for (int i = new_location; i < bessie; i++) {
                uniqueElements.add(places[i]);
            }
            swaps = uniqueElements.size();
        }

        fout.println(swaps);
        fout.close();
    }

    private static int binarySearch(int[] targetList, int value, int direction, int left, int right) {
        while (left + 1 < right) {
            int mid = (left + right) / 2;
            if (targetList[mid] == value) {
                if (direction > 0) {
                    right = mid;
                } else {
                    left = mid;
                }
            } else if (targetList[mid] < value) {
                left = mid;
            } else {
                right = mid;
            }
        }

        if (direction < 0) {
            if (targetList[right] <= value) {
                return right + 1;
            } else if (targetList[left] <= value) {
                return right;
            }
            return left;
        }

        if (targetList[left] >= value) {
            return left - 1;
        }

        if (targetList[right] >= value) {
            return left;
        }

        return right;
    }
}
