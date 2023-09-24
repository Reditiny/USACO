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
cows_sort_by_y = cows.sort(lambda x: x[1])

# [head, head, head], [head, head, tail], [head, tail, tail], [tail, tail, tail]
# [head, head], [head, tail], [tail, tail]
# head, tail
pick = {
    0: [],
    1: [[0], [-1]],
    2: [[0,1], [0, -1], [-2, -1]],
    3: [[0, 1, 2], [0, 1, -1], [0, -2, -1], [-3, -2, -1]]
}



for n_in_x in range(1, 4):
    n_in_y = 3 - n_in_x
    temp_cows = cows.copy()
    for combinations in pick[n_in_x]:
        for index in combinations:
            temp_cows.remove(cows_sort_by_x[index])



fout.write(f"{result // 2}\n")
fout.close()
