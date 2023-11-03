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

g_cows.sort()
l_cows.sort()

min_liars = N
# for each g in g_cow, it means the hiding pos is greater than g, check how many l satisfied
for i in range(len(g_cows)):
    l_liars = bisect(l_cows, g_cows[i])
    g_liars = len(g_cows) - i - 1
    min_liars = min(min_liars, l_liars + g_liars)

# for l_cow iterate reversely as the last pos is the most generous one
for i in range(len(l_cows) - 1, -1, -1):
    l_liars = i
    g_liars = len(g_cows) - bisect(g_cows, l_cows[i])
    min_liars = min(min_liars, l_liars + g_liars)

print(min_liars)
