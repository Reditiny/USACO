"""
ID: mck15821
LANG: PYTHON3
PROG: citystate
"""
# http://www.usaco.org/index.php?page=viewproblem2&cpid=667
fin = open('citystate.in', 'r')
fout = open("citystate.out", "w")
N = int(fin.readline().strip())

state_to_city = dict()
city_map = []
for _ in range(N):
    city_map.append(fin.readline().strip().split())

for city, state in city_map:
    if state not in state_to_city:
        state_to_city[state] = []
    state_to_city[state].append(city)

result = 0
for city, state in city_map:
    if city[:2] == state:
        continue
    if city[:2] in state_to_city:
        city_list = state_to_city[city[:2]]
        for c in city_list:
            if c[:2] == state:
                result += 1

fout.write(f"{result // 2}\n")
fout.close()
