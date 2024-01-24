#include <iostream>
#include <fstream>
#include <vector>
#include <unordered_map>
#include <unordered_set>

using namespace std;


/**
 * 判断 vector<string> spotty 中的字符串是否都不在 unordered_map<string, int> plains 中
 */
bool no_contains(const vector<string>& spotty, unordered_map<string, int>& plains) {
    for (const string& x : spotty) {
        if (plains.find(x) != plains.end()) {
            return false;
        }
    }
    return true;
}


int main() {
    ifstream fin("cownomics.in");
    ofstream fout("cownomics.out");

    int cows, length;
    fin >> cows >> length;

    vector<vector<char>> spotty(cows, vector<char>(length));
    vector<vector<char>> plain(cows, vector<char>(length));

    for (int x = 0; x < cows; x++) {
        string genomics;
        fin.ignore();
        fin >> genomics;
        for (int y = 0; y < length; y++) {
            spotty[x][y] = genomics[y];
        }
    }

    for (int x = 0; x < cows; x++) {
        string genomics;
        fin.ignore();
        fin >> genomics;
        for (int y = 0; y < length; y++) {
            plain[x][y] = genomics[y];
        }
    }

    unordered_map<string, int> plains;
    vector<string> spottys;
    int count = 0;

    for (int x = 0; x < length; x++) {
        for (int y = x + 1; y < length; y++) {
            for (int z = y + 1; z < length; z++) {
                plains.clear();
                spottys.clear();
                for (int a = 0; a < cows; a++) {
                    string plain_ss = string(1, plain[a][x]) + string(1, plain[a][y]) + string(1, plain[a][z]);
                    string spotty_ss = string(1, spotty[a][x]) + string(1, spotty[a][y]) + string(1, spotty[a][z]);
                    plains[plain_ss] = 1;
                    spottys.push_back(spotty_ss);
                }
                if (no_contains(spottys, plains)) {
                    count++;
                }
            }
        }
    }

    fout << count << endl;

    fin.close();
    fout.close();

    return 0;
}
