"""
ID: mck15821
LANG: PYTHON3
PROG: Counting liars
"""
# http://usaco.org/index.php?page=viewproblem2&cpid=1228
from bisect import bisect
g_cows = []
l_cows = []
N = int(input())
for i in range(N):
    ch, val = input().split()
    if ch == "G":
        g_cows.append(int(val))
    else:
        l_cows.append(int(val))

g_cows.sort(reverse=True)
l_cows.sort(reverse=True)

min_liars = N
j = 0
for i in range(len(g_cows)):
    g_liars = i  # g_cows[i] is true, all g_cow before i lied, hiding place is between g_cows[i-1] and g_cows[i]
    # j increase until l_cows[j] < g_cows[i], all cows before j not lie
    while j < len(l_cows) and g_cows[i] <= l_cows[j]:
        j += 1
    if j == len(l_cows):
        min_liars = min(min_liars, g_liars)
        break  # g_liars will increase, so this is at minimum value, can break early
    l_liars = len(l_cows) - j
    min_liars = min(min_liars, g_liars + l_liars)

print(min_liars)
