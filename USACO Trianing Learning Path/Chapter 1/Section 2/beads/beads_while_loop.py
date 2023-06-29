"""
ID: mck15821
LANG: PYTHON3
PROG: beads
"""
#  Time Complexity: O(n)
fin = open('beads.in', 'r')
fout = open("beads.out", "w")
bNum = int(fin.readline())
beads = fin.readline().strip()
beads = beads + beads

maxResult = 0

for i in range(bNum):
    c = beads[i]
    if c == "w":
        state = 0
    else:
        state = 1
    j = i
    current = 0
    while state <= 2:
        while j < bNum + i and (beads[j] == c or beads[j] == "w"):
            current += 1
            j += 1
        state += 1
        c = beads[j]
    if current > maxResult:
        maxResult = current

fout.write(str(maxResult) + "\n")
fout.close()
