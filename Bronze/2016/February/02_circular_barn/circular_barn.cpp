#include <iostream>
#include <fstream>
#include <vector>

using namespace std;

int main() {
    freopen("cbarn.in", "r", stdin);
    freopen("cbarn.out", "w", stdout);

    int n;
    cin >> n;

    // 记录每个房间牛的数量以及牛的总数
    vector<int> rooms(n);
    int cow_count = 0;
    for (int i = 0; i < n; i++) {
        cin >> rooms[i];
        cow_count += rooms[i];
    }

    // 从零号房间进入的总距离
    int last_distance = 0;
    for (int i = 1; i < n; i++) {
        last_distance += i * rooms[i];
    }

    // 依次计算从下一个房间进入的总距离
    int min_distance = last_distance;
    for (int i = 1; i < n; i++) {
        // 当入口从 i 改为 i+1 时，相对于 i 为入口时的距离，
        // i 号房间的牛要多走一圈，其他牛都少走 1 步
        int cur_distance = last_distance - (cow_count - rooms[i - 1]) + (n - 1) * rooms[i - 1];
        if (cur_distance < min_distance) {
            min_distance = cur_distance;
        }
        last_distance = cur_distance;
    }

    cout << min_distance << endl;

    return 0;
}
