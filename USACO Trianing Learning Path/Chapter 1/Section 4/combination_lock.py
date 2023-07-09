"""
ID: mck15821
LANG: PYTHON3
PROG: combo
"""

fin = open('combo.in', 'r')
fout = open("combo.out", "w")
dial_number = int(fin.readline().strip())
john_key = tuple(fin.readline().strip().split())
master_key = tuple(fin.readline().strip().split())

valid_key_set = set()

for first_digit in range(-2, 3):
    for second_digit in range(-2, 3):
        for third_digit in range(-2, 3):
            valid_key_set.add((
                str((int(john_key[0]) + first_digit) % dial_number),
                str((int(john_key[1]) + second_digit) % dial_number),
                str((int(john_key[2]) + third_digit) % dial_number)
            ))
            valid_key_set.add((
                str((int(master_key[0]) + first_digit) % dial_number),
                str((int(master_key[1]) + second_digit) % dial_number),
                str((int(master_key[2]) + third_digit) % dial_number)
            ))

fout.write(f"{len(valid_key_set)}\n")
fout.close()

