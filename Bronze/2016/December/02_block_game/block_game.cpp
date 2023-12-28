#include <fstream>
#include <iostream>
#include <string>
#include <vector>
using namespace std;

int main() {
    freopen("blocks.in", "r", stdin);
    freopen("blocks.out", "w", stdout);
    // 记录正面单词 反面单词 最终字母数
    int face_letter_count[26] = {0};
    int back_letter_count[26] = {0};
    int letter_count_ans[26] = {0};

    int n;
    cin >> n;

    for (int i = 0; i < n; i++) {
        string word[2];
        cin >> word[0] >> word[1];
        // 每块板子正反两个单词 记录每块板子每个字符出现的最大次数
        for (int j = 0; j < word[0].length(); j++) {
            face_letter_count[word[0][j] - 'a']++;
        }
        for (int j = 0; j < word[1].length(); j++) {
            back_letter_count[word[1][j] - 'a']++;
        }
        for (int j = 0; j < 26; j++) {
            letter_count_ans[j] += max(face_letter_count[j], back_letter_count[j]);
            face_letter_count[j] = 0;
            back_letter_count[j] = 0;
        }
    }
    for (int i = 0; i < 26; i++) {
        cout << letter_count_ans[i] << endl;
    }
}