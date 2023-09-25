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

location_L = []
location_B = []

count_r = 0
for i in range(10):
    for j in range(10):
        if map[i][j] == "L":
            location_L = [i, j]
        elif map[i][j] == "B":
            location_B = [i, j]
        elif map[i][j] == "R":
            count_r += 1

diff_x = location_B[0] - location_L[0]
diff_y = location_B[1] - location_L[1]

if count_r <= min(abs(diff_x), abs(diff_y)):
    fout.write(f"{abs(diff_x) + abs(diff_y) - 1}")
    fout.close()
    exit(0)

for i in range(10):
    for j in range(10):
        if map[i][j] != "L":
            continue
        steps = 0
        q = [[i, j]]
        while len(q) > 0:
            q_size = len(q)
            for _ in range(q_size):
                cur = q.pop(0)
                if map[cur[0]][cur[1]] == "B":
                    fout.write(f"{steps - 1}")
                    fout.close()
                    exit(0)
                visited[cur[0]][cur[1]] = True
                for direction in DIRS:
                    x = cur[0] + direction[0]
                    y = cur[1] + direction[1]
                    if x < 0 or x == 10 or y < 0 or y == 10 or visited[x][y] or map[x][y] == "R":
                        continue
                    q.append([x, y])
            steps += 1
