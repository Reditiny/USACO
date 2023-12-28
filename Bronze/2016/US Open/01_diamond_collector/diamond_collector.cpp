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

    sort(diamonds.begin(), diamonds.end());

    int ans = 0;
    int j = 0;

    for (int i = 0; i < n; i++) {
        while (j < n && diamonds[j] - diamonds[i] <= k) {
            j++;
        }
        ans = max(ans, j - i);
    }

    cout << ans << endl;

    return 0;
}
