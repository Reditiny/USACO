"""
ID: mck15821
LANG: PYTHON3
PROG: shell
"""
# http://www.usaco.org/index.php?page=viewproblem2&cpid=891
fin = open('shell.in', 'r')
fout = open("shell.out", "w")

N = int(fin.readline().strip())
shells = []
for i in range(N):
    shells.append(list(map(int, fin.readline().strip().split())))

max_correct = 0
for pos in range(1, 4):
    correct = 0
    for shell in shells:
        # swap shell[0] and shell[1]
        if shell[0] == pos:
            pos = shell[1]
        elif shell[1] == pos:
            pos = shell[0]

        # guess correctly
        if shell[2] == pos:
            correct += 1
    max_correct = max(max_correct, correct)

fout.write(f"{max_correct}\n")
fout.close()
