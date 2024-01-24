#include <iostream>
#include <fstream>
#include <unordered_set>
#include <vector>

using namespace std;

int n;
vector<int> next_position;
vector<bool> visited;

/**
 * 从位置 i 开始寻找环同时标记访问过的位置避免重复访问
 * 当遍历到环的入口时，计算出环的大小
 */
int count_cycle_size(int i) {
    int cur = i;
    unordered_set<int> cycle_set;
    visited[cur] = true;
    cycle_set.insert(cur);

    while (true) {
        // 如果 cur 已经在 set 中，说明此处为环的入口
        cur = next_position[cur];
        if (cycle_set.find(cur) != cycle_set.end()) {
            break;
        }

        // 如果 cur 已经被访问过，但是却没有在 set 中
        // 说明后续的点都已经访问过，环内的大小也已经计算过了
        if (visited[cur]) {
            return 0;
        }

        cycle_set.insert(cur);
        visited[cur] = true;
    }

    // 此时 cur 一定在环中，找到环的大小
    int count = 1;
    int next = next_position[cur];

    while (next != cur) {
        count++;
        next = next_position[next];
    }

    return count;
}

int main() {
    ifstream fin("shuffle.in");
    ofstream fout("shuffle.out");

    fin >> n;

    next_position.resize(n);
    visited.resize(n, false);

    for (int i = 0; i < n; i++) {
        fin >> next_position[i];
        next_position[i]--; // 调整为0-based索引
    }

    int ans = 0;

    for (int i = 0; i < n; i++) {
        if (!visited[i]) {
            ans += count_cycle_size(i);
        }
    }

    fout << ans << endl;

    fin.close();
    fout.close();

    return 0;
}


