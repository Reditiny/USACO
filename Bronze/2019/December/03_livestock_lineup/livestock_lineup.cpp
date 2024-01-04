#include <iostream>
#include <vector>
#include <string>
#include <algorithm>

using namespace std;

const int RESTRICT_LEN = 6;
// 按字典序构造牛的组合
const vector<string> COWS = {"Beatrice", "Belinda", "Bella", "Bessie", "Betsy", "Blue", "Buttercup", "Sue"};
vector<vector<string>> orderings;

// 递归遍历得到所有顺序
void build_order(vector<string> ordering) {
    if ((int) (ordering.size()) == 8) {
        orderings.push_back(ordering);
        return;
    }
    for (const string &COW: COWS) {
        if (find(ordering.begin(), ordering.end(), COW) == ordering.end()) {
            ordering.push_back(COW);
            build_order(ordering);
            ordering.pop_back();
        }
    }
}

// 实现同 Java 中的 indexOf 功能
int index_of(const vector<string> &order, const string &cow) {
    return find(order.begin(), order.end(), cow) - order.begin();
}

int main() {
    freopen("lineup.in", "r", stdin);
    freopen("lineup.out", "w", stdout);
    int n;
    cin >> n;

    vector<pair<string, string>> restrictions;
    for (int i = 0; i < n; i++) {
        string cow1 = "";
        string cow2 = "";
        for (int j = 0; j < RESTRICT_LEN; j++) {
            string word;
            cin >> word;
            cow1 = cow1.empty() ? word : cow1;
            cow2 = word;
        }
        restrictions.emplace_back(cow1, cow2);
    }

    // 因为在 cows 构造时就已经满足字典序了 第一个满足条件的order即是字典序最小的
    build_order({});
    for (vector<string> &order: orderings) {
        bool ok = true;
        for (const pair<string, string> &rule: restrictions) {
            if (abs(index_of(order, rule.first) - index_of(order, rule.second)) > 1) {
                ok = 0;
                break;
            }
        }
        if (ok) {
            for (const string &i: order) { cout << i << '\n'; }
            break;
        }
    }
}