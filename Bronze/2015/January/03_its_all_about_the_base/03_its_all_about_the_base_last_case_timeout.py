"""
ID: mck15821
LANG: PYTHON3
PROG: whatbase
"""
# http://www.usaco.org/index.php?page=viewproblem2&cpid=509
fin = open('whatbase_bronze/11.in', 'r')
fout = open("whatbase.out", "w")


def get_N(source, base):
    digits = str(source)
    return int(digits[0]) * base ** 2 + int(digits[1]) * base + int(digits[2])


K = int(fin.readline().strip())
for _ in range(K):
    num_in_x, num_in_y = map(int, fin.readline().strip().split())
    x, y = 10, 10
    while x <= 15000 and y <= 15000:
        num_x = get_N(num_in_x, x)
        num_y = get_N(num_in_y, y)
        if num_x == num_y:
            fout.write(f"{x} {y}\n")
            break
        elif num_x < num_y:
            x += 1
        else:
            y += 1

fout.close()
