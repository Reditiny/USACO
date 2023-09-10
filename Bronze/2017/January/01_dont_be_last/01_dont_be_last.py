"""
ID: mck15821
LANG: PYTHON3
PROG: notlast
"""
# http://www.usaco.org/index.php?page=viewproblem2&cpid=687
fin = open('notlast.in', 'r')
fout = open("notlast.out", "w")
N = int(fin.readline().strip())
cows = {
    "Bessie": 0,
    "Elsie": 0,
    "Daisy": 0,
    "Gertie": 0,
    "Annabelle": 0,
    "Maggie": 0,
    "Henrietta": 0,
}

for _ in range(N):
    name, prod = fin.readline().strip().split()
    cows[name] += int(prod)

cows_list = list()
for k, v in cows.items():
    cows_list.append((k, v))
cows_list.sort(key=lambda x: x[1])
i = 0
result = "Tie"

while i < len(cows_list):
    if cows_list[i][1] != cows_list[0][1]:
        # not a tie
        if i == len(cows_list) - 1 or cows_list[i][1] != cows_list[i + 1][1]:
            result = cows_list[i][0]
        break
    i += 1

fout.write(f"{result}\n")
fout.close()
