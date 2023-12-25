#include <iostream>
#include <vector>
#include <set>
using namespace std;

struct haybale{
    int flavor, spiciness;
};

int main() {
    freopen("hayfeast.in", "r", stdin);
    freopen("hayfeast.out", "w", stdout);

    int n;
    long long m;
    cin >> n >> m;
    vector<haybale> haybales(n);
    for (haybale &i : haybales) { cin >> i.flavor >> i.spiciness; }

    int min_spiciness = INT32_MAX;
    long long sum_flavor = 0;
    // 使用 multiset 保存窗口内的 spiciness
    // multiset 底层实现为红黑树，可以自动排序，且可以保存重复元素
    multiset<int> spices;
    // 滑动窗口，与双指针类似
    // left 和 right 分别表示窗口的左右边界，均从 0 开始向右移动
    // 当窗口内的 flavor 和小于 m 时，right 向右移动，增加窗口内的 flavor 和
    // 当窗口内的 flavor 和大于等于 m 时，计算辣度，同时 left 向右移动，减少窗口内的 flavor 和
    for (int left = 0, right = 0; right < n; left++) {
        // right 向右移动，增加窗口内的 flavor 和
        while (right < n && sum_flavor < m) {
            // 使用 multiset 保存窗口内的 spiciness
            // multiset 会自动排序，且可以保存重复元素
            sum_flavor += haybales[right].flavor;
            spices.insert(haybales[right].spiciness);
            right++;
        }
        // 窗口内的 flavor 和大于等于 m 时，计算辣度
        if (sum_flavor >= m) {
            min_spiciness = min(min_spiciness, *spices.rbegin());
        }
        // left 向右移动，减少窗口内的 flavor 和
        sum_flavor -= haybales[left].flavor;
        spices.erase(spices.find(haybales[left].spiciness));
    }

    cout << min_spiciness << endl;
}