"""
ID: mck15821
LANG: PYTHON3
PROG: billboard
"""
# http://www.usaco.org/index.php?page=viewproblem2&cpid=783
fin = open('billboard.in', 'r')
fout = open("billboard.out", "w")

x1, y1, x2, y2 = map(int, fin.readline().strip().split())  # lawnmower
x3, y3, x4, y4 = map(int, fin.readline().strip().split())  # billboard

overlap_x = max(0, min(x2, x4) - max(x1, x3))
overlap_y = max(0, min(y2, y4) - max(y1, y3))
overlap_area = overlap_x * overlap_y
lawnmower_area = (x2 - x1) * (y2 - y1)
billboard_area = (x4 - x3) * (y4 - y3)
result = lawnmower_area

# minimum area is less than the entire lawnmower billboard area if and only if
# the overlap's width or length is the same as billboard's length or width
# and the billboard is not splitting the lawnmower in the middle
if (overlap_x == x2 - x1 or overlap_y == y2 - y1) and not (x1 < x3 and x4 < x2) and not (y1 < y3 and y4 < y2):
    result -= overlap_area

fout.write(f"{result}\n")
fout.close()
