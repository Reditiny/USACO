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

answer = 40000 * 40000
# Remove x[0]
width = cows_by_x[-1][0] - cows_by_x[1][0]
if cows_by_x[0] not in [cows_by_y[0], cows_by_y[-1]]:
    answer = min(answer, width * (cows_by_y[-1][1] - cows_by_y[0][1]))
else:
    # compare removing either side of y
    answer = min(answer, width * (cows_by_y[-2][1] - cows_by_y[0][1]))
    answer = min(answer, width * (cows_by_y[-1][1] - cows_by_y[1][1]))

# Remove x[-1]
width = cows_by_x[-2][0] - cows_by_x[0][0]
if cows_by_x[-1] != cows_by_y[0] and cows_by_x[-1] != cows_by_y[-1]:
    answer = min(answer, width * (cows_by_y[-1][1] - cows_by_y[0][1]))
else:
    # compare removing either side of y
    answer = min(answer, width * (cows_by_y[-2][1] - cows_by_y[0][1]))
    answer = min(answer, width * (cows_by_y[-1][1] - cows_by_y[1][1]))

# Remove y[0] or y[-1]
# In previous cases already consider the overlapping case
answer = min(answer, (cows_by_x[-1][0] - cows_by_x[0][0]) * (cows_by_y[-1][1] - cows_by_y[1][1]))
answer = min(answer, (cows_by_x[-1][0] - cows_by_x[0][0]) * (cows_by_y[-2][1] - cows_by_y[0][1]))

fout.write(f"{answer}\n")
fout.close()
