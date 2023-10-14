"""
ID: mck15821
LANG: PYTHON3
PROG: reduce
"""
# Time Complexity: 2 * n * log(n) + 4 ** 4 * k = O(nlogn)
# http://www.usaco.org/index.php?page=viewproblem2&cpid=642
#
# Question: There could still be one case not solved, as when picking two points with the same x and same abs
# of y we still need to try both of the points and cannot guarantee which one to remove.
# For example, choose 3 points from x, at the last point we can choose between (0, 6) and (0, -6), could lead
# to different result (it could happen if removing (0, -6) can save more areas)

# Answer: The solution still work. 
# Start from choosing 1 point, say we randomly choose from (0, 6) and (0, -6) and we didn't pick (0, -6) as the
# furthest point that will give us the smallest area, it's fine because we will consider from y's perspective;
# if (0, -6) is truly an edge point, then we covered the case when removing one point from y
fin = open('reduce.in', 'r')
fout = open("reduce.out", "w")
N = int(fin.readline().strip())
cows = []

for _ in range(N):
    cows.append(tuple(map(int, fin.readline().strip().split())))

cows_sort_by_x = sorted(cows)
cows_sort_by_y = sorted(cows, key=lambda cow: cow[1])

result = 40000 * 40000

for number_of_min_x in range(4):
    for number_of_max_x in range(4):
        for number_of_min_y in range(4):
            for number_of_max_y in range(4):
                if number_of_min_x + number_of_max_x + number_of_min_y + number_of_max_y != 3:
                    continue
                points_removed = set()

                # Remove x points first
                points_removed = points_removed.union(cows_sort_by_x[:number_of_min_x])
                points_removed = points_removed.union(cows_sort_by_x[N - number_of_max_x:])

                # move y pointer to the latest position, if y is 0 and y[0] duplicates with x
                y_start, counter = 0, 0
                while cows_sort_by_y[y_start] in points_removed:
                    y_start += 1

                # skip a point if already included from x
                while counter < number_of_min_y:
                    if cows_sort_by_y[y_start] not in points_removed:
                        counter += 1
                        points_removed.add(cows_sort_by_y[y_start])
                    y_start += 1

                y_end, counter = N - 1, 0
                while cows_sort_by_y[y_end] in points_removed:
                    y_end -= 1

                while counter < number_of_max_y:
                    if cows_sort_by_y[y_end] not in points_removed:
                        counter += 1
                        points_removed.add(cows_sort_by_y[y_end])
                    y_end -= 1

                height = cows_sort_by_y[y_end][1] - cows_sort_by_y[y_start][1]

                # After removing y, x could also get change, so check again
                x_start, x_end = number_of_min_x, N - number_of_max_x - 1

                while cows_sort_by_x[x_start] in points_removed:
                    x_start += 1
                while cows_sort_by_x[x_end] in points_removed:
                    x_end -= 1

                width = cows_sort_by_x[x_end][0] - cows_sort_by_x[x_start][0]
                if width * height < result:
                    result = width * height

fout.write(f"{result}")
fout.close()
