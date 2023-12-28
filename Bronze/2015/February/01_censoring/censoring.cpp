#include <iostream>
using namespace std;


int main() {
    freopen("censor.in", "r", stdin);
    freopen("censor.out", "w", stdout);
    string S;
    string T;
    cin >> S >> T;

    string ans;
    // 将文本中字符依次放入ans中，每次都判断ans是否以target结尾
    for (int i = 0; i < S.size(); i++) {
        ans += S[i];
        if (ans.size() > T.size() &&
            ans.substr(ans.size() - T.size()) == T) {
            ans.resize(ans.size() - T.size());
        }
    }
    cout << ans << endl;
}