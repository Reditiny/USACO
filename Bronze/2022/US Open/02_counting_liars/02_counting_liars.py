"""
ID: mck15821
LANG: PYTHON3
PROG: Daisy Chains
"""
# http://usaco.org/index.php?page=viewproblem2&cpid=1228
pos = []
N = int(input())
for i in range(N):
    ch, val = input().split()
    pos.append([ch, int(val)])

ans = N
for _, hiding_place in pos:
    liars = 0
    for ch, val in pos:
        if (hiding_place < val and ch == "G") or (hiding_place > val and ch == "L"):
            liars += 1
    ans = min(liars, ans)

print(ans)
