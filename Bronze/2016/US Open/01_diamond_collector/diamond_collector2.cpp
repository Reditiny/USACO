#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
    freopen("diamond.in", "r", stdin);
    freopen("diamond.out", "w", stdout);

    int n, k;
    cin >> n >> k;

    vector<int> diamonds;
    for (int i = 0; i < n; i++) {
        int diamond;
        cin >> diamond;
        diamonds.push_back(diamond);
    }

    int ans = 0;

    for (int i = 0; i < diamonds.size(); i++) {
        int floor_count = 0;
        int ceiling_count = 0;
        // 对于每一个 diamonds[i] 分别求 [diamonds[i],diamonds[i]+k] 和 [diamonds[i]-k,diamonds[i]] 两个范围的数量
        for (int j = 0; j < diamonds.size(); j++) {
            if (diamonds[j] >= diamonds[i] && diamonds[j] <= diamonds[i] + k) {
                floor_count++;
            }
            if (diamonds[j] <= diamonds[i] && diamonds[j] >= diamonds[i] - k) {
                ceiling_count++;
            }
        }
        ans = max(ans, max(floor_count, ceiling_count));
    }

    cout << ans << endl;

    return 0;
}
