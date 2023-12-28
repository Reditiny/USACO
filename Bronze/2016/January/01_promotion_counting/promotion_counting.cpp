#include <iostream>
#include <fstream>

using namespace std;

int main() {
    freopen("promote.in", "r", stdin);
    freopen("promote.out", "w", stdout);
    // 一维 bronze silver gold platinum
    // 二维 before after
    int participants[4][2];
    int ans[3] = {0};

    for (int i = 0; i < 4; i++) {
        for (int j = 0; j < 2; j++) {
            cin >> participants[i][j];
        }
    }

    // 高级别多出来的人数就是低级别晋级的人
    for (int i = 3; i > 0; i--) {
        int delta = participants[i][1] - participants[i][0];
        participants[i - 1][1] += delta;
        ans[i - 1] += delta;
    }

    for (int i = 0; i < 3; i++) {
        cout << ans[i] << endl;
    }

    return 0;
}
