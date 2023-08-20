"""
ID: mck15821
LANG: PYTHON3
PROG: ttwo
"""
fin = open('ttwo.in', 'r')
fout = open("ttwo.out", "w")

grid = []
for i in range(10):
    grid.append(list(fin.readline().strip()))


def move(x, y, d):
    # north: 0, east: 1, south: 2, west: 3
    if d == 0:
        if x == 0 or grid[x - 1][y] == "*":
            return x, y, 1
        else:
            return x - 1, y, d
    elif d == 1:
        if y == 9 or grid[x][y + 1] == "*":
            return x, y, 2
        else:
            return x, y + 1, d
    elif d == 2:
        if x == 9 or grid[x + 1][y] == "*":
            return x, y, 3
        else:
            return x + 1, y, d
    else:
        if y == 0 or grid[x][y - 1] == "*":
            return x, y, 0
        else:
            return x, y - 1, d


def move2(x, y, d):
    dir_x = [0, 1, 0, -1]
    dir_y = [-1, 0, 1, 0]
    nx = x + dir_x[d]
    ny = y + dir_y[d]
    if nx < 0 or nx >= 10 or ny < 0 or ny >= 10 or grid[nx][ny] == "*":
        return x, y, (d + 1) % 4
    return nx, ny, d


def get_state():
    return farmer_x, farmer_y, farmer_d, cow_x, cow_y, cow_d


farmer_x, farmer_y, farmer_d = 0, 0, 0
cow_x, cow_y, cow_d = 0, 0, 0
for i in range(10):
    for j in range(10):
        if grid[i][j] == "F":
            farmer_x, farmer_y = i, j
        elif grid[i][j] == "C":
            cow_x, cow_y = i, j

visited = set()
visited.add(get_state())
count = 0
while True:
    farmer_x, farmer_y, farmer_d = move(farmer_x, farmer_y, farmer_d)
    cow_x, cow_y, cow_d = move(cow_x, cow_y, cow_d)
    count += 1
    if get_state() in visited:
        print(get_state())
        fout.write(f"{0}\n")
        break
    elif farmer_x == cow_x and farmer_y == cow_y:
        fout.write(f"{count}\n")
        break
    visited.add(get_state())

fout.close()
