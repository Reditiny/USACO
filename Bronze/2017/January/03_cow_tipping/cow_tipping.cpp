#include <iostream>
#include <fstream>

using namespace std;

bool cow_tipped[10][10];

// 若(row, col)处的牛不正常则翻转左上角到(row, col)范围内的牛
// 返回值表示是否进行了翻转
bool flip(int row, int col) {
    if (cow_tipped[row][col]) {
        for (int r = 0; r <= row; r++) {
            for (int c = 0; c <= col; c++) {
                cow_tipped[r][c] = !cow_tipped[r][c];
            }
        }
        return true;
    }
    return false;
}

int main() {
    freopen("cowtip.in", "r", stdin);
    freopen("cowtip.out", "w", stdout);

    int N;
    cin >> N;

    for (int row = 0; row < N; row++) {
        string line;
        cin >> line;
        for (int col = 0; col < N; col++) {
            cow_tipped[row][col] = line[col] != '0';
        }
    }
    // 从右下角开始先row方向遍历再col方向遍历查看(row,col)位置是否需要翻转
    // 当遍历完(row,col)之后，该位置的牛不会再受后续翻转的影响
    int min_flips = 0;
    for (int row = N - 1; row >= 0; row--) {
        for (int col = N - 1; col >= 0; col--) {
            min_flips += flip(row, col) ? 1 : 0;
        }
    }
    cout << min_flips << endl;
    return 0;
}
