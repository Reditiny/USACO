#include <iostream>
#include <fstream>
#include <vector>

using namespace std;

int N, Q;
vector<vector<vector<int>>> graph;
vector<bool> visited;
int k, v;
int ans;

/**
 * 深度优先搜索遍历相关度足够的节点
 */
void dfs(int start) {
    if (visited[start]) {
        return;
    }
    visited[start] = true;
    for (int i = 0; i < graph[start].size(); i++) {
        if (graph[start][i][1] >= k && !visited[graph[start][i][0]]) {
            ans++;
            dfs(graph[start][i][0]);
        }
    }
}

int main() {
    ifstream fin("mootube.in");
    ofstream fout("mootube.out");

    fin >> N >> Q;

    // 初始化图
    graph.resize(N);
    visited.resize(N, false);

    for (int i = 0; i < N; i++) {
        graph[i].resize(0);
    }

    // 构建图
    for (int i = 0; i < N - 1; i++) {
        int p, q, r;
        fin >> p >> q >> r;
        p--;
        q--;
        vector<int> edge1 = {q, r};
        graph[p].push_back(edge1);
        vector<int> edge2 = {p, r};
        graph[q].push_back(edge2);
    }

    // 处理查询
    for (int i = 0; i < Q; i++) {
        fin >> k >> v;
        v--;

        // 重置 visited 数组
        visited.assign(N, false);

        ans = 0;
        dfs(v);
        fout << ans << endl;
    }

    fin.close();
    fout.close();

    return 0;
}
