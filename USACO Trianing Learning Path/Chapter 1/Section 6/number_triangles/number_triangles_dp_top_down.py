"""
ID: mck15821
LANG: PYTHON3
PROG: numtri
"""

fin = open('numtri.in', 'r')
fout = open("numtri.out", "w")
R = int(fin.readline().strip())
triangles = []
for i in range(R):
    triangles.append(list(map(int, fin.readline().strip().split())))

# dp[i][j] is the maximum sum of the path from the top to (i, j)
dp = []
for i in range(R):
    arr = [0 for _ in range(i + 1)]
    dp.append(arr)

dp[0][0] = triangles[0][0]
for i in range(1, R):
    for j in range(i + 1):
        if j == 0:
            dp[i][j] = dp[i - 1][j] + triangles[i][j]
        elif j == i:
            dp[i][j] = dp[i - 1][j - 1] + triangles[i][j]
        else:
            dp[i][j] = max(dp[i - 1][j - 1], dp[i - 1][j]) + triangles[i][j]

fout.write(f"{max(dp[R - 1])}\n")
fout.close()