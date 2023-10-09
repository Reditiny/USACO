import java.util.*;
import java.io.*;

public class FieldReduction {
	public static void main(String[] args) throws FileNotFoundException{
		File f = new File("reduce.in");
		Scanner sc = new Scanner(f);
		PrintWriter pw = new PrintWriter("reduce.out");

		int cows = sc.nextInt();
		int[][] coordinatesXIncrease = new int[cows][2];
		int[][] coordinatesYIncrease = new int[cows][3];
		int[] largeX = new int[4];
		int[] smallX = new int[4];
		int[][] largeY = new int[4][2];
		int[][] smallY = new int[4][2];
		int area = Integer.MAX_VALUE;

		for(int i = 0; i < cows; i++) {
			for(int j = 0; j < 2; j++) {
				coordinatesXIncrease[i][j] = sc.nextInt();
			}
		}
		Arrays.sort(coordinatesXIncrease, (a, b) -> Double.compare(a[0], b[0]));
		smallX[0] = coordinatesXIncrease[0][0];
		smallX[1] = coordinatesXIncrease[1][0];
		smallX[2] = coordinatesXIncrease[2][0];
		smallX[3] = coordinatesXIncrease[3][0];
		largeX[0] = coordinatesXIncrease[cows - 1][0];
		largeX[1] = coordinatesXIncrease[cows - 2][0];
		largeX[2] = coordinatesXIncrease[cows - 3][0];
		largeX[3] = coordinatesXIncrease[cows - 4][0];

		for(int i = 0; i < cows; i++) {
			for(int j = 0; j < 2; j++) {
				coordinatesYIncrease[i][j] = coordinatesXIncrease[i][j];
			}
			coordinatesYIncrease[i][2] = i;
		}
		Arrays.sort(coordinatesYIncrease, (a, b) -> Double.compare(a[1], b[1]));
		smallY[0][0] = coordinatesYIncrease[0][1];
		smallY[1][0] = coordinatesYIncrease[1][1];
		smallY[2][0] = coordinatesYIncrease[2][1];
		smallY[3][0] = coordinatesYIncrease[3][1];
		largeY[0][0] = coordinatesYIncrease[cows - 1][1];
		largeY[1][0] = coordinatesYIncrease[cows - 2][1];
		largeY[2][0] = coordinatesYIncrease[cows - 3][1];
		largeY[3][0] = coordinatesYIncrease[cows - 4][1];

		smallY[0][1] = coordinatesYIncrease[0][2];
		smallY[1][1] = coordinatesYIncrease[1][2];
		smallY[2][1] = coordinatesYIncrease[2][2];
		smallY[3][1] = coordinatesYIncrease[3][2];
		largeY[0][1] = coordinatesYIncrease[cows - 1][2];
		largeY[1][1] = coordinatesYIncrease[cows - 2][2];
		largeY[2][1] = coordinatesYIncrease[cows - 3][2];
		largeY[3][1] = coordinatesYIncrease[cows - 4][2];

		for(int sx = 0; sx < 4; sx++) {
			for(int lx = 0; lx < 4; lx++) {
				for(int sy = 0; sy < 4; sy++) {
					for(int ly = 0; ly < 4; ly++) {
						boolean[] out = new boolean[cows];

						for(int i = 0; i < sx; i++) {
							out[i] = true;
						}
						for(int i = 0; i < lx; i++) {
							out[cows - i - 1] = true;
						}
						for(int i = 0; i < sy; i++) {
							out[smallY[i][1]] = true;
						}
						for(int i = 0; i < ly; i++) {
							out[largeY[i][1]] = true;
						}

						int sxCor = smallX[sx];
						int lxCor = largeX[lx];
						int syCor = smallY[sy][0];
						int lyCor = largeY[ly][0];

						int counter = 0;
						for(int i = 0; i < cows; i++) {
							if(out[i] == true) counter++;
						}
						if(counter > 3) continue;

						int width = lxCor - sxCor;
						int length = lyCor - syCor;
						area = Math.min(area, width*length);
					}
				}
			}
		}
		pw.println(area);
		pw.close();
	}
}