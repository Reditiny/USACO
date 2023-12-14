#include <iostream>
#include <queue>
#include <cstring>

using namespace std;

struct Cell {
    int row, col;
    int distance;

    Cell(int r, int c, int distance) : row(r), col(c), distance(distance) {}
};

int main() {
    int n, d;
    cin >> n >> d;

    char grid[n][n];
    for (int i = 0; i < n; i++) {
        string temp;
        cin >> temp;
        for (int j = 0; j < n; j++) {
            grid[i][j] = temp[j];
        }
    }
    // BFS 计算所有草地到石头的最短距离
    int distance_to_rock[n][n];
    queue<Cell> queue;
    for (int row = 0; row < n; row++) {
        memset(distance_to_rock[row], -1, sizeof(distance_to_rock[row]));
        for (int col = 0; col < n; col++) {
            if (grid[row][col] == '#') {
                queue.push(Cell(row, col, 0));
            }
        }
    }
    while (!queue.empty()) {
        Cell state = queue.front();
        queue.pop();
        // 越界检查
        if (state.row < 0 || state.row >= n || state.col < 0 || state.col >= n) {
            continue;
        }
        // 跳过已经计算过的点
        if (distance_to_rock[state.row][state.col] != -1) {
            continue;
        }
        distance_to_rock[state.row][state.col] = state.distance;
        for (int i = -1; i <= 1; i += 2) {
            queue.push(Cell(state.row + i, state.col, state.distance + 1));
            queue.push(Cell(state.row, state.col + i, state.distance + 1));
        }
    }

    // BFS 从机器人位置出发
    int vis[n][n];
    memset(vis, -1, sizeof(vis));
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            if (grid[i][j] == 'S') {
                queue.push(Cell(i, j, 0));
            }
        }
    }
    // 通过中心机器人的位置和大小来表示一个簇，
    // 如果一个单元格被中心机器人占据，vis 存储了访问每个单元格的簇的大小
    while (!queue.empty()) {
        Cell state = queue.front();
        queue.pop();
        int size = (state.distance - 1) / d;
        // 越界检查
        if (state.row < 0 || state.row >= n || state.col < 0 || state.col >= n) {
            continue;
        }
        // 跳过已经计算过的点
        if (vis[state.row][state.col] != -1) {
            continue;
        }
        // 簇的大小不能超过到石头的最短距离
        if (distance_to_rock[state.row][state.col] <= size) {
            continue;
        }

        vis[state.row][state.col] = size;
        // 更新簇的大小
        size = (state.distance) / d;
        // 新大小不能超过到石头的最短距离
        if (distance_to_rock[state.row][state.col] <= size) {
            continue;
        }
        vis[state.row][state.col] = size;
        for (int i = -1; i <= 1; i += 2) {
            queue.push(Cell(state.row + i, state.col, state.distance + 1));
            queue.push(Cell(state.row, state.col + i, state.distance + 1));
        }
    }

    // 机器人占据的单元格标记为 x
    int ans = 0;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            for (int di = 0; di <= vis[i][j]; di++) {
                for (int dj = 0; dj <= vis[i][j] - di; dj++) {
                    grid[i + di][j + dj] = 'x';
                    grid[i - di][j + dj] = 'x';
                    grid[i + di][j - dj] = 'x';
                    grid[i - di][j - dj] = 'x';
                }
            }
        }
    }
    // 计算 x 的数量
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            ans += (grid[i][j] == 'x') ? 1 : 0;
        }
    }

    cout << ans << endl;

    return 0;
}
