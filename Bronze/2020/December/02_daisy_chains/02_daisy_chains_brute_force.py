"""
ID: mck15821
LANG: PYTHON3
PROG: Daisy Chains
"""

N = int(input())
P = list(map(int, input().split()))

count = N  # include the single flower
for i in range(N):
    for j in range(i+1, N):
        sum_p = sum(P[i:j + 1])
        number_of_flowers = j - i + 1
        if sum_p % number_of_flowers != 0:
            continue
        if (sum_p // number_of_flowers) in P[i:j + 1]:
            count += 1

print(count)
