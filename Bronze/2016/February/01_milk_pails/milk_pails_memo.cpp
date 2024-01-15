#include <iostream>
#include <fstream>
#include <vector>

using namespace std;

int X, Y, M;
int result = 0;
vector<bool> visited;
// 深度优先搜索查找最大值
// 通过 visited 数组记录已经访问过的状态，避免重复访问
void dfs(int acc) {
    if (acc > M || visited[acc]) {
        return;
    }
    visited[acc] = true;
    result = max(result, acc);
    dfs(acc + X);
    dfs(acc + Y);
}

int main() {
    ifstream fin("pails.in");
    ofstream fout("pails.out");

    fin >> X >> Y >> M;
    visited.resize(M + 1, false);

    dfs(0);

    fout << result << endl;
    fout.close();

    return 0;
}
