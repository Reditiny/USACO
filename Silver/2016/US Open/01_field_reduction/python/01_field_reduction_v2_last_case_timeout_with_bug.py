"""
ID: mck15821
LANG: PYTHON3
PROG: reduce
"""
# http://www.usaco.org/index.php?page=viewproblem2&cpid=642
fin = open('reduce.in', 'r')
fout = open("reduce.out", "w")
N = int(fin.readline().strip())
cows_sort_by_x = []

for _ in range(N):
    cows_sort_by_x.append(tuple(map(int, fin.readline().strip().split())))
cows_sort_by_x = sorted(cows_sort_by_x)

result = 40000 * 40000

# tactic is to remove the 3 points from the edge, on x and y dimension
for number_of_min_x in range(4):
    for number_of_max_x in range(4):
        for number_of_min_y in range(4):
            for number_of_max_y in range(4):
                if number_of_min_x + number_of_max_x + number_of_min_y + number_of_max_y != 3:
                    continue

                # remove selected cows
                left_cows = cows_sort_by_x[number_of_min_x:N - number_of_max_x]
                left_cows = sorted(left_cows, key=lambda cow: cow[1])
                left_cows = left_cows[number_of_min_y:N - number_of_max_y]

                min_x = 40000
                max_x = 0
                min_y = 40000
                max_y = 0
                for cow in left_cows:
                    min_x = min(min_x, cow[0])
                    max_x = max(max_x, cow[0])
                    min_y = min(min_y, cow[1])
                    max_y = max(max_y, cow[1])
                # if (max_x - min_x) * (max_y - min_y) < result:
                #     print(set(cows_sort_by_x).difference(left_cows))
                #     print(number_of_min_x, number_of_max_x, number_of_min_y, number_of_max_y)
                result = min(result, (max_x - min_x) * (max_y - min_y))
fout.write(f"{result}")
fout.close()
