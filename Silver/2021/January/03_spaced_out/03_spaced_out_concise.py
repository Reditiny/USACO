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
    sum_col += max(sum([grid[i][j] for j in range(0, N, 2)]), sum([grid[i][j] for j in range(1, N, 2)]))
    sum_row += max(sum([grid[j][i] for j in range(0, N, 2)]), sum([grid[j][i] for j in range(1, N, 2)]))

print(max(sum_row, sum_col))

