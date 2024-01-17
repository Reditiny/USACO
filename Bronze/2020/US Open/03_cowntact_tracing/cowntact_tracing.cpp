#include <iostream>
#include <fstream>
#include <vector>
#include <unordered_set>

using namespace std;

const int MAX_T = 250;
const int MAX_N = 100;

bool cow_ends_infected[MAX_N + 1];
int n, cow_x[MAX_T + 1], cow_y[MAX_T + 1];
// 判断 patient_zero 为传播起点握手 k 次 是否能满足最终感染结果
bool consistent_with_data(int patient_zero, int k) {
    bool infected[MAX_N + 1] = {false};
    int num_handshakes[MAX_N + 1] = {0};
    infected[patient_zero] = true;
    for (int t = 0; t <= MAX_T; t++) {
        int x = cow_x[t];
        int y = cow_y[t];
        if (x > 0) {
            if (infected[x]) {
                num_handshakes[x]++;
            }
            if (infected[y]) {
                num_handshakes[y]++;
            }
            if (num_handshakes[x] <= k && infected[x]) {
                infected[y] = true;
            }
            if (num_handshakes[y] <= k && infected[y]) {
                infected[x] = true;
            }
        }
    }
    for (int i = 1; i <= n; i++) {
        if (infected[i] != cow_ends_infected[i]) {
            return false;
        }
    }
    return true;
}

int main() {
    ifstream fin("tracing.in");
    ofstream fout("tracing.out");

    fin >> n;
    int t;
    fin >> t;
    // 记录最终感染结果
    string s;
    fin >> s;
    // 记录接触信息
    for (int x = 1; x <= n; x++) {
        cow_ends_infected[x] = (s[x - 1] == '1');
    }
    // 枚举所有牛作为 patient zero 以及握手次数 k
    unordered_set<int> candidate_patient_zero;
    int lower_k = MAX_T + 1;
    int upper_k = 0;

    for (int i = 0; i < t; i++) {
        int time, x, y;
        fin >> time >> x >> y;
        cow_x[time] = x;
        cow_y[time] = y;
    }

    for (int i = 1; i <= n; i++) {
        // k 可以取 MAX_T + 1（251）时说明最终为 Infinity
        for (int k = 0; k <= MAX_T + 1; k++) {
            if (consistent_with_data(i, k)) {
                lower_k = min(lower_k, k);
                upper_k = max(upper_k, k);
                candidate_patient_zero.insert(i);
            }
        }
    }

    fout << candidate_patient_zero.size() << " " << lower_k << " ";

    if (upper_k == MAX_T + 1) {
        fout << "Infinity" << endl;
    } else {
        fout << upper_k << endl;
    }

    fin.close();
    fout.close();

    return 0;
}
