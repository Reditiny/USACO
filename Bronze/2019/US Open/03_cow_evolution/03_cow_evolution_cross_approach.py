"""
ID: mck15821
LANG: PYTHON3
PROG: evolution
"""
fin = open('evolution.in', 'r')
fout = open("evolution.out", "w")
# http://www.usaco.org/index.php?page=viewproblem2&cpid=941

N = int(fin.readline().strip())
cows = []
features = set()

for _ in range(N):
    line = fin.readline().strip().split()
    cows.append(line[1:])
    features.update(line[1:])

features = list(features)
for a in range(len(features)):
    for b in range(a + 1, len(features)):
        both, only_a, only_b = False, False, False
        for c in cows:
            has_a = features[a] in c
            has_b = features[b] in c
            if has_a and has_b:
                both = True
            elif has_a and not has_b:
                only_a = True
            elif has_b and not has_a:
                only_b = True
        # if there's cow A with feature a, cow B with feature b, and cow C with both of them
        # That's an invalid tree
        if only_a and only_b and both:
            fout.write("no")
            exit(0)
fout.write("yes")
fout.close()
