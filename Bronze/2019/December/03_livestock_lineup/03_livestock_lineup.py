"""
ID: mck15821
LANG: PYTHON3
PROG: lineup
"""
# http://www.usaco.org/index.php?page=viewproblem2&cpid=965
fin = open('lineup.in', 'r')
fout = open("lineup.out", "w")
N = int(fin.readline().strip())

conditions = []
for _ in range(N):
    line = fin.readline().strip().split()
    conditions.append((line[0], line[-1]))

names = ["Bessie", "Buttercup", "Belinda", "Beatrice", "Bella", "Blue", "Betsy", "Sue"]
names.sort()
total_combination = []


# run 8! times
def dfs(cur, visited):
    if len(cur) == len(names):
        total_combination.append(cur.copy())
        return
    for i in range(len(names)):
        if visited[i] is True:
            continue
        visited[i] = True
        cur.append(names[i])
        dfs(cur, visited)
        cur.pop(-1)
        visited[i] = False


def compare_list(list1, list2):
    for i in range(len(list1)):
        if list1[i] < list2[i]:
            return -1
        if list1[i] > list2[i]:
            return 1
    return 0


dfs([], [False for _ in range(len(names))])

candidate = []
for comb in total_combination:
    found = True
    for line in conditions:
        source, target = line
        if abs(comb.index(source) - comb.index(target)) != 1:
            found = False
            break
    if found is True:
        if len(candidate) == 0 or compare_list(candidate, comb) > 0:
            candidate = comb

for name in candidate:
    fout.write(f"{name}\n")
fout.close()
