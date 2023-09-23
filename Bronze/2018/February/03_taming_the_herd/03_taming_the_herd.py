"""
ID: mck15821
LANG: PYTHON3
PROG: taming
"""
fin = open('taming.in', 'r')
fout = open("taming.out", "w")
N = int(fin.readline().strip())
line = list(map(int, fin.readline().strip().split()))
line.sort()

if line[0] == line[1] - 1 and line[1] == line[2] - 1:
    fout.write("0\n")
    fout.write("0\n")
    fout.close()
    exit(0)

if line[1] - line[0] == 2 or line[2] - line[1] == 2:
    minimum_move = 1
else:
    minimum_move = 2

maximum_move = max(line[1] - line[0], line[2] - line[1]) - 1

fout.write(f"{minimum_move}\n")
fout.write(f"{maximum_move}\n")
fout.close()
