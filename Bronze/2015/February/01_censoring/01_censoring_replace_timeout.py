"""
ID: mck15821
LANG: PYTHON3
PROG: censor
"""
# http://www.usaco.org/index.php?page=viewproblem2&cpid=526
fin = open('censor.in', 'r')
fout = open("censor.out", "w")

S = fin.readline().strip()
T = fin.readline().strip()

while True:
    new_S = S.replace(T, "", 1)
    if len(new_S) == len(S):
        break
    S = new_S

fout.write(f"{S}\n")
fout.close()
