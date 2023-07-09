"""
ID: mck15821
LANG: PYTHON3
PROG: palsquare
"""
fin = open('palsquare.in', 'r')
fout = open("palsquare.out", "w")

base = int(fin.readline().strip())


def ten_to_base_n(num, n):
    result = ""
    while num > 0:
        digit = num % n
        if digit > 9:
            digit = chr(ord("A") + digit - 10)
        result = str(digit) + result
        num //= n
    return result


def is_palindrome(s):
    return s == s[::-1]


for i in range(1, 301):
    num_transformed = ten_to_base_n(i, base)
    num_square_transformed = ten_to_base_n(i ** 2, base)
    if is_palindrome(num_square_transformed):
        fout.write(f"{num_transformed} {num_square_transformed}\n")
fout.close()
