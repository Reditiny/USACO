#include <iostream>
#include <fstream>
#include <vector>
#include <unordered_set>
#include <algorithm>

using namespace std;

// 用于 unordered_set 的 pair 的 hash 函数
struct pair_hash {
    size_t operator()(const pair<int, int> &pair) const {
        size_t hash1 = hash<int>{}(pair.first);
        size_t hash2 = hash<int>{}(pair.second);
        return hash1 * 1000 + hash2;
    }
};

int main() {
    ifstream fin("gymnastics.in");
    ofstream fout("gymnastics.out");

    int k, n;
    fin >> k >> n;

    vector<vector<int>> cows(k, vector<int>(n));

    for (int i = 0; i < k; i++) {
        for (int j = 0; j < n; j++) {
            fin >> cows[i][j];
        }
    }
    unordered_set<pair<int, int>, pair_hash> ans_pairs;

    for (int t = 0; t < k; t++) {
        unordered_set<pair<int, int>, pair_hash> cur_pairs;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                pair<int, int> pair = {cows[t][i], cows[t][j]};
                cur_pairs.insert(pair);
            }
        }

        if (ans_pairs.empty()) {
            ans_pairs = cur_pairs;
        } else {
            unordered_set<pair<int, int>, pair_hash> intersectionPairs;
            for (const auto &pair : ans_pairs) {
                if (cur_pairs.count(pair)) {
                    intersectionPairs.insert(pair);
                }
            }
            ans_pairs = intersectionPairs;
        }
    }

    fout << ans_pairs.size() << endl;
    fout.close();

    return 0;
}
