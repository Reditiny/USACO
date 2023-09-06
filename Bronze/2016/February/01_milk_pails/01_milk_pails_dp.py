"""
ID: mck15821
LANG: PYTHON3
PROG: pails
"""
# http://www.usaco.org/index.php?page=viewproblem2&cpid=615
fin = open('pails.in', 'r')
fout = open("pails.out", "w")

X, Y, M = map(int, fin.readline().strip().split())
result = [0]
dp = [False for _ in range(M + 1)]

dp[0] = True
for i in range(M + 1):
    if not dp[i]:
        continue
    if i + X <= M:
        dp[i + X] = True

    if i + Y <= M:
        dp[i + Y] = True
for i in range(M, -1, -1):
    if dp[i] is True:
        fout.write(f"{i}\n")
        break

fout.close()
