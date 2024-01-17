#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

int main() {
    vector<int> nums(7);

    for (int i = 0; i < 7; i++) {
        cin >> nums[i];
    }

    sort(nums.begin(), nums.end());
    // 最小的两个数一定是 a 和 b 最大的数一定是 a+b+c
    int a = nums[0];
    int b = nums[1];
    int c = nums[6] - a - b;

    cout << a << " " << b << " " << c << endl;

    return 0;
}
