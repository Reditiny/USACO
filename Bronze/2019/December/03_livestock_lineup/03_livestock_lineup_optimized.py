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
def dfs(cur):
    if len(cur) == len(names):
        total_combination.append(cur.copy())
        return
    for name in names:
        if name not in cur:
            cur.append(name)
            dfs(cur)
            cur.pop(-1)


dfs([])

for comb in total_combination:
    found = True
    for line in conditions:
        source, target = line
        if abs(comb.index(source) - comb.index(target)) != 1:
            found = False
            break
    if found is True:
        for name in comb:
            fout.write(f"{name}\n")
        break
fout.close()


