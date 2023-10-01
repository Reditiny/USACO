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

cows_sort_by_x = cows.sort()
min_4_x = []
max_4_x = []
for i in range(4):
    min_4_x.append(cows_sort_by_x[i])
for i in range(N - 1, -1, -1):
    max_4.append(cows_sort_by_x[i])

cows_sort_by_y = cows.sort(lambda x: x[1])
min_4_y = []
max_4_y = []
for i in range(4):
    min_4_y.append(cows_sort_by_y[i])
for i in range(N - 1, -1, -1):
    max_4_y.append(cows_sort_by_y[i])

result = 40000 * 40000

for number_of_min_x_removed in range(4):
    for number_of_max_x_removed in range(4):

        for number_of_min_y_removed in range(4):
            for number_of_max_y_removed in range(4):
                if number_of_min_x_removed + number_of_max_x_removed + number_of_min_y_removed + number_of_max_y_removed >= 4:



fout.write(f"{result // 2}\n")
fout.close()
