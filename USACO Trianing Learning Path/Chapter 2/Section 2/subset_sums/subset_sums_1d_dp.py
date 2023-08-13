"""
ID: mck15821
LANG: PYTHON3
PROG: subset
"""
fin = open('subset.in', 'r')
fout = open("subset.out", "w")

N = int(fin.readline().strip())

sum_N = N * (N + 1) // 2
if sum_N % 2 == 1:
    fout.write("0\n")
    fout.close()
    exit(0)
target = sum_N // 2

dp = []  # dp[i][j]: number of ways to get sum i using first j numbers
for i in range(target + 1):
    dp.append([0 for j in range(N + 1)])

# calculate dp using
dp[0][0] = 1
for i in range(1, N + 1):  # first i numbers
    for j in range(target + 1):  # sum j
        dp[j][i] = dp[j][i - 1]
        # if j - i >= 0, then we can add i to the sum
        if j - i >= 0:
            dp[j][i] += dp[j - i][i - 1]

fout.write(f"{dp[target][N] // 2}\n")
fout.close()
