#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>
#include <unordered_set>

using namespace std;

int main() {
    freopen("badmilk.in", "r", stdin);
    freopen("badmilk.out", "w", stdout);

    int n, m, d, s;
    cin >> n >> m >> d >> s;
    // 记录喝牛奶的信息[人,奶,时间]
    vector<vector<int>> drink_info;
    unordered_set<int> target_milk;
    for (int i = 0; i < d; i++) {
        vector<int> info(3);
        cin >> info[0] >> info[1] >> info[2];
        drink_info.push_back(info);
        target_milk.insert(info[1]);
    }
    // 按人员编号排序 编号相同时按时间排序 为了满足后续双指针实现
    sort(drink_info.begin(), drink_info.end(), [](const vector<int>& a, const vector<int>& b) {
        if (a[0] == b[0]) {
            return a[2] < b[2];
        }
        return a[0] < b[0];
    });
    // 记录生病信息[人,时间]
    vector<vector<int>> sick_info;
    for (int i = 0; i < s; i++) {
        vector<int> info(2);
        cin >> info[0] >> info[1];
        sick_info.push_back(info);
    }
    // 按人员编号排序
    sort(sick_info.begin(), sick_info.end(), [](const vector<int>& a, const vector<int>& b) {
        return a[0] < b[0];
    });
    // 双指针遍历生病信息的同时 依次缩减坏牛奶的范围
    int j = 0;
    for (int i = 0; i < s; i++) {
        int cur_sick_person = sick_info[i][0];
        int cur_sick_time = sick_info[i][1];
        unordered_set<int> candidate_milk;
        while (j < d && drink_info[j][0] < cur_sick_person) {
            j++;
        }
        while (j < d && drink_info[j][0] == cur_sick_person) {
            if (drink_info[j][2] < cur_sick_time) {
                candidate_milk.insert(drink_info[j][1]);
            }
            j++;
        }
        for (auto it = target_milk.begin(); it != target_milk.end();) {
            if (candidate_milk.find(*it) == candidate_milk.end()) {
                it = target_milk.erase(it);
            } else {
                ++it;
            }
        }
    }
    // 找到喝过坏牛奶的人
    unordered_set<int> sick_person;

    for (int i = 0; i < d; i++) {
        if (target_milk.find(drink_info[i][1]) != target_milk.end()) {
            sick_person.insert(drink_info[i][0]);
        }
    }
    cout << sick_person.size() << endl;

    return 0;
}
