"""
ID: mck15821
LANG: PYTHON3
PROG: reduce
"""
# Time Complexity: n * log(n) + n * log(n) + 4 * n
# http://www.usaco.org/index.php?page=viewproblem2&cpid=642
fin = open('reduce.in', 'r')
fout = open("reduce.out", "w")
N = int(fin.readline().strip())
cows_by_x = []

for _ in range(N):
    cows_by_x.append(list(map(int, fin.readline().strip().split())))

cows_by_x = sorted(cows_by_x)
cows_by_y = sorted(cows_by_x, key=lambda cow: cow[1])

points_to_drop = [cows_by_x[0], cows_by_x[-1], cows_by_y[0], cows_by_y[-1]]
answer = 40000 * 40000

for point in points_to_drop:
    min_x = 40000
    max_x = 0
    min_y = 40000
    max_y = 0
    for cow in cows_by_x:
        if cow == point:
            continue
        min_x = min(min_x, cow[0])
        max_x = max(max_x, cow[0])
        min_y = min(min_y, cow[1])
        max_y = max(max_y, cow[1])
    answer = min(answer, (max_x - min_x) * (max_y - min_y))

fout.write(f"{answer}\n")
fout.close()
