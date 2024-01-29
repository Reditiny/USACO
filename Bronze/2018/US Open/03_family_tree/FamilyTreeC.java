import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.*;

/**
 * @author Red
 * @version 1.0
 */
public class FamilyTreeC {
    static int N;
    static Map<String, TreeNode> graph = new HashMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("family.in"));
        PrintWriter pw = new PrintWriter("family.out");
        StringTokenizer st = new StringTokenizer(r.readLine());
        N = Integer.parseInt(st.nextToken());
        String name_a = st.nextToken();
        String name_b = st.nextToken();
        for (int i = 0; i < N; i++) {
            String[] names = r.readLine().trim().split(" ");
            String name_i = names[0];
            String name_j = names[1];

            if (!graph.containsKey(name_i)) {
                graph.put(name_i, new TreeNode(name_i));
            }
            if (!graph.containsKey(name_j)) {
                graph.put(name_j, new TreeNode(name_j));
            }

            TreeNode node_i = graph.get(name_i);
            TreeNode node_j = graph.get(name_j);

            node_i.children.add(node_j);
            node_j.mother = node_i;
        }

        TreeNode node = graph.get(name_a);
        int da = 0;
        while (node != null) {
            if (getAncestorDistance(graph.get(name_b), node) != -1) {
                break;
            }
            node = node.mother;
            da++;
        }

        // cannot rd a common ancestor between a and b
        if (node == null) {
            pw.write("NOT RELATED");
            pw.close();
            System.exit(0);
        }

        int db = getAncestorDistance(graph.get(name_b), node);
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
            String tempName = name_a;
            name_a = name_b;
            name_b = tempName;
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

        pw.write(name_a + " is the " + prefix + " of " + name_b);
        pw.close();
    }

    /**
     * 返回 sourceCow 到 targetCow 的辈份距离
     */
    static int getAncestorDistance(TreeNode sourceCow, TreeNode targetCow) {
        int distance = 0;
        TreeNode node = sourceCow;
        while (node != null) {
            if (node == targetCow) {
                return distance;
            }
            node = node.mother;
            distance++;
        }
        return -1;
    }


}

class TreeNode {
    String name;
    TreeNode mother;
    List<TreeNode> children = new ArrayList<>();
    TreeNode(String name) {
        this.name = name;
    }
}
