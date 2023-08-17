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
    dp.append(0)

dp[0] = 1
for i in range(1, N + 1):  # first i numbers
    for j in range(target, i - 1, -1):  # sum j, stops at i because we can't use i to get sum less than i
        dp[j] += dp[j - i]

fout.write(f"{dp[target] // 2}\n")
fout.close()
