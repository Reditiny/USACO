#include <iostream>
#include <fstream>
#include <cmath>
#include <vector>

using namespace std;

int n;
vector<vector<int>> cow_positions;
vector<int> powers;
vector<bool> visited;
int visited_count;

// 深度优先搜索遍历
void dfs(int i) {
    if (visited[i]) {
        return;
    }

    visited[i] = true;
    visited_count++;

    for (int j = 0; j < n; j++) {
        if (i != j && !visited[j] && hypot(cow_positions[i][0] - cow_positions[j][0],
                                           cow_positions[i][1] - cow_positions[j][1]) <= powers[i]) {
            dfs(j);
        }
    }
}

int main() {
    ifstream fin("moocast.in");
    ofstream fout("moocast.out");

    fin >> n;
    cow_positions.resize(n, vector<int>(2));
    powers.resize(n);
    visited.resize(n);

    for (int i = 0; i < n; i++) {
        fin >> cow_positions[i][0] >> cow_positions[i][1] >> powers[i];
        cow_positions[i][0]--;  // Adjusting to 0-based indexing
        cow_positions[i][1]--;
    }
    // 从每头牛开始遍历，计算每头牛能到达的牛的数量，取最大值
    int ans = 0;
    for (int i = 0; i < n; i++) {
        visited = vector<bool>(n, false);
        visited_count = 0;
        dfs(i);
        ans = max(ans, visited_count);
    }

    fout << ans << endl;
    fout.close();

    return 0;
}

