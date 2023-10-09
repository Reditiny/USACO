"""
ID: mck15821
LANG: PYTHON3
PROG: balancing
"""
# http://www.usaco.org/index.php?page=viewproblem2&cpid=619
fin = open('balancing.in', 'r')
fout = open("balancing.out", "w")

N = int(fin.readline().strip())

cows = []
for _ in range(N):
    cows.append(list(map(int, fin.readline().strip().split())))
cows_by_x = sorted(cows)
cows_by_y = sorted(cows, key=lambda cow: cow[1])


def min_partition(v_fence, cows_by_y):
    left = [c for c in cows_by_y if c[0] < v_fence]
    right = [c for c in cows_by_y if c[0] > v_fence]

    most_balanced = N
    left_index = right_index = 0
    while left_index + right_index < N:
        h_fence = cows_by_y[left_index + right_index][1] + 1

        while left_index < len(left) and h_fence > left[left_index][1]:
            left_index += 1
        while right_index < len(right) and h_fence > right[right_index][1]:
            right_index += 1
        below_max = max(left_index, right_index)
        above_max = max(len(left) - left_index, len(right) - right_index)
        most_balanced = min(most_balanced, max(above_max, below_max))
    return most_balanced


most_balanced = N
index_x = 0
while index_x < N:
    v_fence = cows_by_x[index_x][0] + 1
    most_balanced = min(most_balanced, min_partition(v_fence, cows_by_y))
    while index_x < N and cows_by_x[index_x][0] < v_fence:
        index_x += 1

fout.write(f"{most_balanced}")
fout.close()
