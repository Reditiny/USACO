#include <iostream>
#include <fstream>
#include <vector>

using namespace std;

int main() {
    freopen("shuffle.in", "r", stdin);
    freopen("shuffle.out", "w", stdout);
    int N;
    cin >> N;
    // shuffle 方式
    vector<int> shuffle(N);
    //  当前顺序
    vector<int> order(N);
    for (int i = 0; i < N; i++) {
        cin >> shuffle[i];
    }
    for (int i = 0; i < N; i++) {
        cin >> order[i];
    }
    // 根据 shuffle 方式重新排列三次
    for (int i = 0; i < 3; i++) {
        vector<int> temp(N);
        for (int j = 0; j < N; j++) {
            temp[j] = order[shuffle[j] - 1];
        }
        order = temp;
    }
    for (int i = 0; i < N; i++) {
        cout << order[i] << endl;
    }
    return 0;
}
