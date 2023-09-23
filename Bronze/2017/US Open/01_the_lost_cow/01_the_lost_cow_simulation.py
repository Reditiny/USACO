"""
ID: mck15821
LANG: PYTHON3
PROG: lostcow
"""
# http://www.usaco.org/index.php?page=viewproblem2&cpid=735
fin = open('lostcow.in', 'r')
fout = open("lostcow.out", "w")

x, y = map(int, fin.readline().strip().split())

pos = x
distance_from_x = 1
total_distance = 0

while True:
    print(f"Current position: {pos}")
    # upgrade the current position
    new_pos = x + distance_from_x

    # record the steps
    total_distance += abs(new_pos - pos)
    pos = new_pos

    # upgrade variables
    distance_from_x *= -2

    if (y > x and new_pos >= y) or (y < x and new_pos <= y):
        break

total_distance -= abs(pos - y)
fout.write(f"{total_distance}")
fout.close()
