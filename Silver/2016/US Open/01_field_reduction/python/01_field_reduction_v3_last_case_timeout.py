"""
ID: mck15821
LANG: PYTHON3
PROG: reduce
"""
# Time Complexity: n * log(n) + 4 ** 4 * (n * log(n) + n * log(n)) = O(nlogn)
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
                left_cows = left_cows[number_of_min_y:len(left_cows) - number_of_max_y]

                height = left_cows[-1][1] - left_cows[0][1]
                left_cows = sorted(left_cows)
                width = left_cows[-1][0] - left_cows[0][0]
                result = min(result, height * width)
fout.write(f"{result}")
fout.close()
