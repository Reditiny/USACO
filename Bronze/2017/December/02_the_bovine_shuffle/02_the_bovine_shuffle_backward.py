"""
ID: mck15821
LANG: PYTHON3
PROG: shuffle
"""
fin = open('shuffle.in', 'r')
fout = open("shuffle.out", "w")

N = int(fin.readline().strip())
shuffle = list(map(int, fin.readline().strip().split()))
ids = list(map(int, fin.readline().strip().split()))

new_ids = [0] * N # 1,3,4,5,2

for _ in range(3):
    for i in range(N):
        # everytime we move value at location i (id[i]) to shuffle[i] - 1
        # so reversely we move value of shuffle[i] - 1 to i
        new_ids[i] = ids[shuffle[i] - 1]

    ids = new_ids.copy()

for i in range(N):
    fout.write(f"{new_ids[i]}\n")

fout.close()
