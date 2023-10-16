"""
ID: mck15821
LANG: PYTHON3
PROG: closing
"""
# http://www.usaco.org/index.php?page=viewproblem2&cpid=644
fin = open('closing.in', 'r')
fout = open("closing.out", "w")
N, M = map(int, fin.readline().strip().split())
graph = [set() for i in range(N)]
closed = [False for i in range(N)]
for _ in range(M):
    a, b = map(int, fin.readline().strip().split())
    graph[a - 1].add(b - 1)
    graph[b - 1].add(a - 1)


def dfs(visited, graph, cur):
    if visited[cur]:
        return
    visited[cur] = True
    for node in graph[cur]:
        dfs(visited, graph, node)


closed_ids = []
for _ in range(N):
    closed_ids.append(int(fin.readline().strip()) - 1)

visited = closed.copy()
dfs(visited, graph, 0)
if sum(visited) != N:
    fout.write("NO\n")
else:
    fout.write("YES\n")

for i in range(N - 1):
    closed_id = closed_ids[i]
    closed[closed_id] = True
    visited = closed.copy()
    # always start with the last barn
    dfs(visited, graph, closed_ids[-1])

    if sum(visited) != N:
        fout.write("NO\n")
    else:
        fout.write("YES\n")
fout.close()
