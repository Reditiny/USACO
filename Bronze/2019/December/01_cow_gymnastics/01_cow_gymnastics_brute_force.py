"""
ID: mck15821
LANG: PYTHON3
PROG: gymnastics
"""
# http://www.usaco.org/index.php?page=viewproblem2&cpid=963
fin = open('gymnastics.in', 'r')
fout = open("gymnastics.out", "w")

K, N = map(int, fin.readline().strip().split())

gym = []
for _ in range(K):
    gym.append(list(map(int, fin.readline().strip().split())))

result = 0
for v1 in range(1, N + 1):
    for v2 in range(1, N + 1):
        count = 0
        for record in gym:
            if record.index(v1) > record.index(v2):
                count += 1
        if count == K:
            result += 1

fout.write(f"{result}\n")
fout.close()
