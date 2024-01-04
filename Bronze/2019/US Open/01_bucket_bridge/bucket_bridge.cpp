#include <iostream>
#include <fstream>
#include <cmath>

using namespace std;



int main() {
    ifstream fin("buckets.in");
    ofstream fout("buckets.out");

    char farm[10][10];
    int l_x, l_y, b_x, b_y, r_x, r_y;

    for (int i = 0; i < 10; i++) {
        string line;
        fin >> line;
        for (int j = 0; j < 10; j++) {
            char c = line[j];
            farm[i][j] = c;
            if (c == 'B') {
                b_x = i;
                b_y = j;
            } else if (c == 'L') {
                l_x = i;
                l_y = j;
            } else if (c == 'R') {
                r_x = i;
                r_y = j;
            }
        }
    }
    // 当 R 挡在 B 和 L 之间时，需要多走两步绕开
    // L12345B   L12R67B
    //             345
    // 其他情况就是 LB 水平和数值距离只和
    int delta_x = abs(l_x - b_x);
    int delta_y = abs(l_y - b_y);
    // 由于是计算间隔所以是距离-1
    int ans = delta_y + delta_x - 1;
    if (((l_x == b_x && b_x == r_x) && ((r_y - b_y) * (r_y - l_y) < 0))
        || ((l_y == b_y && b_y == r_y) && ((r_x - b_x) * (r_x - l_x) < 0))) {
        ans += 2;
    }

    fout << ans << endl;

    fin.close();
    fout.close();

    return 0;
}
