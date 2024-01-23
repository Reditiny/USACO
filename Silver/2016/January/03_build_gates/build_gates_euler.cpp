#include <iostream>
#include <fstream>
#include <set>
#include <unordered_map>
#include <vector>

using namespace std;

int main() {
    ifstream fin("gates.in");
    ofstream fout("gates.out");

    int n;
    set<pair<int, int>> vertices;
    set<pair<pair<int, int>, pair<int, int>>> edges;
    int move[][2] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    unordered_map<char, int> map;

    map['N'] = 0;
    map['S'] = 1;
    map['W'] = 2;
    map['E'] = 3;

    fin >> n;
    int lastX = 0, lastY = 0;
    int curX = 0, curY = 0;
    vertices.insert({curX, curY});

    char step;
    for (int i = 0; i < n; ++i) {
        fin >> step;
        lastX = curX;
        lastY = curY;
        curX += move[map[step]][0];
        curY += move[map[step]][1];
        pair<int, int> node1 = {lastX, lastY};
        pair<int, int> node2 = {curX, curY};
        vertices.insert(node2);
        if (step == 'N' || step == 'E') {
            edges.insert({node1, node2});
        } else {
            edges.insert({node2, node1});
        }
    }

    // 欧拉公式 顶点数 - 边数 + 面数 = 2
    // V - E + F = 2 => F = 2 - V + E
    // 面即一个封闭区域，门数 = 面数 - 1
    fout << 2 - vertices.size() + edges.size() - 1 << endl;

    fout.close();

    return 0;
}
