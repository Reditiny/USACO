#include <iostream>
#include <fstream>
#include <vector>

using namespace std;

int main() {
    ifstream fin("billboard.in");
    ofstream fout("billboard.out");

    const int MAX_POS = 2000;
    vector<vector<bool>> visibility(MAX_POS, vector<bool>(MAX_POS, false));

    for (int i = 0; i < 3; ++i) {
        int x1, y1, x2, y2;
        fin >> x1 >> y1 >> x2 >> y2;
        x1 += MAX_POS / 2;
        y1 += MAX_POS / 2;
        x2 += MAX_POS / 2;
        y2 += MAX_POS / 2;
        for (int x = x1; x < x2; ++x) {
            for (int y = y1; y < y2; ++y) {
                // 0,1 广告牌 2 卡车
                visibility[x][y] = i < 2;
            }
        }
    }

    int area = 0;
    for (int i = 0; i < MAX_POS; ++i) {
        for (int j = 0; j < MAX_POS; ++j) {
            if (visibility[i][j]) {
                area++;
            }
        }
    }

    fout << area << endl;
    fout.close();

    return 0;
}
