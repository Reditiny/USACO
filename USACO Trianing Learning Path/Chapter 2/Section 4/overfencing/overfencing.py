"""
ID: mck15821
LANG: PYTHON3
PROG: maze1
"""
fin = open('maze1.in', 'r')
fout = open("maze1.out", "w")

W, H = map(int, fin.readline().strip().split())
maze = []
for i in range(2 * H + 1):
    maze.append(list(fin.readline().strip()))

exits = []
for i in range(H):
    if maze[2 * i + 1][0] == " ":
        exits.append((2 * i + 1, 0))
    if maze[2 * i + 1][2 * W] == " ":
        exits.append((2 * i + 1, 2 * W))

for i in range(W):
    if maze[0][2 * i + 1] == " ":
        exits.append((0, 2 * i + 1))
    if maze[2 * H][2 * i + 1] == " ":
        exits.append((2 * H, 2 * i + 1))

dist = [[-1 for j in range(W)] for i in range(H)]
for i in range(len(exits)):
    dist[exits[i][0] // 2][exits[i][1] // 2] = 0

def bfs(x, y):
    q = [(x, y)]
    while len(q) > 0:
        cur = q.pop(0)
        for i in range(4):
            nx = cur[0] + dir_x[i]
            ny = cur[1] + dir_y[i]
            if nx < 0 or nx >= H or ny < 0 or ny >= W or dist[nx][ny] != -1 or maze[2 * nx + 1][2 * ny + 1] == "-":
                continue
            dist[nx][ny] = dist[cur[0]][cur[1]] + 1
            q.append((nx, ny))

dir_x = [0, 1, 0, -1]
dir_y = [-1, 0, 1, 0]
for i in range(H):
    for j in range(W):
        if dist[i][j] == 0:
            bfs(i, j)

best = 0
for i in range(H):
    for j in range(W):
        best = max(best, dist[i][j])
fout.write(f"{best + 1}\n")

fout.close()
