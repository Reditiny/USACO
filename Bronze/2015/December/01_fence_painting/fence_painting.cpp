#include <iostream>
using namespace std;

int main() {
    freopen("paint.in", "r", stdin);
    freopen("paint.out", "w", stdout);

    int a, b, c, d;
    cin >> a >> b >> c >> d;

    int ans;
    // 判断两个区间是否有交叉
    if (b < c || d < a) {
        ans = (b - a) + (d - c);
    } else {
        ans = max(b, d) - min(a, c);
    }
    cout << ans << "\n";
}