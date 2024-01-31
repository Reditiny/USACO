#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
#include <climits>
using namespace std;

// 牛移动的方案
int dx[] = {1, 0, -1, 0, 3, 0, -3, 0, 2, 2, 1, 1, -1, -1, -2, -2};
int dy[] = {0, 1, 0, -1, 0, 3, 0, -3, 1, -1, 2, -2, 2, -2, 1, -1};

int main() {
    freopen("visitfj.in", "r", stdin);
    freopen("visitfj.out", "w", stdout);
    int n, t;
    cin >> n >> t;
    vector<vector<int>> field(n, vector<int>(n));
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) { cin >> field[i][j]; }
    }
    // 初始化距离矩阵为 INT_MAX
    vector<vector<int>> dist(n, vector<int>(n, INT_MAX));
    vector<vector<bool>> visited(n, vector<bool>(n));
    // {distance, {row, col}} priority_queue 会根据 distance 的大小来排序
    // 如果自定义的数据类型需要重载 < 运算符使得 priority_queue 能够正常工作
    priority_queue<pair<int, pair<int, int>>> dijkstra;
    dijkstra.push(make_pair(0, make_pair(0, 0)));
    dist[0][0] = 0;
    while (!dijkstra.empty()) {
        int x =  dijkstra.top().second.first, y =  dijkstra.top().second.second;
        dijkstra.pop();
        if (visited[x][y]) { continue; }
        visited[x][y] = true;
        for (int i = 0; i < 16; i++) {
            int next_x = x + dx[i], next_y = y + dy[i];
            if (next_x < 0 || next_x >= n || next_y < 0 || next_y >= n) { continue; }
            // 更新下一步的距离
            if (dist[next_x][next_y] > dist[x][y] + 3 * t + field[next_x][next_y]) {
                dist[next_x][next_y] = dist[x][y] + 3 * t + field[next_x][next_y];
                dijkstra.push(make_pair(-dist[next_x][next_y], make_pair(next_x, next_y)));
            }
        }
        // 如果当前位置距离终点小于 3，那么可以直接走到终点
        int distance_from_end = n - x - 1 + n - y - 1;
        if (distance_from_end < 3) {
            dist[n - 1][n - 1] = min(dist[n - 1][n - 1], dist[x][y] + distance_from_end * t);
        }
    }
    cout << dist[n - 1][n - 1] << endl;
    return 0;
}