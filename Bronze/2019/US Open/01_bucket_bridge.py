"""
ID: mck15821
LANG: PYTHON3
PROG: buckets
"""
fin = open('buckets.in', 'r')
fout = open("buckets.out", "w")

map = []
for _ in range(10):
    map.append(fin.readline().strip())

DIRS = [[-1, 0], [1, 0], [0, -1], [0, 1]]
visited = [[False for _ in range(10)] for _ in range(10)]


def dfs(i, j, steps):
    if map[i][j] == "L":
        return steps
    visited[i][j] = True
    for p, q in DIRS:
        if 0 <= i + p < 10 and 0 <= j + q < 10:
            if map[i + p][j + q] == "R" or visited[i + p][j + q]:
                continue
            steps = min(steps, dfs(i + p, j + q, steps + 1))
    return steps


for i in range(10):
    for j in range(10):
        if map[i][j] == "B":
            fout.write(f"{dfs(i, j, 100)}\n")
            fout.close()
