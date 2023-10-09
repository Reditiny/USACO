"""
ID: mck15821
LANG: PYTHON3
PROG: reduce
"""
# Time Complexity: n * log(n) + n * log(n) + 12 * 11 * 10 * n = O(nlogn)
# http://www.usaco.org/index.php?page=viewproblem2&cpid=642
fin = open('reduce.in', 'r')
fout = open("reduce.out", "w")
N = int(fin.readline().strip())
cows_by_x = []

for _ in range(N):
    cows_by_x.append(list(map(int, fin.readline().strip().split())))

cows_by_x = sorted(cows_by_x)
cows_by_y = sorted(cows_by_x, key=lambda cow: cow[1])

maybe_set = set()  # a point can have both the smallest x and y
for i in range(3):
    maybe_set.add(tuple(cows_by_x[i]))
    maybe_set.add(tuple(cows_by_x[-(i + 1)]))
    maybe_set.add(tuple(cows_by_y[i]))
    maybe_set.add(tuple(cows_by_y[-(i + 1)]))


def get_area(cow_list, exclude_list):
    min_x = 40000
    max_x = 0
    min_y = 40000
    max_y = 0
    for cow in cow_list:
        if tuple(cow) in exclude_list:
            continue
        min_x = min(min_x, cow[0])
        max_x = max(max_x, cow[0])
        min_y = min(min_y, cow[1])
        max_y = max(max_y, cow[1])
    return (max_x - min_x) * (max_y - min_y)


maybe_list = list(maybe_set)
answer = 40000 * 40000

#  for size of maybe_set, choose 3 of the points
for i in range(len(maybe_list)):
    for j in range(i + 1, len(maybe_list)):
        for k in range(j + 1, len(maybe_list)):
            exclude_list = [maybe_list[i], maybe_list[j], maybe_list[k]]
            answer = min(answer, get_area(cows_by_x, exclude_list))
fout.write(f"{answer}\n")
fout.close()
