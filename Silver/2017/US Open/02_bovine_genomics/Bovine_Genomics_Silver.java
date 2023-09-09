import java.io.BufferedWriter;
import java.util.HashMap;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;

public class Bovine_Genomics_Silver {
	public static void main(String[] args) throws IOException {
		/* Slowest time:1886 ms
		 * Basic function: We put our genome's into an 2d array, then just test every triplet,
		 * increments a counter an print.
		 */
		Scanner sc1 = new Scanner(new File("./cownomics.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("./cownomics.out")));

		int cows = sc1.nextInt();
		int length = sc1.nextInt();

		char[][] spotty = new char[cows][length];
		char[][] plain = new char[cows][length];
		String genomics = "";

		for(int x = 0; x < cows; x++) {
			sc1.nextLine();
			genomics = sc1.next();
			for(int y = 0; y < length; y ++) {
				spotty[x][y] = genomics.charAt(y);
			}
		}

		for(int x = 0; x < cows; x++) {
			sc1.nextLine();
			genomics = sc1.next();
			for(int y = 0; y < length; y ++) {
				plain[x][y] = genomics.charAt(y);
			}
		}

		HashMap<String,Integer> plains = new HashMap<>();
		ArrayList<String> spottys = new ArrayList<>();
		String plainss = "";
		String spottyss = "";
		int count = 0;

		for(int x = 0; x < length; x++) {
			for(int y = x + 1; y < length; y++) {
				for(int z = y + 1; z < length; z++) {
					plains.clear();
					spottys.clear();
					for(int a = 0; a < cows; a++) {
						plainss = Character.toString(plain[a][x]) + Character.toString(plain[a][y]) + Character.toString(plain[a][z]);
						spottyss = Character.toString(spotty[a][x]) + Character.toString(spotty[a][y]) + Character.toString(spotty[a][z]);
						plains.put(plainss, 1);
						spottys.add(spottyss);
					}
					if(Nocontains(spottys,plains)) {
						count ++;
					}
				}
			}
		}

		pw.print(count);
		pw.close();
	}
	public static boolean Nocontains(ArrayList<String> spotty, HashMap<String,Integer> plains) {

		for(String x:spotty) {
			if(plains.containsKey(x)) {
				return false;
			}
		}
		return true;
	}
}