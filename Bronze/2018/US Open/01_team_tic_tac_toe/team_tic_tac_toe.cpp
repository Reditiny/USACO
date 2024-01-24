#include <iostream>
#include <fstream>
#include <unordered_set>

using namespace std;
//  判断给定单牛是否获胜
bool singleWin(char target, char board[3][3]) {
    for (int i = 0; i < 3; i++) {
        // 行
        if (board[i][0] == target && board[i][1] == target && board[i][2] == target) {
            return true;
        }
        // 列
        if (board[0][i] == target && board[1][i] == target && board[2][i] == target) {
            return true;
        }
    }
    // 对角线
    if (board[0][0] == target && board[1][1] == target && board[2][2] == target) {
        return true;
    }
    if (board[0][2] == target && board[1][1] == target && board[2][0] == target) {
        return true;
    }
    return false;
}
// 双牛获胜排列
bool check_if_winners(char target1, char target2, char x, char y, char z) {
    unordered_set<char> s;
    s.insert(x);
    s.insert(y);
    s.insert(z);
    return s.size() == 2 && s.count(target1) && s.count(target2);
}
//  判断给定双牛是否获胜
bool team_win(char ch1, char ch2, char board[3][3]) {
    for (int i = 0; i < 3; i++) {
        // 行
        if (check_if_winners(ch1, ch2, board[i][0], board[i][1], board[i][2])) {
            return true;
        }
        // 列
        if (check_if_winners(ch1, ch2, board[0][i], board[1][i], board[2][i])) {
            return true;
        }
    }
    // 对角线
    if (check_if_winners(ch1, ch2, board[0][0], board[1][1], board[2][2])) {
        return true;
    }
    if (check_if_winners(ch1, ch2, board[0][2], board[1][1], board[2][0])) {
        return true;
    }
    return false;
}



int main() {
    ifstream fin("tttt.in");
    ofstream fout("tttt.out");

    char board[3][3];

    for (int i = 0; i < 3; i++) {
        fin >> board[i][0] >> board[i][1] >> board[i][2];
    }

    int single_count = 0;
    int team_count = 0;

    for (char c1 = 'A'; c1 <= 'Z'; c1++) {
        // 单牛获胜
        if (singleWin(c1, board)) {
            single_count++;
        }
        for (char c2 = 'Z'; c2 > c1; c2--) {
            // 双牛获胜  teamWin 中已实现 ZA 和 AZ 为相同组合
            if (team_win(c1, c2, board)) {
                team_count++;
            }
        }
    }

    fout << single_count << endl;
    fout << team_count << endl;

    fin.close();
    fout.close();

    return 0;
}
