"""
ID: mck15821
LANG: PYTHON3
PROG: bcs
"""
# http://www.usaco.org/index.php?page=viewproblem2&cpid=640
fin = open('bcs.in', 'r')
fout = open("bcs.out", "w")

N, K = map(int, fin.readline().strip().split())

original = []
original_count_of_hash_by_line = []
for _ in range(N):
    original.append(fin.readline().strip())
    original_count_of_hash_by_line.append(original[-1].count("#"))

pieces = []
pieces_count_of_hash_by_line = []
for i in range(K):
    piece = []
    hash_count_by_line = []
    for _ in range(N):
        piece.append(fin.readline().strip())
        hash_count_by_line.append(piece[-1].count("#"))
    pieces_count_of_hash_by_line.append(hash_count_by_line)


def match(pieces, index_1, index_2, original):


def fit(piece, target):




for i in range(K - 1):
    for j in range(i + 1, K):
        if pieces_count_of_hash[i] + pieces_count_of_hash[j] != sum(original_count_of_hash):
            continue
        match(pieces, i, j, original)




fout.write(f"{result}\n")
fout.close()
