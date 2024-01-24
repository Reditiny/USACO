import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.*;

/**
 * @author Red
 * @version 1.0
 */
public class FamilyTreeI {
    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("family.in"));
        PrintWriter pw = new PrintWriter("family.out");
        StringTokenizer st = new StringTokenizer(r.readLine());
        N = Integer.parseInt(st.nextToken());
        String cow1 = st.nextToken();
        String cow2 = st.nextToken();
        List<String[]> relations = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            relations.add(r.readLine().trim().split(" "));
        }

        String node = cow1;
        int da = 0;
        while (node != null) {
            if (getAncestorDistance(cow2, node, relations) != -1) {
                break;
            }
            node = rdMother(node, relations);
            da++;
        }

        // cannot rd a common ancestor between a and b
        if (node == null) {
            pw.write("NOT RELATED");
            pw.close();
            System.exit(0);
        }

        int db = getAncestorDistance(cow2, node, relations);
        if (da > 1 && db > 1) {
            pw.write("COUSINS");
            pw.close();
            System.exit(0);
        }
        if (da == 1 && db == 1) {
            pw.write("SIBLINGS");
            pw.close();
            System.exit(0);
        }

        // rd a node with higher generation
        if (da > db) {
            int temp = da;
            da = db;
            db = temp;
            String tempName = cow1;
            cow1 = cow2;
            cow2 = tempName;
        }

        StringBuilder prefix = new StringBuilder("great-".repeat(Math.max(0, db - 2)));
        if (db > 1 && da == 0) {
            prefix.append("grand-");
        }
        if (da == 0) {
            prefix.append("mother");
        } else {
            prefix.append("aunt");
        }

        pw.write(cow1 + " is the " + prefix + " of " + cow2);
        pw.close();
    }

    static String rdMother(String cow, List<String[]> relations) {
        for (String[] r : relations) {
            if (r[1].equals(cow)) {
                return r[0];
            }
        }
        return null;
    }

    static int getAncestorDistance(String sourceCow, String targetCow, List<String[]> relations) {
        int distance = 0;
        String node = sourceCow;
        while (node != null) {
            if (node.equals(targetCow)) {
                return distance;
            }
            node = rdMother(node, relations);
            distance++;
        }
        return -1;
    }
}
