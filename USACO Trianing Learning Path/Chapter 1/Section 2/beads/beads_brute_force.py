"""
ID: mck15821
LANG: PYTHON3
PROG: beads
"""
#  Time Complexity: O(n^2)
fin = open('beads.in', 'r')
fout = open("beads.out", "w")
bNum = int(fin.readline())
beads = fin.readline().strip()

maxResult = 0


def break_bead(index, direction):
    color = "w"
    count = 0
    for i in range(len(beads)):
        if color == "w" and beads[index] != "w":
            color = beads[index]  # find the target color to count
        if color != "w" and beads[index] != "w" and beads[index] != color:
            break  # found unmatched color
        index = (index + direction) % len(beads)
    return count


for i in range(len(beads)):
    result = break_bead(i, 1) + break_bead((i - 1) % len(beads), -1)
    maxResult = max(maxResult, result)

#  The entire beads can be changed into one color
if maxResult > len(beads):
    maxResult = len(beads)

fout.write(str(maxResult) + "\n")
fout.close()
