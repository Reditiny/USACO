#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
    ifstream fin("sleepy.in");
    ofstream fout("sleepy.out");

    int n;
    fin >> n;

    vector<int> cows(n);
    for (int i = 0; i < n; i++) {
        fin >> cows[i];
    }
    // 对于当前序列后缀的升序序列可以不需要移动
    // 如  5 4 3 2 1 6 7 8 9 10
    // 1 6 7 8 9 10 本身已经时升序，前面的牛只需要移动进这个升序序列之中
    // 2 作为第一个不满足后缀升序的牛，它一定需要移动，且需要前面的牛移动完 2 才能移动
    // 因此需要移动的牛的个数是 n - 后缀升序牛的个数
    int end_order_count = 1;
    int last_number = cows[n - 1];
    for (int i = n - 2; i >= 0; i--) {
        if (cows[i] < last_number) {
            // 记录后缀升序牛的个数
            end_order_count++;
            last_number = cows[i];
        } else {
            // 找到第一个不满足后缀升序的牛
            break;
        }
    }

    fout << n - end_order_count << endl;

    fin.close();
    fout.close();

    return 0;
}
