#include <iostream>
#include <fstream>
#include <vector>
#include <unordered_map>
#include <algorithm>

using namespace std;

class TreeNode {
public:
    string name;
    TreeNode* mother;
    vector<TreeNode*> children;

    TreeNode(string name) : name(name), mother(nullptr) {}
};

int getAncestorDistance(TreeNode* sourceCow, TreeNode* targetCow) {
    int distance = 0;
    TreeNode* node = sourceCow;
    while (node != nullptr) {
        if (node == targetCow) {
            return distance;
        }
        node = node->mother;
        distance++;
    }
    return -1;
}

int main() {
    ifstream fin("family.in");
    ofstream fout("family.out");

    int N;
    fin >> N;
    string name_a, name_b;
    fin >> name_a >> name_b;

    unordered_map<string, TreeNode*> graph;
    for (int i = 0; i < N; ++i) {
        string name_i, name_j;
        fin >> name_i >> name_j;

        if (graph.find(name_i) == graph.end()) {
            graph[name_i] = new TreeNode(name_i);
        }
        if (graph.find(name_j) == graph.end()) {
            graph[name_j] = new TreeNode(name_j);
        }

        TreeNode* node_i = graph[name_i];
        TreeNode* node_j = graph[name_j];

        node_i->children.push_back(node_j);
        node_j->mother = node_i;
    }

    TreeNode* node = graph[name_a];
    int da = 0;
    while (node != nullptr) {
        if (getAncestorDistance(graph[name_b], node) != -1) {
            break;
        }
        node = node->mother;
        da++;
    }

    // cannot find a common ancestor between a and b
    if (node == nullptr) {
        fout << "NOT RELATED";
        return 0;
    }

    int db = getAncestorDistance(graph[name_b], node);
    if (da > 1 && db > 1) {
        fout << "COUSINS";
        return 0;
    }
    if (da == 1 && db == 1) {
        fout << "SIBLINGS";
        return 0;
    }

    // find a node with higher generation
    if (da > db) {
        swap(da, db);
        swap(name_a, name_b);
    }

    string prefix = "";
    for (int i = 0; i < max(0, db - 2); ++i) {
        prefix += "great-";
    }
    if (db > 1 && da == 0) {
        prefix += "grand-";
    }
    if (da == 0) {
        prefix += "mother";
    } else {
        prefix += "aunt";
    }

    fout << name_a << " is the " << prefix << " of " << name_b;
    return 0;
}
