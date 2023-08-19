"""
ID: mck15821
LANG: PYTHON3
PROG: frac1
"""
fin = open('frac1.in', 'r')
fout = open("frac1.out", "w")

N = int(fin.readline().strip())


def is_coprime(a, b):
    r = a % b
    while r != 0:
        a = b
        b = r
        r = a % b
    return b == 1


fracs = []
for bottom in range(1, N + 1):
    for top in range(0, bottom + 1):
        if is_coprime(top, bottom):
            fracs.append([top, bottom])

fracs.sort(key=lambda x: x[0] / x[1])

for frac in fracs:
    fout.write(f"{frac[0]}/{frac[1]}\n")
fout.close()
