#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
    ifstream fin("traffic.in");
    ofstream fout("traffic.out");

    int n;
    fin >> n;

    vector<string> type_list(n);
    vector<int> min_list(n);
    vector<int> max_list(n);

    for (int i = 0; i < n; i++) {
        fin >> type_list[i] >> min_list[i] >> max_list[i];
    }
    int min_traffic = 0, max_traffic = 1000;
    // 从前往后依次合并每个Sensor的数据
    for (int i = n - 1; i >= 0; i--) {
        if (type_list[i] == "none") {
            min_traffic = max(min_traffic, min_list[i]);
            max_traffic = min(max_traffic, max_list[i]);
        } else if (type_list[i] == "off") {
            min_traffic += min_list[i];
            max_traffic += max_list[i];
        } else if (type_list[i] == "on") {
            max_traffic -= min_list[i];
            min_traffic -= max_list[i];
        }
        min_traffic = max(0, min_traffic);
    }
    fout << min_traffic << " " << max_traffic << endl;

    // 从后往前依次合并每个Sensor的数据   注意从后往前时 off 和 on 的逻辑应该相反
    min_traffic = 0, max_traffic = 1000;
    for (int i = 0; i < n; i++) {
        if (type_list[i] == "none") {
            min_traffic = max(min_traffic, min_list[i]);
            max_traffic = min(max_traffic, max_list[i]);
        } else if (type_list[i] == "on") {
            min_traffic += min_list[i];
            max_traffic += max_list[i];
        } else if (type_list[i] == "off") {
            max_traffic -= min_list[i];
            min_traffic -= max_list[i];
        }
        min_traffic = max(0, min_traffic);
    }
    fout << min_traffic << " " << max_traffic << endl;

    fin.close();
    fout.close();

    return 0;
}
