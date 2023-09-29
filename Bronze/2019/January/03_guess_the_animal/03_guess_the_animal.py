"""
ID: mck15821
LANG: PYTHON3
PROG: guess
"""
# http://www.usaco.org/index.php?page=viewproblem2&cpid=893
fin = open('guess.in', 'r')
fout = open("guess.out", "w")

N = int(fin.readline().strip())

# find maximum number of features shared by 2 animals
animals = []

for _ in range(N):
    line = fin.readline().strip().split()
    animal = line[0]
    number_of_features = int(line[1])
    feature_set = set()
    for i in range(2, 2 + number_of_features):
        feature_set.add(line[i])
    animals.append(feature_set)

count = 0
# max means the maximum number of overlapping features between two animals
for i in range(N):
    for j in range(i + 1, N):
        count = max(count, len(animals[i].intersection(animals[j])))

fout.write(f"{count + 1}\n")
fout.close()
