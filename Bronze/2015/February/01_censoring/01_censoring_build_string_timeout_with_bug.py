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

i = 0
while i <= len(S) - len(T):
    if S[i:i + len(T)] == T:
        S = S[:i] + S[i + len(T):]
        i -= len(T)
    i += 1

fout.write(f"{S}\n")
fout.close()
