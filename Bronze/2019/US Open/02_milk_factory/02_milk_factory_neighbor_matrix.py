"""
ID: mck15821
LANG: PYTHON3
PROG: buckets
"""
fin = open('factory.in', 'r')
fout = open("factory.out", "w")

N = int(fin.readline().strip())

neighbor_matrix = [[-1 for i in range(N)] for j in range(N)]

graph = [[] for _ in range(N)]
for _ in range(N - 1):
    f, t = map(int, fin.readline().strip().split())
    graph[t - 1].append(f - 1) # build path reversely

# find if one station can go to all stations
for i in range(N):
    q = [i]
    while len(q) > 0:
        cur = q.pop(0)
        if neighbor_matrix[i][cur] != -1:
            continue
        neighbor_matrix[i][cur] = 1
        for next_station in graph[cur]:
            q.append(next_station)

    if sum(neighbor_matrix[i]) == N:
        fout.write(f"{i + 1}")
        exit(0)
    for j in range(N):
        if neighbor_matrix[i][j] == -1:
            neighbor_matrix[i][j] = 0

fout.write("-1")
fout.close()
