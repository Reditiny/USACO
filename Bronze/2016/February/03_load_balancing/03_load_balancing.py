"""
ID: mck15821
LANG: PYTHON3
PROG: balancing
"""
# http://www.usaco.org/index.php?page=viewproblem2&cpid=617
fin = open('balancing.in', 'r')
fout = open("balancing.out", "w")

N, B = map(int, fin.readline().strip().split())

cows = []
h_fence = set()
v_fence = set()

for _ in range(N):
    x, y = map(int, fin.readline().strip().split())
    cows.append([x, y])
    v_fence.add(x + 1)  # x coordinates of vertical fence
    h_fence.add(y + 1)  # y coordinates of horizontal fence

min_result = N
for v in v_fence:
    for h in h_fence:
        top_left, top_right, bottom_left, bottom_right = 0, 0, 0, 0
        for c in cows:
            if c[0] < v and c[1] < h:
                bottom_left += 1
            elif c[0] < v and c[1] > h:
                top_left += 1
            elif c[0] > v and c[1] < h:
                bottom_right += 1
            elif c[0] > v and c[1] > h:
                top_right += 1
        min_result = min(min_result, max(top_left, top_right, bottom_left, bottom_right))

fout.write(f"{min_result}")
fout.close()
