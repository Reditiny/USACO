"""
ID: mck15821
LANG: PYTHON3
PROG: censor
"""
# http://www.usaco.org/index.php?page=viewproblem2&cpid=526
fin = open('censor_bronze/15.in', 'r')
fout = open("censor.out", "w")

S = fin.readline().strip()
T = fin.readline().strip()

result = ""

for i in range(len(S)):
    result += S[i]
    if len(result) >= len(T) and result[-len(T):] == T:
        result = result[:-len(T)]

fout.write(f"{result}\n")
fout.close()
