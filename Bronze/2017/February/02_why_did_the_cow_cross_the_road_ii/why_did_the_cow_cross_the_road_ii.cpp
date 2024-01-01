#include <iostream>
#include <fstream>
#include <cstring>

using namespace std;

const int MAX_N = 26;

int main() {
    freopen("circlecross.in", "r", stdin);
    freopen("circlecross.out", "w", stdout);

    string pos;
    cin >> pos;

    int in_order[MAX_N] = {0};
    int out_order[MAX_N] = {0};
    int order = 0;
    bool in_field[MAX_N] = {false};
    // 记录进出的顺序 进出用同一个顺序记录 因为进出要放在一起比较
    for (int i = 0; i < 52; i++) {
        int cow = pos[i] - 'A';
        if (!in_field[cow]) {
            in_field[cow] = true;
            in_order[cow] = order++;
        } else {
            out_order[cow] = order++;
        }
    }
    int ans = 0;
    // 满足条件的 cows(i,j) 顺序应该为 i..j..i..j 即 i(in)<j(in)<i(out)<j(out)
    for (int i = 0; i < MAX_N; i++) {
        for (int j = 0; j < MAX_N; j++) {
            if (in_order[i] < in_order[j] && in_order[j] < out_order[i] && out_order[i] < out_order[j]) {
                ans++;
            }
        }
    }
    cout << ans << endl;
    return 0;
}
