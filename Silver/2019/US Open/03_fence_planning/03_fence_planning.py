"""
ID: mck15821
LANG: PYTHON3
PROG: fenceplan
"""
# http://www.usaco.org/index.php?page=viewproblem2&cpid=944
import sys
sys.setrecursionlimit(10**5)

fin = open('fenceplan.in', 'r')
fout = open("fenceplan.out", "w")

N, M = map(int, fin.readline().strip().split())
coordinates = []
for _ in range(N):
    coordinates.append(list(map(int, fin.readline().strip().split())))

# Build graph
graph = [set() for _ in range(N)]
for _ in range(M):
    a, b = map(int, fin.readline().strip().split())
    graph[a - 1].add(b - 1)
    graph[b - 1].add(a - 1)


def dfs(visited, graph, cur, min_coordinates, max_coordinates):
    if visited[cur]:
        return
    visited[cur] = True
    min_coordinates[0] = min(min_coordinates[0], coordinates[cur][0])
    max_coordinates[0] = max(max_coordinates[0], coordinates[cur][0])
    min_coordinates[1] = min(min_coordinates[1], coordinates[cur][1])
    max_coordinates[1] = max(max_coordinates[1], coordinates[cur][1])
    for node in graph[cur]:
        dfs(visited, graph, node, min_coordinates, max_coordinates)


min_area = float("inf")
visited = [False for _ in range(N)]
for i in range(N):
    if visited[i]:
        continue
    min_coordinates = [10 ** 8, 10 ** 8]
    max_coordinates = [0, 0]
    dfs(visited, graph, i, min_coordinates, max_coordinates)
    min_area = min(min_area, (max_coordinates[1] - min_coordinates[1]) * 2 + (max_coordinates[0] - min_coordinates[0]) * 2)

fout.write(f"{min_area}")
fout.close()
