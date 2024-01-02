#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>
#include <limits>

using namespace std;

int main() {
    ifstream fin("taming.in");
    ofstream fout("taming.out");

    int n;
    fin >> n;

    vector<int> logs(n);
    for (int i = 0; i < n; i++) {
        fin >> logs[i];
    }
    // 从后往前用已有信息填充 -1 处 如
    // -1 -1 0 -1 -1 3 -1 -1 -1 -1 2
    // -1 -1 0  1  2 3 -1 -1  0  1 2
    // 其中的 2 和 3 可以确定前面几位的数字
    int log_days = 0;
    bool flag = false;
    for (int i = n - 1; i >= 0; i--) {
        if (logs[i] != -1) {
            flag = true;
            log_days = logs[i];
        } else {
            if (flag && log_days > 0) {
                log_days--;
                logs[i] = log_days;
                if (log_days == 0) {
                    flag = false;
                }
            }
        }
    }
    // 判断记录是否合法  如 1 2 3 5 序列为不合法
    bool valid = true;
    for (int i = 1; i < n; i++) {
        if (logs[i - 1] == 0 && logs[i] > 1) {
            valid = false;
            break;
        }
        if (logs[i] > 0 && logs[i - 1] > 0 && logs[i] != logs[i - 1] + 1) {
            valid = false;
            break;
        }
    }

    if (!valid) {
        fout << -1 << endl;
    } else {
        // 找到最大最小值
        // 最大值时可以将所有的 -1 都写为 0
        // 最小值时只考虑前缀的 -1 如
        // -1 -1 -1 0 1 2 -1 0 1 -1
        //  0  1  2 0 1 2  3 0 1  2
        int max_days = 0, min_days = 0;
        for (int i = 0; i < n; i++) {
            if (logs[i] == 0) {
                max_days++;
                min_days++;
            }
            if (logs[i] == -1) {
                max_days++;
            }
        }
        if (logs[0] == -1) {
            min_days++;
        }
        fout << min_days << " " << max_days << endl;
    }

    fin.close();
    fout.close();

    return 0;
}
