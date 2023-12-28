#include <iostream>
#include <vector>
using namespace std;

const int LEN = 100;

int main() {
    freopen("speeding.in", "r", stdin);
    freopen("speeding.out", "w", stdout);

    int N, M;
    cin >> N >> M;

    int start = 0;
    // 记录每单位距离的限速 单位为 1 mile
    vector<int> speed_limit(LEN);
    for (int i = 0; i < N; i++) {
        int distance, speed;
        cin >> distance >> speed;
        for (int j = start; j < start + distance; j++) { speed_limit[j] = speed; }
        start += distance;
    }

    start = 0;
    // 记录每单位距离的实际速度 单位为 1 mile
    vector<int> real_speed(LEN);
    for (int i = 0; i < M; i++) {
        int length, speed;
        cin >> length >> speed;
        for (int j = start; j < start + length; j++) { real_speed[j] = speed; }
        start += length;
    }
    // 以 1 mile 为单位计算最大超速值
    int ans = 0;
    for (int i = 0; i < LEN; i++) { ans = max(ans, real_speed[i] - speed_limit[i]); }
    cout << ans << endl;
}