#include <iostream>
#include <fstream>
#include <vector>
#include <array>

using namespace std;

int n, k, r;
// 草地 -1 表示路 0 表示未访问 1，2，3...表示连通图编号
vector<vector<int>> grid;
vector<vector<int> > directions = {{{-1, 0}, {1, 0}, {0, -1}, {0, 1}}};
vector<vector<int>> cows;


/**
 * 深度优先搜索同时标记连通图编号
 */
void dfs(int i, int j, int region_count) {
    grid[i][j] = region_count;

    for (auto &d : directions) {
        int nextI = i + d[0];
        int nextJ = j + d[1];

        if (nextI >= 0 && nextI < 2 * n && nextJ >= 0 && nextJ < 2 * n && grid[nextI][nextJ] == 0) {
            dfs(nextI, nextJ, region_count);
        }
    }
}

int main() {
    ifstream fin("countcross.in");
    ofstream fout("countcross.out");

    fin >> n >> k >> r;

    // 将草地扩大两倍 可以使得路和草地都可以在数组表示
    // 例如 原本两个相邻的草地 路没法在数组中表示 扩大后可以表示
    //
    // ##  扩大   ####  路   #｜##
    //     ->    ####  ->   #｜##
    grid.resize(2 * n, vector<int>(2 * n, 0));

    for (int i = 0; i < r; i++) {
        int r1, c1, r2, c2;
        fin >> r1 >> c1 >> r2 >> c2;
        r1--, c1--, r2--, c2--; // 调整为0-based索引

        if (r1 == r2) {
            grid[2 * r1][c1 + c2] = -1;
            grid[2 * r1 + 1][c1 + c2] = -1;
        } else {
            grid[r1 + r2][2 * c1] = -1;
            grid[r1 + r2][2 * c1 + 1] = -1;
        }
    }

    for (int i = 0; i < k; i++) {
        int r, c;
        fin >> r >> c;
        r--, c--; // 调整为0-based索引
        cows.push_back({r * 2, c * 2});
    }

    // 广度优先搜索连通图同时标记连通图编号
    int regionCount = 1;

    for (int i = 0; i < 2 * n; i++) {
        for (int j = 0; j < 2 * n; j++) {
            if (grid[i][j] == 0) {
                dfs(i, j, regionCount);
                regionCount++;
            }
        }
    }

    // 比较每两个牛的位置是否在同一个连通图中
    int ans = 0;

    for (int i = 0; i < k; i++) {
        for (int j = i + 1; j < k; j++) {
            if (grid[cows[i][0]][cows[i][1]] != grid[cows[j][0]][cows[j][1]]) {
                ans++;
            }
        }
    }

    fout << ans << endl;

    fin.close();
    fout.close();

    return 0;
}

