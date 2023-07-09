"""
ID: mck15821
LANG: PYTHON3
PROG: crypt1
"""

fin = open('crypt1.in', 'r')
fout = open("crypt1.out", "w")
number_of_digits = int(fin.readline().strip())
digits = list(map(int, fin.readline().strip().split()))

valid_2_digits_number = []
valid_3_digits_number = []


def is_valid_number(num, digit_len):
    # check length
    s = str(num)
    if len(s) != digit_len:
        return False
    for ch in s:
        if int(ch) not in digits:
            return False
    return True


for i in range(10, 100):
    if is_valid_number(i, 2):
        valid_2_digits_number.append(i)

for i in range(100, 1000):
    if is_valid_number(i, 3):
        valid_3_digits_number.append(i)

result = 0
for num_3_digit in valid_3_digits_number:
    for num_2_digit in valid_2_digits_number:
        partial_result_1 = num_3_digit * int(str(num_2_digit)[1])
        if not is_valid_number(partial_result_1, 3):
            continue
        partial_result_2 = num_3_digit * int(str(num_2_digit)[0])
        if not is_valid_number(partial_result_2, 3):
            continue
        total = partial_result_1 + 10 * partial_result_2
        if not is_valid_number(total, 4):
            continue
        result += 1

fout.write(f"{result}\n")
fout.close()

