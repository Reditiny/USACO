#include <iostream>
#include <fstream>
#include <vector>

using namespace std;

int main() {
    ifstream fin("circlecross.in");
    ofstream fout("circlecross.out");

    string s;
    fin >> s;

    vector<char> char_stack;
    int visited[26] = {0};
    int ans = 0;

    for (int i = 0; i < 52; i++) {
        char ch = s[i];
        int ch_index = ch - 'A';
        if (visited[ch_index] == 0) {
            visited[ch_index] = 1;
            char_stack.push_back(ch);
        } else {
            int start_pos = 0;
            while (start_pos < char_stack.size()) {
                if (char_stack[start_pos] == ch) {
                    break;
                }
                start_pos++;
            }
            ans += char_stack.size() - start_pos - 1;
            char_stack.erase(char_stack.begin() + start_pos);
        }
    }

    fout << ans << endl;
    fin.close();
    fout.close();

    return 0;
}
