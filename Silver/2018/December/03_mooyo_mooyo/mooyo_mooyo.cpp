#include <iostream>
#include <fstream>
#include <vector>
#include <queue>

using namespace std;

int n, k;
vector<vector<int>> grid;
vector<vector<bool>> visited;
vector<vector<int>> directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
/**
 * 深度搜索并获得连通图大小
 */
int dfs(int i, int j, int color) {
    visited[i][j] = true;
    int count = 1;
    for (const auto& m : directions) {
        int x = i + m[0];
        int y = j + m[1];
        if (x >= 0 && x < n && y >= 0 && y < 10 && !visited[x][y] && grid[x][y] == color) {
            count += dfs(x, y, color);
        }
    }
    return count;
}
/**
 * 广度搜索并将连通图中的数字置为0
 */
void disappear_bfs(int i, int j, int color) {
    grid[i][j] = 0;
    queue<pair<int, int>> q;
    q.push({i, j});
    while (!q.empty()) {
        auto cur = q.front();
        q.pop();
        int row = cur.first;
        int col = cur.second;
        for (const auto& m : directions) {
            int x = row + m[0];
            int y = col + m[1];
            if (x >= 0 && x < n && y >= 0 && y < 10 && grid[x][y] == color) {
                grid[x][y] = 0;
                q.push({x, y});
            }
        }
    }
}
/**
 * 模拟重力下落
 */
void fall() {
    for (int i = 0; i < 10; i++) {
        vector<int> column(n);
        int index = n - 1;
        for (int j = n - 1; j >= 0; j--) {
            if (grid[j][i] != 0) {
                column[index] = grid[j][i];
                index--;
            }
        }
        for (int j = 0; j < n; j++) {
            grid[j][i] = column[j];
        }
    }
}

int main() {
    ifstream fin("mooyomooyo.in");
    ofstream fout("mooyomooyo.out");

    fin >> n >> k;
    grid.resize(n, vector<int>(10));

    for (int i = 0; i < n; i++) {
        string line;
        fin >> line;
        for (int j = 0; j < 10; j++) {
            grid[i][j] = line[j] - '0';
        }
    }

    while (true) {
        bool flag = false;
        visited.assign(n, vector<bool>(10, false));

        // 搜索所有连通图
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 10; j++) {
                if (grid[i][j] != 0 && !visited[i][j]) {
                    int count = dfs(i, j, grid[i][j]);
                    if (count >= k) {
                        // 连通图大小至少为k则将数字置为0
                        flag = true;
                        disappear_bfs(i, j, grid[i][j]);
                    }
                }
            }
        }

        if (!flag) {
            break;
        }
        fall();
    }

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < 10; j++) {
            fout << grid[i][j];
        }
        fout << endl;
    }

    fin.close();
    fout.close();

    return 0;
}
