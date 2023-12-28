#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
    freopen("balancing.in", "r", stdin);
    freopen("balancing.out", "w", stdout);

    int n, B;
    cin >> n >> B;

    vector<vector<int>> cows(n, vector<int>(2));
    for (int i = 0; i < n; i++) {
        cin >> cows[i][0] >> cows[i][1];
    }

    int ans = n;

    // 枚举所有可能的分割线
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            int x = cows[i][0] + 1;
            int y = cows[j][1] + 1;
            vector<int> cow_count(4, 0);
            for (const vector<int>& cow : cows) {
                if (cow[0] < x && cow[1] < y) {
                    cow_count[0]++;
                } else if (cow[0] < x && cow[1] > y) {
                    cow_count[1]++;
                } else if (cow[0] > x && cow[1] < y) {
                    cow_count[2]++;
                } else {
                    cow_count[3]++;
                }
            }
            ans = min(ans, *max_element(cow_count.begin(), cow_count.end()));
        }
    }

    cout << ans << endl;

    return 0;
}
