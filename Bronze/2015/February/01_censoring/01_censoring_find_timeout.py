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
    index_of_t = S.find(T)
    if index_of_t == -1:
        break
    S = S[:index_of_t] + S[index_of_t + len(T):]

fout.write(f"{S}\n")
fout.close()
