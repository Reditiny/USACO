#include <iostream>
#include <fstream>
#include <vector>

using namespace std;


// 各个手势的可能对应含义
char rules[6][3] = {{'H', 'P', 'S'}, {'P', 'S', 'H'}, {'S', 'H', 'P'},{'H', 'S', 'P'}, {'P', 'H', 'S'}, {'S', 'P', 'H'}};
int gestures[101][2];
// 检查当前规则下，当前手势,第一头牛是否赢
int check(char rule[3], int gesture[2]) {
    char cow1 = rule[gesture[0]];
    char cow2 = rule[gesture[1]];
    if ((cow1 == 'H' && cow2 == 'S') || (cow1 == 'P' && cow2 == 'H') || (cow1 == 'S' && cow2 == 'P')) {
        return 1;
    }
    return 0;
}

int main() {
    freopen("hps.in", "r", stdin);
    freopen("hps.out", "w", stdout);
    int N;
    cin >> N;
    for (int i = 0; i < N; i++) {
        cin >> gestures[i][0] >> gestures[i][1];
        gestures[i][0]--;
        gestures[i][1]--;
    }
    int ans = 0;
    for (int i = 0; i < 6; i++) {
        int curAns = 0;
        for (int j = 0; j < N; j++) {
            curAns += check(rules[i], gestures[j]);
        }
        ans = max(ans, curAns);
    }
    cout << ans << endl;
    return 0;
}
