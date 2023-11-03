"""
ID: mck15821
LANG: PYTHON3
PROG: Lonely Photo
"""
# http://usaco.org/index.php?page=viewproblem2&cpid=1155
N = int(input())
pos = [c == "H" for c in input()]
# print(pos)

left, right = 0, 2
valid = []
running_sum = sum(pos[left: right + 1])
while right < N:
    if running_sum == 1 or running_sum == right - left:
        valid.append(str(pos[left: right + 1]))
    right += 1
    if right < N:
        running_sum += pos[right]

    while running_sum != 1 and running_sum != right - left and right - left > 2:
        running_sum -= pos[left]
        left += 1
print(len(valid))
