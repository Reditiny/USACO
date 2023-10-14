"""
ID: mck15821
LANG: PYTHON3
PROG: reduce
"""
# Time Complexity: n * log(n) + 4 ** 4 * k = O(nlogn) http://www.usaco.org/index.php?page=viewproblem2&cpid=642
# Thoughts: When there are multiple points with the same x, try to remove the points with furthest y For bigger
# chance to also remove edge point of y
#
# Question: There could still be one case not solved, as when picking two points with the same x and same abs
# of y we still need to try both of the points and cannot guarantee which one to remove.
# For example, choose 3 points from x, at the last point we can choose between (0, 6) and (0, -6), could lead
# to different result (it could happen if removing (0, -6) can save more areas)
from functools import cmp_to_key


def x_compare_asc(item1, item2):
    x1, y1 = item1
    x2, y2 = item2
    if x1 < x2:
        return -1
    if x1 > x2:
        return 1
    if abs(y1) > abs(y2):
        return -1
    return 1


def x_compare_desc(item1, item2):
    x1, y1 = item1
    x2, y2 = item2
    if x1 > x2:
        return -1
    if x1 < x2:
        return 1
    if abs(y1) > abs(y2):
        return -1
    return 1


def y_compare_asc(item1, item2):
    x1, y1 = item1
    x2, y2 = item2
    if y1 < y2:
        return -1
    if y1 > y2:
        return 1
    if abs(x1) > abs(x2):
        return -1
    return 1


def y_compare_desc(item1, item2):
    x1, y1 = item1
    x2, y2 = item2
    if y1 > y2:
        return -1
    if y1 < y2:
        return 1
    if abs(x1) > abs(x2):
        return -1
    return 1


fin = open('reduce.in', 'r')
fout = open("reduce.out", "w")
N = int(fin.readline().strip())
cows = []

for _ in range(N):
    cows.append(tuple(map(int, fin.readline().strip().split())))

cows_sort_by_x_asc = sorted(cows, key=cmp_to_key(x_compare_asc))
cows_sort_by_x_desc = sorted(cows, key=cmp_to_key(x_compare_desc))
cows_sort_by_y_asc = sorted(cows, key=cmp_to_key(y_compare_asc))
cows_sort_by_y_desc = sorted(cows, key=cmp_to_key(y_compare_desc))

result = 40000 * 40000

# tactic is to remove the 3 points from the edge, on x and y dimension
for number_of_min_x in range(4):
    for number_of_max_x in range(4):
        for number_of_min_y in range(4):
            for number_of_max_y in range(4):
                if number_of_min_x + number_of_max_x + number_of_min_y + number_of_max_y != 3:
                    continue
                points_removed = set()

                # Remove x points first
                points_removed = points_removed.union(cows_sort_by_x_asc[:number_of_min_x])
                points_removed = points_removed.union(cows_sort_by_x_desc[:number_of_max_x])

                # move y pointer to the latest position, if y is 0 and y[0] duplicates with x
                y_start, counter = 0, 0
                while cows_sort_by_y_asc[y_start] in points_removed:
                    y_start += 1

                # skip a point if already included from x
                while counter < number_of_min_y:
                    if cows_sort_by_y_asc[y_start] not in points_removed:
                        counter += 1
                        points_removed.add(cows_sort_by_y_asc[y_start])
                    y_start += 1

                y_end, counter = 0, 0
                while cows_sort_by_y_desc[y_end] in points_removed:
                    y_end += 1

                while counter < number_of_max_y:
                    if cows_sort_by_y_desc[y_end] not in points_removed:
                        counter += 1
                        points_removed.add(cows_sort_by_y_desc[y_end])
                    y_end += 1

                height = cows_sort_by_y_desc[y_end][1] - cows_sort_by_y_asc[y_start][1]

                # After removing y, x could also get change, so check again
                x_start, x_end = number_of_min_x, number_of_max_x

                while cows_sort_by_x_asc[x_start] in points_removed:
                    x_start += 1
                while cows_sort_by_x_desc[x_end] in points_removed:
                    x_end += 1

                width = cows_sort_by_x_desc[x_end][0] - cows_sort_by_x_asc[x_start][0]
                if width * height < result:
                    result = width * height

fout.write(f"{result}")
fout.close()
