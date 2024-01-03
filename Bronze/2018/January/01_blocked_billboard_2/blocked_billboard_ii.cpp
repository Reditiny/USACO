#include <iostream>
#include <fstream>
#include <algorithm>

using namespace std;

int billboards[2][4];
// 判断点是否在矩形内
bool cover_corner(int x, int y) {
    return x >= billboards[1][0] && x <= billboards[1][2] && y >= billboards[1][1] && y <= billboards[1][3];
}
// 计算矩形面积
int area(int board[4]) {
    return (board[2] - board[0]) * (board[3] - board[1]);
}
// 计算两个矩形重叠的面积
int coverArea(int board1[4], int board2[4]) {
    int xOverlap = max(0, min(board1[2], board2[2]) - max(board1[0], board2[0]));
    int yOverlap = max(0, min(board1[3], board2[3]) - max(board1[1], board2[1]));
    return xOverlap * yOverlap;
}

int main() {
    ifstream fin("billboard.in");
    ofstream fout("billboard.out");

    for (int i = 0; i < 2; i++) {
        for (int j = 0; j < 4; j++) {
            fin >> billboards[i][j];
            billboards[i][j] += 1000;
        }
    }
    // 判断覆盖了几个角
    int cover_corner_count = 0;
    if (cover_corner(billboards[0][0], billboards[0][1])) {
        cover_corner_count++;
    }
    if (cover_corner(billboards[0][0], billboards[0][3])) {
        cover_corner_count++;
    }
    if (cover_corner(billboards[0][2], billboards[0][1])) {
        cover_corner_count++;
    }
    if (cover_corner(billboards[0][2], billboards[0][3])) {
        cover_corner_count++;
    }
    // 如果没能盖住两个角就需要用和原面积一样大的布
    int ans = area(billboards[0]);
    if (cover_corner_count >= 2) {
        ans -= coverArea(billboards[0], billboards[1]);
    }

    fout << ans << endl;

    fin.close();
    fout.close();

    return 0;
}
