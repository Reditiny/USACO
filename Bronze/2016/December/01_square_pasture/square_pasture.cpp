#include <cstdio>
#include <iostream>
using namespace std;

int main() {
    freopen("square.in", "r", stdin);
    freopen("square.out", "w", stdout);
    int x1, y1, x2, y2;
    int x3, y3, x4, y4;
    cin >> x1 >> y1 >> x2 >> y2 >> x3 >> y3 >> x4 >> y4;
    // 找出覆盖两个牧场的最小矩形的边
    int left = min(x1, x3);
    int right = max(x2, x4);
    int bottom = min(y1, y3);
    int top = max(y2, y4);
    // 正方形的最小边长是矩形的最大边长
    int edge = max(right - left, top - bottom);
    cout << edge * edge << endl;
    return 0;
}