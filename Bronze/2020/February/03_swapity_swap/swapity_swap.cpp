#include <iostream>
#include <fstream>
#include <vector>

using namespace std;

int n, k, a1, a2, b1, b2;
vector<int> cows;
// 将序列中的 start 到 end 之间的元素进行反转
void reverse(int start, int end){
    int temp;
    while (start < end) {
        temp = cows[start];
        cows[start] = cows[end];
        cows[end] = temp;
        start++;
        end--;
    }
}
// 进行一次操作
void exercise() {
    reverse( a1, a2 );
    reverse( b1, b2);
}
// 判断当前序列是否与初始序列相同
bool isSame() {
    for (int i = 0; i < n; i++) {
        if (cows[i] != i + 1) {
            return false;
        }
    }
    return true;
}

int main() {
    ifstream fin("swap.in");
    ofstream fout("swap.out");

    fin >> n >> k;
    cows.resize(n);
    for (int i = 0; i < n; i++) {
        cows[i] = i + 1;
    }

    fin >> a1 >> a2;
    fin >> b1 >> b2;
    a1--, a2--, b1--, b2--;
    // 寻找周期 即经过 cycle 次操作后，序列回到原来的状态
    int cycle = 0;
    while (true) {
        exercise();
        cycle++;
        if (isSame()) {
            break;
        }
    }
    // 最后仅需要进行 k % cycle 次操作即可
    k %= cycle;

    for (int i = 0; i < k; i++) {
        exercise();
    }

    for (int i = 0; i < n; i++) {
        fout << cows[i] << endl;
    }

    fin.close();
    fout.close();

    return 0;
}
