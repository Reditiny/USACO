"""
ID: mck15821
LANG: PYTHON3
PROG: Lonely Photo
"""
# Time complexity: O(N)
# http://usaco.org/index.php?page=viewproblem2&cpid=1155
N = int(input())
pos = input()
lonely = 0

# At given character, find length of the continuous sequence of the other character on left and right side
# Possible combinations can be left * right
for i in range(N):
    left = 0
    if i > 0 and pos[i - 1] != pos[i]:
        left += 1
        for j in range(i - 2, -1, -1):
            if pos[j] != pos[i - 1]:
                break
            left += 1
    right = 0
    if i + 1 < N and pos[i + 1] != pos[i]:
        right += 1
        for j in range(i + 2, N):
            if pos[j] != pos[i + 1]:
                break
            right += 1
    lonely += left * right
    # Only consider single side
    if left >= 2:
        lonely += left - 1
    if right >= 2:
        lonely += right - 1
print(lonely)