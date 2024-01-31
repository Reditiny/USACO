#include <iostream>
#include <fstream>
#include <vector>
#include <map>
#include <algorithm>

using namespace std;

int n, m;
vector<vector<int>> graph;
vector<bool> visited;
vector<bool> closed;
map<int, vector<int>> region_id_to_farm;
map<int, int> farm_to_region_id;

void dfs(int current, int region_id);

int main() {
    ifstream fin("closing.in");
    ofstream fout("closing.out");

    fin >> n >> m;

    graph.resize(n);
    visited.resize(n, false);
    closed.resize(n, false);

    for (int i = 0; i < m; i++) {
        int a, b;
        fin >> a >> b;
        a--, b--; // Adjusting to 0-based indexing
        graph[a].push_back(b);
        graph[b].push_back(a);
    }

    int region_id = 0;

    for (int i = 0; i < n; i++) {
        if (!visited[i]) {
            region_id_to_farm[region_id] = vector<int>();
            dfs(i, region_id);
            region_id++;
        }
    }

    if (region_id == 1) {
        fout << "YES" << endl;
    } else {
        fout << "NO" << endl;
    }

    for (int i = 0; i < n - 1; i++) {
        int closed_farm;
        fin >> closed_farm;
        closed_farm--; // Adjusting to 0-based indexing
        int farm_region_id = farm_to_region_id[closed_farm];
        vector<int> farms_in_same_region = region_id_to_farm[farm_region_id];
        closed[closed_farm] = true;
        visited = closed;

        region_id_to_farm.erase(farm_region_id);

        for (int farm : farms_in_same_region) {
            if (!visited[farm]) {
                region_id_to_farm[region_id] = vector<int>();
                dfs(farm, region_id);
                region_id++;
            }
        }

        if (region_id_to_farm.size() == 1) {
            fout << "YES" << endl;
        } else {
            fout << "NO" << endl;
        }
    }

    fout.close();

    return 0;
}

void dfs(int current, int region_id) {
    if (visited[current]) {
        return;
    }

    visited[current] = true;
    farm_to_region_id[current] = region_id;
    region_id_to_farm[region_id].push_back(current);

    for (int next : graph[current]) {
        if (!visited[next]) {
            dfs(next, region_id);
        }
    }
}
