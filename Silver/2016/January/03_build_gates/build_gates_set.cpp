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
    set<pair<pair<int, int>, pair<int, int>>> visited_edges;
    set<pair<int, int>> visited_vertices;
    int move[][2] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    unordered_map<char, int> map;

    map['N'] = 0;
    map['S'] = 1;
    map['W'] = 2;
    map['E'] = 3;

    fin >> n;
    int ans = 0;
    int lastX = 0, lastY = 0;
    int curX = 0, curY = 0;
    visited_vertices.insert({curX, curY});

    char step;
    for (int i = 0; i < n; ++i) {
        fin >> step;
        lastX = curX;
        lastY = curY;
        curX += move[map[step]][0];
        curY += move[map[step]][1];
        pair<int, int> node1 = {lastX, lastY};
        pair<int, int> node2 = {curX, curY};
        pair<pair<int, int>, pair<int, int>> edge = {node1, node2};
        pair<pair<int, int>, pair<int, int>> reverseEdge = {node2, node1};
        // 创建边的过程中 一个新的边连到已经存在的点上就会隔出一块封闭图形 需要多一个门让这块图形和外面连通
        if (visited_edges.find(edge) == visited_edges.end() && visited_edges.find(reverseEdge) == visited_edges.end()
            && visited_vertices.find(node2) != visited_vertices.end()) {
            ans++;
        }
        visited_edges.insert(edge);
        visited_vertices.insert(node2);
    }

    fout << ans << endl;
    fout.close();

    return 0;
}
