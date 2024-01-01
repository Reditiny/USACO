#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>
using namespace std;

// 计算矩形面积
int area(int board[]) {
    return (board[2] - board[0]) * (board[3] - board[1]);
}
// 计算两个矩形重叠的面积
int cover_area(int board[], int truck[]) {
    int x_overlap = max(0, min(board[2], truck[2]) - max(board[0], truck[0]));
    int y_overlap = max(0, min(board[3], truck[3]) - max(board[1], truck[1]));
    return x_overlap * y_overlap;
}

int main() {

    freopen("billboard.in", "r", stdin);
    freopen("billboard.out", "w", stdout);

    int rectangles[3][4];
    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 4; j++) {
            cin >> rectangles[i][j];
        }
    }
    // rectangles[0] 表示第一个广告牌的坐标 rectangles[1] 表示第二个广告牌的坐标 rectangles[2] 表示卡车的坐标
    // 计算广告牌面积和卡车挡住两个广告牌的面积
    int ans = 0;
    for (int i = 0; i < 2; i++) {
        ans += area(rectangles[i]) - cover_area(rectangles[i], rectangles[2]);
    }
    cout << ans << endl;
    return 0;
}
