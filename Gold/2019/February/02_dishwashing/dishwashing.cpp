#include <iostream>
#include <vector>
#include <deque>
#include <algorithm>
using namespace std;
vector<int> order;

// 返回是否可以洗掉 [0, end_index) 的盘子
bool prefix_washable (int end_index) {
    // 需要洗的盘子 盘子必须从小到大洗
    // 这里从大到小排序，每次只需要从后完全弹出元素即可实现从小到大洗
    vector<int> not_washed;
    for (int i = 0; i < end_index; i++) { not_washed.push_back(order[i]); }
    sort(not_washed.begin(), not_washed.end(), greater<int>());
    // 每一个 vector<int> 表示一个肥皂堆
    // deque 数据结构可以从首尾两端进行插入和弹出
    deque<vector<int>> soapy_stacks;
    for (int i = 0; i < end_index; i++) {
        int plate = order[i];
        // 当前盘子只能放在堆顶上的盘子编号比当前盘子编号大的堆
        // 二分查找第一个可以放的堆
        int l = -1, r = soapy_stacks.size();
        while (l < r - 1) {
            int mid = (l + r) / 2;
            if (soapy_stacks[mid].back() > plate) {
                r = mid;
            } else {
                l = mid;
            }
        }
        if (r == soapy_stacks.size()) {
            // 没有堆满足条件，新放一个堆
            soapy_stacks.push_back({plate});
        } else {
            soapy_stacks[r].push_back(plate);
        }
        // 每次洗都是拿最前面那堆盘子的最上面一个
        while (!soapy_stacks.empty() && soapy_stacks.front().back() == not_washed.back()) {
            soapy_stacks.front().pop_back();
            not_washed.pop_back();
            // 第一堆拿空了之后，把这堆移除
            if (soapy_stacks.front().empty()) { soapy_stacks.pop_front(); }
        }
    }
    // 检查是否可以全部洗完
    return not_washed.empty();
};

int main() {
    freopen("dishes.in", "r", stdin);
    freopen("dishes.out", "w", stdout);
    int n;
    cin >> n;
    order.resize(n);
    for (int i = 0; i < n; i++) { cin >> order[i]; }
    // 二分查找最长的前缀
    int l = 0, r = n + 1;
    while (l < r - 1) {
        int mid = (l + r) / 2;
        if (prefix_washable(mid)) {
            l = mid;
        } else {
            r = mid;
        }
    }
    cout << l << endl;
    return 0;
}