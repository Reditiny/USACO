"""
ID: mck15821
LANG: PYTHON3
PROG: Photoshoot
"""
# http://usaco.org/index.php?page=viewproblem2&cpid=1227
# Explanation: http://usaco.org/current/data/sol_prob1_bronze_open22.html
pos = []
N = int(input())
cows = input().strip()

# Reversing an even length of prefix, meaning we move the location of the 2-character pair, and flip the characters
# For example, HH | GH | GG | HG => GH | GG | HG | HH
# Notice the location change doesn't make a difference, because we only counts the G at even location, no matter which even location it is
# So the only difference is the flip, especially for GH -> HG; and GG and HH will stay the same

flips = 0
# G at even position, as odd index
j = N - 1
for j in range(N - 2, -1, -2):
    sub = cows[j:j + 2]
    if sub[0] == sub[1]:
        continue
    if (sub == "GH" and flips % 2 == 0) or (sub == "HG" and flips % 2 == 1):
        flips += 1

print(flips)
