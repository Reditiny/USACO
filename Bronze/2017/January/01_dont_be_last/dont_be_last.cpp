#include <iostream>
#include <fstream>
#include <map>
#include <climits>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
    freopen("notlast.in", "r", stdin);
    freopen("notlast.out", "w", stdout);
    // map 存储每头奶牛的奶量，初始值 0 必须要给，因为有些奶牛可能不出现在产奶日志上
    map<string, int> cow_to_milk;
    cow_to_milk["Bessie"] = 0;
    cow_to_milk["Elsie"] = 0;
    cow_to_milk["Daisy"] = 0;
    cow_to_milk["Gertie"] = 0;
    cow_to_milk["Annabelle"] = 0;
    cow_to_milk["Maggie"] = 0;
    cow_to_milk["Henrietta"] = 0;

    int n;
    cin >> n;
    for (int i = 0; i < n; i++) {
        string cow;
        int milk;
        cin >> cow >> milk;
        cow_to_milk[cow] += milk;
    }

    int min_milk = INT_MAX;
    int second_min_milk = INT_MAX;
    int second_count = 0;
    string ans_cow;
    // 确定最小值与次小值
    for (const auto& entry : cow_to_milk) {
        int milk = entry.second;
        if (milk < min_milk) {
            second_min_milk = min_milk;
            min_milk = milk;
        } else if (milk > min_milk && milk < second_min_milk) {
            second_min_milk = milk;
        }
    }
    // 计算次小值的数量以及次小值的牛
    for (const auto& entry : cow_to_milk) {
        if (entry.second == second_min_milk) {
            second_count++;
            ans_cow = entry.first;
        }
    }
    if (second_count != 1) {
        cout << "Tie" << endl;
    } else {
        cout << ans_cow << endl;
    }

    return 0;
}
