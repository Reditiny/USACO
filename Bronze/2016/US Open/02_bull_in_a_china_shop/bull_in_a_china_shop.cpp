#include <iostream>
#include <fstream>
#include <vector>

using namespace std;

const int MAX_N = 500;

bool pieces[MAX_N + 1][MAX_N][MAX_N];
int n;

bool check(int piece, int x, int y) {
    return x >= 0 && x < n && y >= 0 && y < n && pieces[piece][x][y];
}

int main() {
    freopen("bcs.in", "r", stdin);
    freopen("bcs.out", "w", stdout);

    cin >> n;
    int k;
    cin >> k;

    vector<vector<int>> s(k + 1, vector<int>(4, 0));

    for (int row = 0; row <= k; row++) {
        int left = n - 1;
        int right = 0;
        int top = n - 1;
        int bottom = 0;

        for (int col = 0; col < n; col++) {
            string str;
            cin >> str;

            for (int l = 0; l < n; l++) {
                char c = str[l];
                pieces[row][col][l] = (c == '#');

                if (pieces[row][col][l]) {
                    bottom = max(bottom, col);
                    top = min(top, col);
                    right = max(right, l);
                    left = min(left, l);
                }
            }
        }

        s[row][0] = left;
        s[row][1] = right;
        s[row][2] = top;
        s[row][3] = bottom;
    }
    // 逻辑同 JAVA 代码
    for (int i = 1; i <= k; i++) {
        for (int j = i + 1; j <= k; j++) {
            for (int iDetalRow = s[i][3] - n + 1; iDetalRow <= s[i][2]; iDetalRow++) {
                for (int iDetalCol = s[i][1] - n + 1; iDetalCol <= s[i][0]; iDetalCol++) {
                    for (int jDetalRow = s[j][3] - n + 1; jDetalRow <= s[j][2]; jDetalRow++) {
                        for (int jDetalCol = s[j][1] - n + 1; jDetalCol <= s[j][0]; jDetalCol++) {
                            bool good = true;

                            for (int x = 0; x < n; x++) {
                                for (int y = 0; y < n; y++) {
                                    bool iPiece = check(i, x + iDetalRow, y + iDetalCol);
                                    bool jPiece = check(j, x + jDetalRow, y + jDetalCol);

                                    if (iPiece && jPiece) {
                                        good = false;
                                        break;
                                    }

                                    if (pieces[0][x][y] != (iPiece || jPiece)) {
                                        good = false;
                                        break;
                                    }
                                }

                                if (!good) {
                                    break;
                                }
                            }

                            if (good) {
                                cout << i << " " << j << endl;
                            }
                        }
                    }
                }
            }
        }
    }

    return 0;
}
