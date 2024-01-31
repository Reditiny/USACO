#include <iostream>
#include <fstream>
#include <vector>

using namespace std;

vector<vector<int>> boots;
vector<int> snow_depth;
vector<vector<int>> visited;
int n, b;

int main() {
    ifstream fin("snowboots.in");
    ofstream fout("snowboots.out");

    fin >> n >> b;
    snow_depth.resize(n);
    visited.resize(n, vector<int>(b, 0));

    for (int i = 0; i < n; i++) {
        fin >> snow_depth[i];
    }

    for (int i = 0; i < b; i++) {
        vector<int> boot(2);
        fin >> boot[0] >> boot[1];
        boots.push_back(boot);
    }

    visited[0][0] = 1;

    // 遍历每个鞋和每个砖块
    for (int boot = 0; boot < b; boot++) {
        int top_depth = boots[boot][0];
        int top_size = boots[boot][1];

        for (int location = 0; location < n; location++) {
            // 当前的鞋子无法站住当前的砖块上
            if (snow_depth[location] > top_depth) {
                visited[location][boot] = -1;
                continue;
            }

            // 穿着当前的鞋子，前一块可能的砖块
            for (int prior_location = 0; prior_location < location; prior_location++) {
                if (visited[prior_location][boot] == 1 && prior_location + top_size >= location) {
                    visited[location][boot] = 1;
                }
            }

            // 当前的砖块，前一双可能的鞋子
            for (int prior_boot = 0; prior_boot < boot; prior_boot++) {
                if (visited[location][prior_boot] == 1) {
                    visited[location][boot] = 1;
                }
            }
        }

        if (visited[n - 1][boot] == 1) {
            fout << boot << endl;
            break;
        }
    }

    fin.close();
    fout.close();

    return 0;
}
