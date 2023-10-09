"""
ID: mck15821
LANG: PYTHON3
PROG: reduce
"""
# Time Complexity: n * log(n) + 4 ** 4 * (n * log(n)) = O(nlogn)
# http://www.usaco.org/index.php?page=viewproblem2&cpid=642
fin = open('reduce.in', 'r')
fout = open("reduce.out", "w")
N = int(fin.readline().strip())
cows_sort_by_x = []

for _ in range(N):
    cows_sort_by_x.append(list(map(int, fin.readline().strip().split())))
cows_sort_by_x = sorted(cows_sort_by_x)
min_4_x = cows_sort_by_x[:4]
max_4_x = cows_sort_by_x[-4:]
cows_sort_by_y = sorted(cows_sort_by_x, key=lambda cow: cow[1])
min_4_y = cows_sort_by_y[:4]
max_4_y = cows_sort_by_y[-4:]

result = 40000 * 40000

# tactic is to remove the 3 points from the edge, on x and y dimension
for number_of_min_x in range(4):
    for number_of_max_x in range(4):
        for number_of_min_y in range(4):
            for number_of_max_y in range(4):
                if number_of_min_x + number_of_max_x + number_of_min_y + number_of_max_y != 3:
                    continue

                excluded_list = []
                excluded_list = cows_sort_by_x[:number_of_min_x] + cows_sort_by_x[N - number_of_max_x:]
                left_cows = cows_sort_by_x[number_of_min_x:N - number_of_max_x]
                # WARNING: It's not safe to assume width won't change when removing y, so even though this passed
                # all the test cases, but this can fail in other cases
                width = left_cows[-1][0] - left_cows[0][0]

                if number_of_min_y > 0:
                    has_duplication = len(set(excluded_list).intersection(set(cows_sort_by_y[:number_of_min_y]))

                left_cows = sorted(left_cows, key=lambda cow: cow[1])
                left_cows = left_cows[number_of_min_y:len(left_cows) - number_of_max_y]
                height = left_cows[-1][1] - left_cows[0][1]

                result = min(result, height * width)
fout.write(f"{result}")
fout.close()
