"""
ID: mck15821
LANG: PYTHON3
PROG: concom
"""
fin = open('concom.in', 'r')
fout = open("concom.out", "w")

N = int(fin.readline().strip())
NCOM = 100
shares = []
controls = []

for i in range(100):
    shares.append([0 for j in range(100)])
    controls.append([False for j in range(100)])
for i in range(100):
    controls[i][i] = True


def add_controller(i, j):
    if controls[i][j]:
        return
    controls[i][j] = True

    # i controls j, so i controls all companies that j controls
    for k in range(NCOM):
        shares[i][k] += shares[j][k]
        # k controls i, so k also controls j
        if controls[k][i]:
            add_controller(k, j)
        if shares[i][k] > 50:
            add_controller(i, k)



for _ in range(N):
    a, b, p = map(int, fin.readline().strip().split())
    for k in range(NCOM):
        if controls[k][a - 1]:
            shares[k][b - 1] += p
        if shares[k][b - 1] > 50:
            add_controller(k, b - 1)

for i in range(NCOM):
    for j in range(NCOM):
        if i != j and controls[i][j]:
            fout.write(f"{i + 1} {j + 1}\n")

fout.close()
