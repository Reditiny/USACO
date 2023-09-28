"""
ID: mck15821
LANG: PYTHON3
PROG: outofplace
"""
# http://www.usaco.org/index.php?page=viewproblem2&cpid=785
fin = open('outofplace.in', 'r')
fout = open("outofplace.out", "w")
N = int(fin.readline().strip())
places = []
for _ in range(N):
    places.append(int(fin.readline().strip()))

if places[0] > places[1]:
    bessie = 0
    direction = 1
elif places[N - 2] > places[N - 1]:
    bessie = N - 1
    direction = -1
else:
    for i in range(1, N):
        if places[i - 1] > places[i]:
            if places[i] < places[i - 2]:
                bessie = i
                direction = -1
            elif places[i - 1] > places[i + 1]:
                bessie = i - 1
                direction = 1


def binary_search(target_list, value, direction, left, right):
    while left + 1 < right:
        mid = (left + right) // 2
        if target_list[mid] == value:
            if direction > 0:
                right = mid
            else:
                left = mid
        elif target_list[mid] < value:
            left = mid
        else:
            right = mid
    if direction < 0:
        if target_list[right] <= value:
            return right + 1
        elif target_list[left] <= value:
            return right
        return left
    if target_list[left] >= value:
        return left - 1
    if target_list[right] >= value:
        return left
    return right


if direction > 0:
    new_location = binary_search(places, places[bessie], direction, bessie + 1, N - 1)
    swaps = len(set(places[bessie + 1: new_location + 1]))
else:
    new_location = binary_search(places, places[bessie], direction, 0, bessie - 1)
    swaps = len(set(places[new_location: bessie]))

fout.write(f"{swaps}")
fout.close()
