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
    # print(d, counter)
    if d[i] == 0:
        continue
    direction = 1 if d[i] > 0 else -1
    increment = abs(d[i])
    while increment > 0:
        for j in range(i, N):
            if d[j] * direction <= 0:
                break
            increment = min(increment, abs(d[j]))
        for j in range(i, N):
            if d[j] * direction <= 0:
                break
            d[j] -= direction * increment
        counter += increment
        increment = abs(d[i])
print(counter)
