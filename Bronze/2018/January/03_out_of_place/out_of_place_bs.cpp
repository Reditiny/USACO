#include <iostream>
#include <fstream>
#include <vector>
#include <set>

using namespace std;

int binary_search(const vector<int>& target_list, int value, int direction, int left, int right) {
    while (left + 1 < right) {
        int mid = (left + right) / 2;
        if (target_list[mid] == value) {
            if (direction > 0) {
                right = mid;
            } else {
                left = mid;
            }
        } else if (target_list[mid] < value) {
            left = mid;
        } else {
            right = mid;
        }
    }

    if (direction < 0) {
        if (target_list[right] <= value) {
            return right + 1;
        } else if (target_list[left] <= value) {
            return right;
        }
        return left;
    }

    if (target_list[left] >= value) {
        return left - 1;
    }

    if (target_list[right] >= value) {
        return left;
    }

    return right;
}

int main() {
    ifstream fin("outofplace.in");
    ofstream fout("outofplace.out");

    int N;
    fin >> N;

    vector<int> places(N);
    for (int i = 0; i < N; i++) {
        fin >> places[i];
    }

    int bessie;
    int direction;

    if (places[0] > places[1]) {
        bessie = 0;
        direction = 1;
    } else if (places[N - 2] > places[N - 1]) {
        bessie = N - 1;
        direction = -1;
    } else {
        for (int i = 1; i < N; i++) {
            if (places[i - 1] > places[i]) {
                if (places[i] < places[i - 2]) {
                    bessie = i;
                    direction = -1;
                } else if (places[i - 1] > places[i + 1]) {
                    bessie = i - 1;
                    direction = 1;
                }
            }
        }
    }

    int swaps;
    set<int> uniqueElements;

    if (direction > 0) {
        int new_location = binary_search(places, places[bessie], direction, bessie + 1, N - 1);
        uniqueElements.insert(places.begin() + bessie + 1, places.begin() + new_location + 1);
        swaps = uniqueElements.size();
    } else {
        int new_location = binary_search(places, places[bessie], direction, 0, bessie - 1);
        uniqueElements.insert(places.begin() + new_location, places.begin() + bessie);
        swaps = uniqueElements.size();
    }

    fout << swaps << endl;
    fout.close();

    return 0;
}
