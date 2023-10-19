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
result = []

i = 0
for i in range(len(S)):
    result.append(S[i])
    while "".join(result[-len(T):]) == T:
        del result[-len(T):]

fout.write(f"{''.join(result)}\n")
fout.close()
