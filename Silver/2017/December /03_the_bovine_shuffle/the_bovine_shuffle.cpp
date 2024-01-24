#include <iostream>
#include <fstream>
#include <unordered_set>
#include <vector>

using namespace std;

int n;
vector<int> next_position;
vector<bool> in_cycle;

/**
 * 从位置 i 开始寻找环 若是环则返回环的大小
 * 例如 1 -> 3 -> 7 -> 1  每次换位时 1 3 7 位置上始终有牛
 * 例如 1 -> 3 -> 7 -> 5 -> 3  虽然有环但 1 不是起点，当遍历到 5 时环就会被计算
 */
int count_cycle_size(int i) {
    int cur = i;
    int count = 0;
    unordered_set<int> cycle_set;

    while (cycle_set.find(cur) == cycle_set.end()) {
        cycle_set.insert(cur);
        cur = next_position[cur];

        // 如果遍历的过程中发现了其他的环，那么 i 作为起点就不可能在环内
        if (in_cycle[cur]) {
            return 0;
        }
    }

    // 如果环的起点是 i，那么环的大小就是 cycle_set 的大小
    if (cur == i) {
        for (int j : cycle_set) {
            in_cycle[j] = true;
            count++;
        }
    }

    return count;
}

int main() {
    ifstream fin("shuffle.in");
    ofstream fout("shuffle.out");

    fin >> n;

    next_position.resize(n);
    in_cycle.resize(n, false);

    for (int i = 0; i < n; i++) {
        fin >> next_position[i];
        next_position[i]--;
    }

    int ans = 0;

    for (int i = 0; i < n; i++) {
        if (!in_cycle[i]) {
            ans += count_cycle_size(i);
        }
    }

    fout << ans << endl;

    fin.close();
    fout.close();

    return 0;
}


