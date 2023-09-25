"""
ID: mck15821
LANG: PYTHON3
PROG: teleport
"""
# http://www.usaco.org/index.php?page=viewproblem2&cpid=807
fin = open('teleport.in', 'r')
fout = open("teleport.out", "w")

a, b, x, y = map(int, fin.readline().strip().split())

a, b = min(a, b), max(a, b)
x, y = min(x, y), max(x, y)

result = b - a  # doesn't need teleport if not helpful

# teleport within a and b
if a <= x <= b and a <= y <= b:
    result = (x - a) + (b - y)
elif x < a <= y <= b:
    result = min(a - x, y - a) + (b - y)
elif a <= x <= b < y:
    result = (x - a) + min(y - b, b - x)
elif b < x or a > y:
    result = b - a
else:
    result = min(b - a, a - x + y - b)

fout.write(f"{result}")
fout.close()
