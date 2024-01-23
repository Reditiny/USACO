#include <iostream>
#include <fstream>
#include <string>
#include <unordered_map>

using namespace std;

int main() {
    ifstream fin("citystate.in");
    ofstream fout("citystate.out");

    int N;
    fin >> N;

    unordered_map<string, int> city_map;

    for (int i = 0; i < N; i++) {
        string city, state;
        fin >> city >> state;

        // Two cities need to come from different states
        if (city.substr(0, 2) == state) {
            continue;
        }

        string key_str = city.substr(0, 2) + state;

        if (city_map.find(key_str) == city_map.end()) {
            city_map[key_str] = 0;
        }

        city_map[key_str] += 1;
    }

    int result = 0;

    for (const auto& pair_count : city_map) {
        string pair = pair_count.first;
        int count = pair_count.second;

        string reversed_pair = pair.substr(2) + pair.substr(0, 2);

        if (city_map.find(reversed_pair) != city_map.end()) {
            result += count * city_map[reversed_pair];
        }
    }

    fout << result / 2 << endl;
    fout.close();

    return 0;
}
