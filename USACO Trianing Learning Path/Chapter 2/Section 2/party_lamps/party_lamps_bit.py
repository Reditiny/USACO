"""
ID: mck15821
LANG: PYTHON3
PROG: lamps
"""
fin = open('lamps.in', 'r')
fout = open("lamps.out", "w")

MAX_LAMP = 6  # Pattern repeats every 6 lamps
N = int(fin.readline().strip())
C = int(fin.readline().strip())
on_list = list(map(int, fin.readline().strip().split()))
off_list = list(map(int, fin.readline().strip().split()))
is_on = 0
visited = 0
possibility = [False for _ in range(1 << MAX_LAMP)]

for i in range(len(on_list) - 1):
    move_digit = MAX_LAMP - 1 - (on_list[i] - 1) % MAX_LAMP
    is_on |= 1 << move_digit
    visited |= 1 << move_digit

for i in range(len(off_list) - 1):
    move_digit = MAX_LAMP - 1 - (off_list[i] - 1) % MAX_LAMP
    visited |= 1 << move_digit

# Any even number of switch press greater than 4 is equivalent to 4 presses,
# because 2 presses of certain button equals to no press
# Any odd number of switch press greater than 3 is equivalent to 3 presses
if C > 4:
    C = 4 - (C % 2)

flips = [int("111111", 2), int("101010", 2), int("010101", 2), int("100100", 2)]


def search(lights, target):
    if target == 0:
        if (lights & visited) == is_on:
            possibility[lights] = True
        return
    for j in range(4):
        search(lights ^ flips[j], target - 1)


def gen_output(n):
    s = ""
    while len(s) < N:
        s += f"{n:b}".zfill(MAX_LAMP)
    return s[:N]


search((1 << MAX_LAMP) - 1, C)
impossible = True
for i in range(1 << MAX_LAMP):
    if possibility[i] is True:
        fout.write(f"{gen_output(i)}\n")
        impossible = False

if impossible is True:
    fout.write("IMPOSSIBLE\n")
fout.close()
