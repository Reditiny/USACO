"""
ID: mck15821
LANG: PYTHON3
PROG: shuffle
"""
fin = open('shuffle.in', 'r')
fout = open("shuffle.out", "w")

N = int(fin.readline().strip())
pos = list(map(int, fin.readline().strip().split()))
ids = list(map(int, fin.readline().strip().split()))

shuffle = [0] * N

for _ in range(3):
    for i in range(N):
        # everytime we move value at location i (id[i]) to pos[i] - 1
        # so reversely we move value of pos[i] - 1 to i
        shuffle[i] = ids[pos[i] - 1]

    ids = shuffle.copy()

for i in range(N):
    fout.write(f"{shuffle[i]}\n")

fout.close()
