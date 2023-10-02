"""
ID: mck15821
LANG: PYTHON3
PROG: Spaced Out
"""
# http://www.usaco.org/index.php?page=viewproblem2&cpid=1088

N = int(input())
grid = []
for i in range(N):
    grid.append(list(map(int, input().strip().split(" "))))

sum_row, sum_col = 0, 0
# Either rows need to be alternative, or columns need to be alternative, or both
for i in range(N):
    sum_odd, sum_even = 0, 0
    for j in range(N):
        if j % 2 == 0:
            sum_even += grid[i][j]
        else:
            sum_odd += grid[i][j]
    sum_col += max(sum_even, sum_odd)

for j in range(N):
    sum_odd, sum_even = 0, 0
    for i in range(N):
        if i % 2 == 0:
            sum_even += grid[i][j]
        else:
            sum_odd += grid[i][j]
    sum_row += max(sum_even, sum_odd)

print(max(sum_row, sum_col))

