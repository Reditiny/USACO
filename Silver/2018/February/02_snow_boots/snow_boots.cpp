#include <iostream>
#include <fstream>
#include <vector>
#include <climits>
using namespace std;

vector<vector<int>> boots;
vector<int> snow_depth;
vector<vector<bool>> visited;
int n, b;
int ans = INT_MAX;
/**
 * 在 tile 砖块上穿着 boot 鞋子
 * 深度优先搜索遍历可能的[砖块，鞋子]组合
 */
void dfs(int tile, int boot) {
    if (visited[tile][boot]) {
        return;
    }

    visited[tile][boot] = true;

    // 到达最后一块有雪的砖，现在鞋子的编号就是已经丢掉的鞋子的数量
    if (tile == n - 1) {
        ans = min(ans, boot);
    }

    // 从当前砖块出发，穿当前的鞋子，尝试所有可能的下一个砖块
    for (int next_tile = tile + 1; next_tile < n && next_tile - tile <= boots[boot][1]; next_tile++) {
        if (snow_depth[next_tile] <= boots[boot][0]) {
            dfs(next_tile, boot);
        }
    }

    // 在当前砖块上，尝试所有可能的鞋子
    for (int next_boot = boot + 1; next_boot < b; next_boot++) {
        if (snow_depth[tile] <= boots[next_boot][0]) {
            dfs(tile, next_boot);
        }
    }
}


int main() {
    ifstream fin("snowboots.in");
    ofstream fout("snowboots.out");

    fin >> n >> b;
    snow_depth.resize(n);
    visited.resize(n, vector<bool>(b, false));

    for (int i = 0; i < n; i++) {
        fin >> snow_depth[i];
    }

    for (int i = 0; i < b; i++) {
        vector<int> boot(2);
        fin >> boot[0] >> boot[1];
        boots.push_back(boot);
    }

    dfs(0, 0);

    fout << ans << endl;

    fin.close();
    fout.close();

    return 0;
}
