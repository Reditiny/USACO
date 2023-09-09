"""
ID: mck15821
LANG: PYTHON3
PROG: whereami
"""
# http://www.usaco.org/index.php?page=viewproblem2&cpid=964
fin = open('whereami.in', 'r')
fout = open("whereami.out", "w")
N = int(fin.readline().strip())
s = fin.readline().strip()
result = 0

# Start with length of N-1
for length in range(N - 1, 0, -1):
    found = False
    # range of index that can have substring as long as length, e.g. length = N-1 then i can be 0 or 1
    for i in range(N - length + 1):
        # found a repeated substring
        if s.find(s[i:i + length], i + 1) != -1:
            found = True
            break
    if found is True:
        result = length + 1
        break

fout.write(f"{result}\n")
fout.close()
