#include <iostream>
#include <fstream>
#include <unordered_map>
#include <climits>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
    freopen("notlast.in", "r", stdin);
    freopen("notlast.out", "w", stdout);
    // map 存储每头奶牛的奶量，初始值 0 必须要给，因为有些奶牛可能不出现在产奶日志上
    unordered_map<string, int> cow_to_milk;
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


    vector<pair<string, int>> cowsList;
    for (auto& entry : cow_to_milk) {
        cowsList.push_back(entry);
    }
    // 根据奶量排序，如果第二小的奶量只有一个，那么就是答案，否则就是 Tie
    sort(cowsList.begin(), cowsList.end(), [](pair<string, int> a, pair<string, int> b) {
        return a.second < b.second;
    });

    int i = 0;
    string result = "Tie";

    while (i < cowsList.size()) {
        if (cowsList[i].second != cowsList[0].second) {
            if (i == cowsList.size() - 1 || cowsList[i].second != cowsList[i + 1].second) {
                result = cowsList[i].first;
            }
            break;
        }
        i++;
    }

    cout << result << endl;
    return 0;
}
