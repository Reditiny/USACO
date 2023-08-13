"""
ID: mck15821
LANG: PYTHON3
PROG: castle
"""
import copy

fin = open('castle.in', 'r')
fout = open("castle.out", "w")

N, M = map(int, fin.readline().strip().split())
castle_map = []
visited = []
for i in range(M):
    castle_map.append(list(map(int, fin.readline().strip().split())))

for i in range(M):
    visited.append([-1 for _ in range(N)])

# Task 1: The number of rooms the castle has
# Task 2: The size of the largest room
# Task 3: The size of the largest room creatable by removing one wall
# Task 4: The single wall to remove to make the largest room possible
room_count = 0
adjacent_room_list = []
id = 0
area_list = []
DIRS = [[-1, 0], [1, 0], [0, -1], [0, 1]]


def dfs(i, j, id):
    if visited[i][j] != -1:
        return 0
    visited[i][j] = id

    # Mark neighbors
    if len(adjacent_room_list) == id:
        adjacent_room_list.append(set())

    for p, q in DIRS:
        if 0 <= i + p < M and 0 <= j + q < N and visited[i + p][j + q] != -1 and visited[i + p][j + q] != id:
            adjacent_room_list[id].add(visited[i + p][j + q])

    count = 1  # to count the area of room
    directions = int_to_binary_string(castle_map[i][j])
    # south
    if directions[0] == "0" and i < M - 1:
        count += dfs(i + 1, j, id)
    # east
    if directions[1] == "0" and j < N - 1:
        count += dfs(i, j + 1, id)
    # north
    if directions[2] == "0" and i > 0:
        count += dfs(i - 1, j, id)
    # west
    if directions[3] == "0" and j > 0:
        count += dfs(i, j - 1, id)
    return count


def int_to_binary_string(n):
    return f"{n:b}".zfill(4)


for i in range(M):
    for j in range(N):
        if visited[i][j] == -1:
            area = dfs(i, j, id)
            area_list.append(area)
            room_count += 1
            id += 1

fout.write(f"{room_count}\n")
# all isolated room
if room_count == M * N:
    fout.write("1\n")
    fout.write("2\n")
    if M > 1:
        fout.write(f"{M} 1 N\n")
    else:
        fout.write(f"{M} 1 E\n")
else:
    fout.write(f"{max(area_list)}\n")

    # Open one wall
    opened = [False for _ in range(room_count)]
    new_area = max(area_list)

    candidates = []
    for i in range(room_count - 1):
        for j in range(i + 1, room_count):
            if j in adjacent_room_list[i] or i in adjacent_room_list[j]:
                if new_area < area_list[i] + area_list[j]:
                    new_area = area_list[i] + area_list[j]
                    candidates = [[i, j]]
                elif new_area == area_list[i] + area_list[j]:
                    candidates.append([i, j])

    for row in visited:
        print(row)

    fout.write(f"{new_area}\n")


    def dfs_find_the_door(i, j, source, target, door):
        if visited_door[i][j] != source:
            return
        visited_door[i][j] = -1
        for p, q in DIRS:
            if 0 <= i + p < M and 0 <= j + q < N:
                # Found a connection point
                if visited[i + p][j + q] == target:
                    # target is on the west or south, then use target as the door location
                    if p > 0 or q < 0:
                        potential_door = [i + p, j + q, "E" if q < 0 else "N"]
                    else:
                        potential_door = [i, j, "E" if q > 0 else "N"]
                    old_door = door[0]
                    # potential door is west of south than the door so far
                    if potential_door[1] < old_door[1] or (
                            potential_door[1] == old_door[1] and potential_door[0] > old_door[0]):
                        door.clear()
                        door.append(potential_door.copy())
                else:
                    dfs_find_the_door(i + p, j + q, source, target, door)


    # Find the door
    door = [[0, N - 1, ""]]
    for pair in candidates:
        source, target = pair
        visited_door = copy.deepcopy(visited)
        for i in range(M):
            for j in range(N):
                if visited_door[i][j] == source:
                    dfs_find_the_door(i, j, source, target, door)

    fout.write(f"{door[0][0] + 1} {door[0][1] + 1} {door[0][2]}\n")

fout.close()
