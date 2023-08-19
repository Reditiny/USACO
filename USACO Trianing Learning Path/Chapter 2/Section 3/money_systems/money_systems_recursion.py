"""
ID: mck15821
LANG: PYTHON3
PROG: money
"""
fin = open('money.in', 'r')
fout = open("money.out", "w")


V, N = map(int, fin.readline().strip().split()) # V = type of coins, N = target value
count = 0
coins = []
# No guarantee on how many coins per row
while count < V:
    coins_row = list(map(int, fin.readline().strip().split()))
    coins.extend(coins_row)
    count += len(coins_row)
dp = []
for i in range(N + 1):
    dp.append([-1 for j in range(V + 1)])
for i in range(V + 1):
    dp[0][i] = 1


# n: target value, k: using first k coins
def dfs(n, k):
    if dp[n][k] != -1:
        return dp[n][k]
    res = 0
    for i in range(k):
        if n - coins[i] >= 0:
            # Use coins[i] or not
            res += dfs(n - coins[i], i) + dfs(n, i - 1)
    dp[n][k] = res
    return res

fout.write(f"{dfs(N, V)}\n")
print(dp)
fout.close()
