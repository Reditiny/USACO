"""
ID: mck15821
LANG: PYTHON3
PROG: citystate
"""
# http://www.usaco.org/index.php?page=viewproblem2&cpid=667
fin = open('citystate_silver_dec16/5.in', 'r')
fout = open("citystate.out", "w")
N = int(fin.readline().strip())

city_map = {}
for _ in range(N):
    city, state = fin.readline().strip().split()
    # two city need to come from different states
    if city[:2] == state:
        continue
    key_str = city[:2] + state
    if key_str not in city_map:
        city_map[key_str] = 0
    city_map[key_str] += 1

result = 0
for pair, count in city_map.items():
    reversed_pair = pair[2:] + pair[:2]
    if reversed_pair in city_map:
        result += count * city_map[reversed_pair]

fout.write(f"{result // 2}\n")
fout.close()
