"""
ID: mck15821
LANG: PYTHON3
PROG: buckets
"""
fin = open('factory.in', 'r')
fout = open("factory.out", "w")
# http://www.usaco.org/index.php?page=viewproblem2&cpid=940

N = int(fin.readline().strip())
graph = [[] for _ in range(N)]
for _ in range(N - 1):
    f, t = map(int, fin.readline().strip().split())
    graph[t - 1].append(f - 1) # build path reversely

# find if one station can go to all stations
for i in range(N):
    q = [i]
    visited = [False for _ in range(N)]
    while len(q) > 0:
        cur = q.pop(0)
        if visited[cur] is True:
            continue
        visited[cur] = True
        for next_station in graph[cur]:
            q.append(next_station)
    if sum(visited) == N:
        fout.write(f"{i + 1}")
        exit(0)

fout.write("-1")
fout.close()
