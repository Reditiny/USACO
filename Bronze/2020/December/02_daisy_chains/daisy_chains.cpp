#include <iostream>
#include <vector>

using namespace std;

int main() {
    int n;
    cin >> n;

    vector<int> daisies(n);
    for (int i = 0; i < n; i++) {
        cin >> daisies[i];
    }

    int ans = 0;
    // 枚举所有可能的区间
    for (int i = 0; i < n; i++) {
        int sum = 0;
        int count = 0;
        for (int j = i; j < n; j++) {
            sum += daisies[j];
            count++;
            if (sum % count == 0) {
                int avg = sum / count;
                for (int k = i; k <= j; k++) {
                    if (daisies[k] == avg) {
                        ans++;
                        break;
                    }
                }
            }
        }
    }

    cout << ans << endl;

    return 0;
}
