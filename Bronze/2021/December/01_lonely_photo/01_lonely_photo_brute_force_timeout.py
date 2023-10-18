"""
ID: mck15821
LANG: PYTHON3
PROG: Lonely Photo
"""
# Time complexity: O(N**2)
# http://usaco.org/index.php?page=viewproblem2&cpid=1155
N = int(input())
pos = [c == "H" for c in input()]
lonely = 0

for window_len in range(3, N + 1):
    left, right = 0, window_len - 1
    running_sum = sum(pos[left: right + 1])
    while right < N:
        if running_sum == 1 or running_sum == right - left:
            lonely += 1
        right += 1
        if right < N:
            running_sum += pos[right]
        running_sum -= pos[left]
        left += 1
print(lonely)