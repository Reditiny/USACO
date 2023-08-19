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
dp = [0 for i in range(N + 1)]
dp[0] = 1

for coin in coins:
    for j in range(coin, N + 1):
        dp[j] += dp[j - coin]
fout.write(f"{dp[N]}\n")
fout.close()
