#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>

using namespace std;

string findMother(string cow, const vector<vector<string>>& relations) {
    for (const auto& r : relations) {
        if (r[1] == cow) {
            return r[0];
        }
    }
    return "";
}

int getAncestorDistance(const string& sourceCow, const string& targetCow, const vector<vector<string>>& relations) {
    int distance = 0;
    string node = sourceCow;
    while (!node.empty()) {
        if (node == targetCow) {
            return distance;
        }
        node = findMother(node, relations);
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

    vector<vector<string>> relations(N, vector<string>(2));
    for (int i = 0; i < N; ++i) {
        fin >> relations[i][0] >> relations[i][1];
    }

    string node = name_a;
    int da = 0;
    while (!node.empty()) {
        if (getAncestorDistance(name_b, node, relations) != -1) {
            break;
        }
        node = findMother(node, relations);
        da++;
    }

    // cannot find a common ancestor between a and b
    if (node.empty()) {
        fout << "NOT RELATED";
        return 0;
    }

    int db = getAncestorDistance(name_b, node, relations);
    if (da > 1 && db > 1) {
        fout << "COUSINS";
        return 0;
    }
    if (da == 1 && db == 1) {
        fout << "SIBLINGS";
        return 0;
    }

    // find a node with a higher generation
    if (da > db) {
        swap(da, db);
        swap(name_a, name_b);
    }

    string prefix = "";
    int repeat = max(0, db - 2);
    for (int i = 0; i < repeat; ++i) {
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
