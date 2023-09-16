"""
ID: mck15821
LANG: PYTHON3
PROG: Cow College
"""
# http://usaco.org/index.php?page=viewproblem2&cpid=1251
N = int(input())
fees = list(map(int, input().split()))
fees.sort(reverse=True)

result = [0]
tuition = [fees[0]]
for i in range(len(fees)):
    profit = fees[i] * (i + 1)
    if result[0] <= profit:
        result[0] = profit
        if tuition[0] > fees[i]:
            tuition[0] = fees[i]

print(f"{result[0]} {tuition[0]}")
