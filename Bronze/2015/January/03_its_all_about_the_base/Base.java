import java.io.*;
import java.util.StringTokenizer;

//O(Nlog(N))
public class Base {
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new FileReader("whatbase.in"));
		PrintWriter pw = new PrintWriter("whatbase.out");

		StringTokenizer st = new StringTokenizer(r.readLine());
		int k = Integer.parseInt(st.nextToken());
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(r.readLine());
			int firstNum = Integer.parseInt(st.nextToken());
			int secondNum = Integer.parseInt(st.nextToken());
			int smallerBase = Math.max(firstNum, secondNum);
			int greaterBase = Math.min(firstNum, secondNum);
			boolean found = false;
			for (int j = 10; j <= 15000; j++) {
				int otherBase = findOtherBase(firstNum, j, secondNum);
				if (otherBase != -1) {
					pw.println(j + " " + otherBase);
					break;
				}
			}

		}

		pw.close();
	}

	public static int findOtherBase(int firstNum, int base, int secondNum) {
		int first = 10;
		int last = 15000;
		int baseTen = xToTen(firstNum, base);
		while (first <= last) {
			int mid = (first + last)/2;
			int otherBaseTen = xToTen(secondNum, mid);
			if (baseTen == otherBaseTen) {
				return mid;
			} else {
				if (otherBaseTen > baseTen) {
					last = mid - 1;
				} else {
					first = mid + 1;
				}
			}

		}
		return -1;
	}

	public static int xToTen(int num, int base) {
		int hundreds = num/100;
		int tens = (num % 100)/10;
		int ones = num % 10;
		return base * base * hundreds + base * tens + ones;
	}

	public static int tenToX(int num, int base) {
		int hundreds = num/(base * base);
		int tens = (num % 100)/base;
		int ones = num - hundreds * base * base - tens * base;
		return hundreds * 100 + tens * 10 + ones;
	}
}
