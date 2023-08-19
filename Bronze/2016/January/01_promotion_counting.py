"""
ID: mck15821
LANG: PYTHON3
PROG: promote
"""
fin = open('promote.in', 'r')
fout = open("promote.out", "w")

participants = []
for _ in range(4):
    participants.append(list(map(int, fin.readline().strip().split())))
stack = []

promotes = 0
for i in range(3, 0, -1):
    promotes = promotes + participants[i][1] - participants[i][0]
    stack.insert(0, promotes)

for n in stack:
    fout.write(f"{n}\n")

fout.close()
