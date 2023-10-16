"""
ID: mck15821
LANG: PYTHON3
PROG: moocast
"""
# In this question cannot use memorized dfs
# The reason is there could be loop in the graph
# The new entry point could be a visited node in the cache, so use cache value could double count itself
# For example, consider loop 1->2->3->1. Say we start with 1 and get cache[1] = 3, cache[2] = 2, cache[3] = 1
# Then when we start with 2, we use the cache value 2, but in fact it could be 3.
# So it's safer to recalculate the path when there's a loop
# http://www.usaco.org/index.php?page=viewproblem2&cpid=668
fin = open('moocast.in', 'r')
fout = open("moocast.out", "w")
N = int(fin.readline().strip())
coordinates = []
radius = []
for _ in range(N):
    x, y, r = map(int, fin.readline().strip().split())
    coordinates.append([x, y])
    radius.append(r)


def can_reach(id1, id2):
    x1, y1 = coordinates[id1]
    x2, y2 = coordinates[id2]
    return (x1 - x2) ** 2 + (y1 - y2) ** 2 <= radius[id1] ** 2


# Build graph
graph = [[] for _ in range(N)]
for i in range(N):
    for j in range(i + 1, N):
        if can_reach(i, j):
            graph[i].append(j)
        if can_reach(j, i):
            graph[j].append(i)


def dfs(visited, graph, cur):
    visited[cur] = True
    total = 1
    for neighbor in graph[cur]:
        if not visited[neighbor]:
            total += dfs(visited, graph, neighbor)
    return total


max_reach = 0
for i in range(N):
    visited = [False for _ in range(N)]
    max_reach = max(max_reach, dfs(visited, graph, i))

fout.write(f"{max_reach}")
fout.close()
