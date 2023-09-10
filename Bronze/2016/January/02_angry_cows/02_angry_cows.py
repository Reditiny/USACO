"""
ID: mck15821
LANG: PYTHON3
PROG: angry
"""
fin = open('angry.in', 'r')
fout = open("angry.out", "w")

N = int(fin.readline().strip())
bales = []
for _ in range(N):
    bales.append(int(fin.readline().strip()))
bales.sort()

total_explode = 0
# i is the index of starting point
for i in range(len(bales)):
    r = 1
    j = i  # the index that can go furthest on the left
    center = i  # center of explosion
    while j >= 0:
        old_j = j
        while j > 0 and bales[j - 1] >= bales[center] - r:
            j -= 1
        # chain action stops if nothing changed
        if old_j == j:
            break
        r += 1
        center = j

    r = 1
    k = i  # the index that can go furthest on the right
    center = i  # center of explosion
    while k < len(bales):
        old_k = k
        while k < len(bales) - 1 and bales[k + 1] <= bales[center] + r:
            k += 1
        # chain action stops if nothing changed
        if old_k == k:
            break
        r += 1
        center = k

    total_explode = max(total_explode, k - j + 1)

fout.write(f"{total_explode}\n")
fout.close()
