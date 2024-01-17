#include <iostream>
#include <fstream>
#include <vector>

using namespace std;

int n, k;
vector<string> words;

int main() {
    ifstream fin("word.in");
    ofstream fout("word.out");

    fin >> n >> k;

    words.resize(n);
    for (int i = 0; i < n; i++) {
        fin >> words[i];
    }
    // 因为每次循环不确定要输出多少个字符，所以最后并没有 i++，而是放到循环体内去改变
    for (int i = 0; i < n; ) {
        int j = i;
        int len = 0;
        // 从 i 开始，尽可能多的输出单词，直到长度超过 k
        while (j < n && len + words[j].length() <= k) {
            len += words[j].length();
            j++;
        }
        for (int l = i; l < j; l++) {
            fout << words[l];
            if (l != j - 1) {
                fout << " ";
            }
        }
        fout << endl;
        i = j;
    }

    fin.close();
    fout.close();

    return 0;
}
