#include <iostream>
#include <fstream>
#include <string>

using namespace std;

int main() {
    freopen("cowsignal.in", "r", stdin);
    freopen("cowsignal.out", "w", stdout);

    int m, n, k;
    cin >> m >> n >> k;

    // 每个字符串 s 的每个字符重复 k 次
    for (int i = 0; i < m; i++) {
        string s;
        cin >> s;

        for (int j = 0; j < k; j++) {
            for (int l = 0; l < n; l++) {
                for (int o = 0; o < k; o++) {
                    cout << s[l];
                }
            }
            cout << endl;
        }
    }
    return 0;
}
