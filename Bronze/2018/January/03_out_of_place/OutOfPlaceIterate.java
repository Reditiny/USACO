import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class OutOfPlaceIterate {
    public static void main(String[] args) throws IOException {
        BufferedReader fin = new BufferedReader(new FileReader("outofplace.in"));
        FileWriter fout = new FileWriter("outofplace.out");

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

        int count = 0;
        if (direction > 0) {
            int i = bessie + 1;
            while (i < N) {
                if (places[bessie] <= places[i]) {
                    break;
                }
                // multiple cows have height places[i], skip them
                int current_i = i;
                while (i < N && places[i] == places[current_i]) {
                    i++;
                }
                count++;
            }
        } else {
            int i = bessie - 1;
            while (i >= 0) {
                if (places[bessie] >= places[i]) {
                    break;
                }
                int current_i = i;
                while (i >= 0 && places[i] == places[current_i]) {
                    i--;
                }
                count++;
            }
        }

        fout.write(count + "\n");
        fout.close();
        fin.close();
    }
}
