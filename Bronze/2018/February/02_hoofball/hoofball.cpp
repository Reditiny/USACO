#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>

using namespace std;

int N;
vector<int> cows;
vector<int> receive_count;
vector<int> next_cow;

int pass(int cow) {
    int left_cow = -1;
    int right_cow = -1;
    int left_distance = 1000;
    int right_distance = 1000;
    // 找到 cow 左右两边最近的牛
    for (int i = 0; i < N; i++) {
        if (cows[i] < cows[cow] && cows[cow] - cows[i] < left_distance) {
            left_cow = i;
            left_distance = cows[cow] - cows[i];
        }
        if (cows[i] > cows[cow] && cows[i] - cows[cow] < right_distance) {
            right_cow = i;
            right_distance = cows[i] - cows[cow];
        }
    }
    if (left_distance <= right_distance) {
        return left_cow;
    }
    return right_cow;
}

int main() {
    ifstream fin("hoofball.in");
    ofstream fout("hoofball.out");

    fin >> N;
    cows.resize(N);
    receive_count.resize(N, 0);
    next_cow.resize(N);

    for (int i = 0; i < N; i++) {
        fin >> cows[i];
    }

    // 记录每头牛的传球目标 计算每头牛接到不同球的次数
    for (int i = 0; i < N; i++) {
        int next = pass(i);
        next_cow[i] = next;
        receive_count[next]++;
    }

    int ans = 0;
    for (int i = 0; i < N; i++) {
        // 如果这头牛不会接到其他牛的球，那么需要给它一颗球
        if (receive_count[i] == 0) {
            ans++;
        }
        // 两头牛只会接到彼此的球，那么需要给其中一头牛一颗球
        if (i < next_cow[i] && next_cow[next_cow[i]] == i &&
            receive_count[i] == 1 && receive_count[next_cow[i]] == 1) {
            ans++;
        }
    }

    fout << ans << endl;

    fin.close();
    fout.close();

    return 0;
}
