"""
ID: mck15821
LANG: PYTHON3
PROG: Watching Mooloo
"""
# http://www.usaco.org/index.php?page=viewproblem2&cpid=1301

N, K = map(int, input().split())
days = list(map(int, input().split()))
total = 0

for i in range(N):
    if i == 0:
        total += K + 1
    else:
        total += min(days[i] - days[i - 1], K + 1)

print(total)
