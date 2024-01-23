#include <iostream>
#include <fstream>
#include <vector>
#include <unordered_set>
#include <algorithm>

using namespace std;
struct pair_hash {
    size_t operator()(const pair<int, int> &pair) const {
        size_t hash1 = hash<int>{}(pair.first);
        size_t hash2 = hash<int>{}(pair.second);
        return hash1 * 1000 + hash2;
    }
};
int x, y, k, m;
unordered_set<pair<int, int>, pair_hash> possible_combination;

// 从当前状态得到下一个状态的函数
vector<pair<int, int>> next_combination(const pair<int, int>& cur) {
    vector<pair<int, int>> coms;

    // 1. 倒空 x
    coms.push_back({0, cur.second});
    // 2. 倒空 y
    coms.push_back({cur.first, 0});
    // 3. 倒满 x
    coms.push_back({x, cur.second});
    // 4. 倒满 y
    coms.push_back({cur.first, y});
    // 5. x 倒入 y
    if (cur.first + cur.second > x) {
        coms.push_back({x, cur.second - (x - cur.first)});
    } else {
        coms.push_back({cur.first + cur.second, 0});
    }
    // 6. y 倒入 x
    if (cur.first + cur.second > y) {
        coms.push_back({cur.first - (y - cur.second), y});
    } else {
        coms.push_back({0, cur.first + cur.second});
    }

    return coms;
}

int main() {
    ifstream fin("pails.in");
    ofstream fout("pails.out");

    fin >> x >> y >> k >> m;
    possible_combination.insert({0, 0});

    // 从当前可能的状态集合出发操作一次得到下一个可能的状态集合，重复 k 次
    for (int i = 0; i < k; i++) {
        unordered_set<pair<int, int>, pair_hash> temp;
        for (const pair<int, int>& cur : possible_combination) {
            vector<pair<int, int>> coms = next_combination(cur);
            temp.insert(coms.begin(), coms.end());
        }
        possible_combination = temp;
    }

    int ans = m;

    // 遍历所有可能的状态，计算最小差值
    for (const pair<int, int>& cur : possible_combination) {
        ans = min(ans, abs(cur.first + cur.second - m));
    }

    fout << ans << endl;
    fout.close();

    return 0;
}


