#include <iostream>
#include <stack>
#include <vector>
using namespace std;

int main() {
    freopen("art2.in", "r", stdin);
    freopen("art2.out", "w", stdout);
    int N;
    cin >> N;
    // 记录颜色以及颜色的起始和结束位置
    vector<int> colors(N + 1);
    vector<int> start(N + 1, INT32_MAX);
    vector<int> end(N + 1, INT32_MIN);
    for (int i = 1; i <= N; i++) {
        cin >> colors[i];
        start[colors[i]] = min(start[colors[i]], i);
        end[colors[i]] = max(end[colors[i]], i);
    }

    int ans = 0;
    start[0] = 0;
    end[0] = N + 1;
    stack<int> active;
    // 依次遍历每一个颜色
    for (int i = 0; i <= N; i++) {
        int cur_color = colors[i];
        // 首次遇到该颜色，入栈，栈内元素个数就是当前所需的轮数
        // 因为栈内的颜色还没有到末端，所以新出现的颜色不能在同一轮中完成涂色
        if (i == start[cur_color]) {
            active.emplace(cur_color);
            ans = max(ans, (int)active.size());
        }
        // 当出现 ABAB 的颜色分布时无法完成涂色
        // 需要某个颜色完全包裹另一个颜色，或者两者完全不相交
        // 当前遍历到的颜色(A)不是栈顶颜色(B)，说明A比B先入栈，但是当前的A之后仍有B
        if (cur_color != active.top()) {
            cout << "-1" << endl;
            return 0;
        }
        // 最后一次遇到该颜色时，出栈
        if (i == end[cur_color]) active.pop();
    }

    cout << ans - 1 << endl;
}