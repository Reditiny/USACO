"""
ID: mck15821
LANG: PYTHON3
PROG: reduce
"""
# http://www.usaco.org/index.php?page=viewproblem2&cpid=642
fin = open('reduce.in', 'r')
fout = open("reduce.out", "w")
N = int(fin.readline().strip())
cows = []

for _ in range(N):
    cows.append(list(map(int, fin.readline().strip().split())))

# to minimize the area, on each side remove minimum or maximum value of 0-3 extreme cases
# in total it's 4 ** 4 = 256 combinations
# for each combination calculate the x_diff * y_diff for the remaining cows
min_4_x = []
max_4_x = []
min_4_y = []
max_4_y = []

# tactic is to remove the 3 points from the edge, on x and y dimension

cows_sort_by_x = sorted(cows)
for i in range(4):
    min_4_x.append(cows_sort_by_x[i][0])
    max_4_x.append(cows_sort_by_x[-(i + 1)][0])

cows_sort_by_y = sorted(cows, key=lambda cow:cow[1])
for i in range(4):
    min_4_y.append(cows_sort_by_y[i])
    max_4_y.append(cows_sort_by_y[-(i + 1)])

result = 40000 * 40000

for number_of_min_x in range(4):
    for number_of_max_x in range(4):
        for number_of_min_y in range(4):
            for number_of_max_y in range(4):
                if number_of_min_x + number_of_max_x + number_of_min_y + number_of_max_y != 3:
                    continue
                cows_to_remove = []
                if number_of_min_x + number_of_max_x == 0:
                    result = min(result, (cows_sort_by_x[-number_of_max_x - 1] - cows_sort_by_x[number_of_min_x]) * cows)

                # pick cows to remove from x and y dimension
                else:
                    x_coordinates_of_cows_to_remove_from_x = cows_sort_by_x[:number_of_min_x] + cows_sort_by_x[-number_of_max_x:]
                    y_cows_to_remove = []
                    if number_of_min_y > 0:
                        for i in range(number_of_min_y):
                            cur = cows_sort_by_y[i]
                            if cur[0] in x_coordinates_of_cows_to_remove_from_x
                        y_cows_to_remove = min_4_y[:number_of_min_y]
                        # already removed from x, deduplicate




fout.write(f"{result // 2}\n")
fout.close()
