"""
ID: mck15821
LANG: PYTHON3
PROG: wormhole
"""

fin = open('wormhole.in', 'r')
fout = open("wormhole.out", "w")
number_of_holes = int(fin.readline().strip())
coordinates = []
for i in range(number_of_holes):
    coordinates.append(list(map(int, fin.readline().strip().split())))

nextOnRight = [-1 for i in range(number_of_holes)]  # next hole on the right
partners = [-1 for i in range(number_of_holes)]  # the whole to pair with


def cycleExists():
    for i in range(number_of_holes):
        # pick a start hole
        pos = i
        # iterate for N times to keep going to the partners
        for j in range(number_of_holes):
            pos = nextOnRight[partners[pos]]
            # got out of the loop
            if pos == -1:
                break
        # Still at one of the hole
        if pos != -1:
            return True
    return False


def solve():
    total = 0
    i = 0
    while i < number_of_holes:
        # found an unpaired hole
        if partners[i] == -1:
            break
        i += 1
    if i == number_of_holes:
        if cycleExists():
            return 1
        return 0
    for j in range(i + 1, number_of_holes):
        if partners[j] == -1:
            partners[i] = j
            partners[j] = i
            total += solve()
            partners[i] = -1
            partners[j] = -1
    return total


for i in range(number_of_holes):
    for j in range(number_of_holes):
        # point i on the same line as point j
        if coordinates[i][0] < coordinates[j][0] and coordinates[i][1] == coordinates[j][1]:
            # no wormhole on the right existed, or the existed wormhole on the right is further than j
            if nextOnRight[i] == -1 \
                    or coordinates[j][0] - coordinates[i][0] < coordinates[nextOnRight[i]][0] - coordinates[i][0]:
                nextOnRight[i] = j

fout.write(f"{solve()}\n")
fout.close()

