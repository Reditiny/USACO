#include <iostream>
#include <cstdio>

using namespace std;

#define MAXB 15000

// 计算数字在指定进制下的值
int evaluate(const string& num, int base) {
    return (num[0] - '0') * base * base +
           (num[1] - '0') * base +
           (num[2] - '0');
}

int main() {
    freopen("whatbase.in", "r", stdin);
    freopen("whatbase.out", "w", stdout);

    int T;
    cin >> T;
    for (int t = 1; t <= T; t++) {
        string X, Y;
        cin >> X >> Y;
        // 将两个基数初始化为10，递增得到较小结果的基数，直到它们得到相等的结果。
        int x_base = 10;
        int y_base = 10;
        while (x_base <= MAXB && y_base <= MAXB) {
            int num_x = evaluate(X, x_base);
            int num_y = evaluate(Y, y_base);
            if (num_x < num_y) {
                x_base++;
            } else if (num_y < num_x) {
                y_base++;
            } else {
                cout << x_base << ' ' << y_base << '\n';
                break;
            }
        }
    }
    return 0;
}