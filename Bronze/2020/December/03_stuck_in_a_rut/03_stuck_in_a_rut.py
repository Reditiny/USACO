"""
ID: mck15821
LANG: PYTHON3
PROG: Stuck in a Rut
"""
# http://www.usaco.org/index.php?page=viewproblem2&cpid=1061
N = int(input())
n_cows = []
e_cows = []

# Group cows into n and e
for i in range(N):
    direction, x, y = input().split()
    if direction == "E":
        e_cows.append((int(x), int(y), i))
    elif direction == "N":
        n_cows.append((int(x), int(y), i))

# sort n_cow by x, e_cow by y
n_cows.sort()
e_cows.sort(key=lambda cow: cow[1])

stop_pos = [-1 for _ in range(N)]
for nc in n_cows:
    for ec in e_cows:
        # only when nc is on the east of ec, and ec is at the south of nc, will they intersect
        if nc[0] > ec[0] and nc[1] < ec[1]:
            n_travel = ec[1] - nc[1]
            e_travel = nc[0] - ec[0]

            # check if the north cow blocks the east cow
            if n_travel < e_travel and stop_pos[ec[2]] == -1:
                stop_pos[ec[2]] = nc[0]

            # check if the east cow blocks the north cow
            if n_travel > e_travel and stop_pos[ec[2]] == -1:
                stop_pos[nc[2]] = ec[1]
                break

dist = [-1 for _ in range(N)]
for nc in n_cows:
    if stop_pos[nc[2]] != -1:
        # Eaten is stop_pos - original_pos
        dist[nc[2]] = stop_pos[nc[2]] - nc[1]

for ec in e_cows:
    if stop_pos[ec[2]] != -1:
        dist[ec[2]] = stop_pos[ec[2]] - ec[0]

for d in dist:
    print("Infinity" if d == -1 else d)
