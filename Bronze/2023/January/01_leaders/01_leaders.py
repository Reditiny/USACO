"""
ID: mck15821
LANG: PYTHON3
PROG: leaders
"""
N = int(input())
cows = input()
cow_list = [0] + list(map(int, input().split()))
g_start = cows.index("G") + 1
h_start = cows.index("H") + 1
reversed_cows = cows[::-1]
g_end = N - 1 - reversed_cows.index("G") + 1
h_end = N - 1 - reversed_cows.index("H") + 1
count = 0

# A cow can be a leader with 2 conditions: cover all breed or cover the other leader
# If the first G covers all G, we got a G leader:
# than we either find all B before G leader that covers G, or check whether the first B covers all B
# If the first G doesn't cover all G, than only chance is like GGGGGB

# The first H is before the first G
if g_start > h_start:
    if cow_list[g_start] >= g_end:
        for i in range(1, g_start):
            if cow_list[i] >= g_start or i == h_start and cow_list[h_start] >= h_end:
                count += 1
    # Otherwise the only chance g_start can be a leader is to cover leader of H
    # But if that leader of H is after g_start, it cannot cover all H or g_start, so cannot be leader
    # Thus the only condition needs to be checked is whether g_start covers all G
else:
    if cow_list[h_start] >= h_end:
        for i in range(1, h_start):
            if cow_list[i] >= h_start or i == g_start and cow_list[g_start] >= g_end:
                count += 1

print(count)
