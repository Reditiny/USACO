"""
ID: mck15821
LANG: PYTHON3
PROG: Air Cownditioning
"""
# Time complexity: O(V * N), V as maximum difference
# http://usaco.org/index.php?page=viewproblem2&cpid=1156
N = int(input())
p = list(map(int, input().strip().split()))
t = list(map(int, input().strip().split()))
d = [p[i] - t[i] for i in range(N)]
counter = 0

for i in range(N):
    if d[i] == 0:
        continue
    direction = 1 if d[i] > 0 else -1
    increment = abs(d[i])
    for _ in range(increment):
        for j in range(i, N):
            if d[j] * direction <= 0:
                break
            d[j] -= direction
    counter += increment
print(counter)
