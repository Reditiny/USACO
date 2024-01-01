#include <iostream>
#include <fstream>
#include <cstring>
#include <vector>
using namespace std;





int main() {
    freopen("crossroad.in", "r", stdin);
    freopen("crossroad.out", "w", stdout);
    int N;
    cin >> N;
    // 记录每头牛的位置，初始为 -1
    vector<int> cowsLocation(10, -1);
    int ans = 0;
    for (int i = 0; i < N; i++) {
        int cow, location;
        cin >> cow >> location;
        cow--;
        if (cowsLocation[cow] != -1 && cowsLocation[cow] != location) {
            ans++;
        }
        cowsLocation[cow] = location;
    }
    cout << ans << endl;
    return 0;
}
