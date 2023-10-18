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

# e represents the difference between two diff: e[i] = abs(d[i] - d[i - 1])
# The goal is to make every e[i] to 0. When increase / decrease from d[i] to d[j], the change to e is on
# e[i - 1] and e[j + 1], so each operation change 2 on e
# So the question is to find sum of e divide by 2
d = [0] + d + [0]
e = []
for i in range(1, len(d)):
    e.append(abs(d[i] - d[i - 1]))
print(sum(e) // 2)

# print(sum(abs(x - y) for x, y in zip(d + [0], [0] + d)) // 2)