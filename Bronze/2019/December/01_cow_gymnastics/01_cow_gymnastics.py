"""
ID: mck15821
LANG: PYTHON3
PROG: gymnastics
"""
# http://www.usaco.org/index.php?page=viewproblem2&cpid=963
fin = open('gymnastics.in', 'r')
fout = open("gymnastics.out", "w")

K, N = map(int, fin.readline().strip().split())
valid_pair_set = set()
invalid_pair_set = set()

for _ in range(K):
    record = list(map(int, fin.readline().strip().split()))
    for i in range(N):
        for j in range(i + 1, N):
            print(valid_pair_set, invalid_pair_set)
            # first time seeing this combo
            if (record[i], record[j]) in valid_pair_set or (record[i], record[j]) in invalid_pair_set:
                continue
            if (record[j], record[i]) in valid_pair_set:
                valid_pair_set.remove((record[j], record[i]))
                invalid_pair_set.add((record[i], record[j]))
                invalid_pair_set.add((record[j], record[i]))
                continue
            valid_pair_set.add((record[i], record[j]))

fout.write(f"{len(valid_pair_set)}")
fout.close()
