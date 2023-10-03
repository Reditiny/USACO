"""
ID: mck15821
LANG: PYTHON3
PROG: buckets
"""
fin = open('buckets.in', 'r')
fout = open("buckets.out", "w")
# http://www.usaco.org/index.php?page=viewproblem2&cpid=939

layout = []
for _ in range(10):
    layout.append(fin.readline().strip())  # kist of 10 lines

for line in layout:  # collect (x,y) coordinate pairs of objects of interests
    if 'B' in line:
        barn = (layout.index(line), line.index('B'))
    if 'L' in line:
        lake = (layout.index(line), line.index('L'))
    if 'R' in line:
        rock = (layout.index(line), line.index('R'))

d = abs(barn[0] - lake[0]) + abs(barn[1] - lake[1]) - 1
# minimal distance will almost always be just going down then left (or up/right, etc.). Even if there's a rock on the way, just reverse steps:
# for example, if going right and down results in a rock, just go down and then right
# The only exception is if x/y of the barn and lake is the same and rock is on the way (between them) - then, you must go around and add 2 cows for it
if barn[0] == lake[0] == rock[0] and (barn[1] < rock[1] < lake[1] or barn[1] > rock[1] > lake[1]):
    d += 2
elif barn[1] == lake[1] == rock[1] and (barn[0] < rock[0] < lake[0] or barn[0] > rock[0] > lake[0]):
    d += 2
fout.write(f"{d}")
fout.close()
