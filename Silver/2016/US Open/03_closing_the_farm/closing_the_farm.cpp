#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>

using namespace std;

int n;
int m;
vector<vector<int>> graph;
vector<bool> closed;
vector<bool> visited;
vector<int> closeOrder;
int visitedCount;
// 深度优先搜索遍历图
void dfs(int node) {
    if (visited[node] || closed[node]) {
        return;
    }

    visitedCount++;
    visited[node] = true;

    for (int neighbor : graph[node]) {
        dfs(neighbor);
    }
}

int main() {
    ifstream fin("closing.in");
    ofstream fout("closing.out");

    fin >> n >> m;

    closeOrder.resize(n);
    closed.resize(n, false);
    graph.resize(n, vector<int>());

    for (int i = 0; i < n; i++) {
        graph[i].resize(0);
    }

    for (int i = 0; i < m; i++) {
        int a, b;
        fin >> a >> b;
        a--; b--;
        graph[a].push_back(b);
        graph[b].push_back(a);
    }

    for (int i = 0; i < n; i++) {
        fin >> closeOrder[i];
        closeOrder[i]--;
    }
    // 依次关闭barn，然后从最后一个barn开始dfs查看图的连通性
    for (int i = 0; i < n; i++) {
        visited = vector<bool>(n, false);
        visitedCount = 0;
        dfs(closeOrder[n - 1]);

        if (visitedCount == n - i) {
            fout << "YES" << endl;
        } else {
            fout << "NO" << endl;
        }

        closed[closeOrder[i]] = true;
    }

    fout.close();

    return 0;
}

