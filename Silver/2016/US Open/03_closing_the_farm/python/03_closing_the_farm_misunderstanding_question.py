"""
ID: mck15821
LANG: PYTHON3
PROG: closing
"""
# http://www.usaco.org/index.php?page=viewproblem2&cpid=644
fin = open('closing.in', 'r')
fout = open("closing.out", "w")
N, M = map(int, fin.readline().strip().split())
graph = [[] for i in range(N)]
for _ in range(M):
    a, b = map(int, fin.readline().strip().split())
    graph[a - 1].append(b - 1)
    graph[b - 1].append(a - 1)


def dfs(visited, graph, cur):
    if visited[cur]:
        return
    visited[cur] = True
    for node in graph[cur]:
        dfs(visited, graph, node)


closed_ids = []
for _ in range(N):
    closed_ids.append(int(fin.readline().strip()) - 1)

for i in range(N):
    visited = [False for _ in range(N)]
    if i == 0:
        dfs(visited, graph, 0)
    else:
        closed_id = closed_ids[i - 1]
        visited[closed_id] = True
        if closed_id == 0:
            dfs(visited, graph, 1)
        else:
            dfs(visited, graph, 0)

    if sum(visited) != N:
        fout.write("NO\n")
    else:
        fout.write("YES\n")
fout.close()
