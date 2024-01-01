#include <iostream>
#include <fstream>
#include <algorithm>

using namespace std;
bool compare_arrays(bool arr1[], bool arr2[]) {
    for (int i = 0; i < 3; i++) {
        if (arr1[i] != arr2[i]) {
            return false;
        }
    }
    return true;
}

int main() {
    freopen("measurement.in", "r", stdin);
    freopen("measurement.out", "w", stdout);

    int n;
    cin >> n;
    // 0 Bessie 1 Elsie 2 Mildred 按日期顺序依次记录三头牛的奶量变化量
    int measurements[101][3] = {0};
    for (int i = 0; i < n; i++) {
        int day, milk;
        string cow;
        cin >> day >> cow >> milk;
        switch (cow[0]) {
            case 'B':
                measurements[day][0] = milk;
                break;
            case 'E':
                measurements[day][1] = milk;
                break;
            case 'M':
                measurements[day][2] = milk;
                break;
        }
    }
    // 0 Bessie 1 Elsie 2 Mildred 按日期顺序依次记录三头牛的奶量，每次记录后判断是否需要修改display
    int ans = 0;
    int last_milk[3] = {7, 7, 7};
    bool last_display[3] = {false, false, false};
    for (int i = 1; i <= 100; i++) {
        if (measurements[i][0] != 0 || measurements[i][1] != 0 || measurements[i][2] != 0) {
            int cur_milk[3] = {last_milk[0] + measurements[i][0], last_milk[1] + measurements[i][1], last_milk[2] + measurements[i][2]};
            bool cur_display[3] = {false, false, false};
            int max_milk = max(cur_milk[0], max(cur_milk[1], cur_milk[2]));
            for (int j = 0; j < 3; j++) {
                if (cur_milk[j] == max_milk) {
                    cur_display[j] = true;
                }
            }
            if (!compare_arrays(cur_display, last_display)) {
                ans++;
                for (int j = 0; j < 3; j++) {
                    last_display[j] = cur_display[j];
                }
            }
            for (int j = 0; j < 3; j++) {
                last_milk[j] = cur_milk[j];
            }
        }
    }
    cout << ans << endl;
    return 0;
}
