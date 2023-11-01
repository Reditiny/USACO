"""
ID: mck15821
LANG: PYTHON3
PROG: milkorder
"""
# http://www.usaco.org/index.php?page=viewproblem2&cpid=832
fin = open('milkorder.in', 'r')
fout = open("milkorder.out", "w")

N, M, K = map(int, fin.readline().strip().split())
orders = list(map(int, fin.readline().strip().split()))
cows = [0] * (N + 1)

# Fill cows to requested spots; if found cow 0 early, then direct return
for _ in range(K):
    cow_number, target_pos = map(int, fin.readline().strip().split())
    if cow_number == 1:
        fout.write(f"{target_pos}")
        fout.close()
        exit(0)
    cows[target_pos] = cow_number


def insert_order_from_start():
    last_pos = 1
    for cow in orders:
        if cow == 1:
            break
        if cow in cows:
            last_pos = cows.index(cow)
        else:
            while cows[last_pos] != 0:
                last_pos += 1
        cows[last_pos] = cow
    return last_pos


def insert_order_from_end():
    last_pos = len(cows) - 1
    for i in range(len(orders) - 1, -1, -1):
        cow = orders[i]
        if cow in cows:
            last_pos = cows.index(cow)
        else:
            while cows[last_pos] != 0:
                last_pos -= 1
        cows[last_pos] = cow


if 1 in orders:
    last_pos = insert_order_from_start()
else:
    insert_order_from_end()
    last_pos = 1

for i in range(last_pos, len(cows)):
    if cows[i] == 0:
        fout.write(f"{i}")
        fout.close()
        exit(0)
