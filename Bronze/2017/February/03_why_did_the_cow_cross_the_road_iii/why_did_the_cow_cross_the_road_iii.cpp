#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
    freopen("cowqueue.in", "r", stdin);
    ofstream fout("cowqueue.out");

    int N;
    cin >> N;

    vector<vector<int>> cows;
    for (int i = 0; i < N; i++) {
        int arrival, duration;
        cin >> arrival >> duration;
        cows.push_back({arrival, duration});
    }
    // 先来后到排序，同时到按答题时长排序
    sort(cows.begin(), cows.end(), [](const vector<int>& a, const vector<int>& b) {
        if (a[0] == b[0]) {
            return a[1] < b[1];
        }
        return a[0] < b[0];
    });
    int ans = 0;
    for (int i = 0; i < N; i++) {
        if (cows[i][0] >= ans) {
            // 这头牛到达时间比上一头牛结束时间更晚，到了才能答题
            ans = cows[i][0] + cows[i][1];
        } else {
            // 这头牛到达时间比上一头牛结束时间更早，等上头牛答完才能答题
            ans += cows[i][1];
        }
    }
    fout << ans << endl;
    return 0;
}
