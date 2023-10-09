import java.io.*;
import java.util.*;

public class FieldReduction {
	static boolean sort1;

	static class Point implements Comparable<Point> {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public int compareTo (Point p) {
			if ((sort1 && this.x == p.x) || (!sort1 && this.y != p.y)) return Integer.compare(this.y, p.y);
			return Integer.compare(this.x, p.x);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("reduce.in"));
		PrintWriter out = new PrintWriter("reduce.out");
		int N = Integer.parseInt(reader.readLine());
		Point[] byX = new Point[N];
		StringTokenizer st;
		for (int n = 0; n < N; n++) {
 			st = new StringTokenizer(reader.readLine());
			byX[n] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		sort1 = true;
		Arrays.sort(byX);

		int[] remove = new int[4]; //Holds num to remove: {minXRem, maxXRem, minYRem, maxYRem}
		List<Point> withRem = new ArrayList<Point>();
		int ans = (int) 16e9;
		int length;
		int width;
		for (int i = 0; i < 4; i++) {
			remove[i]++;
			for (int j = 0; j < 4; j++) {
				remove[j]++;
				for (int k = 0; k < 4; k++) {
					remove[k]++;

					withRem.clear();
// 					Question: does the order matter of handling x first vs handling y first?
// 					Answer: No it does not.
// 					The only occasion where we need to care about the order, is when adding x first may also
// 					adding the biggest / smallest y, which could affect that the added y is not
// 					the global biggest / smallest y.
// 					But in fact, under this condition, if we add the y first, one of the biggest / smallest x
// 					will also be added. So either way it is the same.
					for (int a = remove[0]; a < N - remove[1]; a++) {
						withRem.add(byX[a]);
					}
					length = withRem.get(withRem.size() - 1).x - withRem.get(0).x;
					sort1 = false;
					Collections.sort(withRem);
					width = withRem.get(withRem.size() - 1 - remove[3]).y - withRem.get(remove[2]).y;
					ans = Math.min(length * width, ans);

					remove[k]--;
				}
				remove[j]--;
			}
			remove[i]--;
		}
		out.println(ans);
		out.close();
	}
}
