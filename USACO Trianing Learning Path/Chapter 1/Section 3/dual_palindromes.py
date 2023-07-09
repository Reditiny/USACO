"""
ID: mck15821
LANG: PYTHON3
PROG: dualpal
"""
fin = open('dualpal.in', 'r')
fout = open("dualpal.out", "w")

N, S = map(int, fin.readline().strip().split())


def ten_to_base_n(num, n):
    result = ""
    while num > 0:
        result = str(num % n) + result
        num //= n
    return result


def is_palindrome(s):
    return s == s[::-1]


result = []

for i in range(S + 1, 2 ** 32):
    count = 0
    for j in range(2, 11):
        if is_palindrome(ten_to_base_n(i, j)):
            count += 1
        if count > 1:
            break
    if count > 1:
        fout.write(f"{i}\n")
        N -= 1
        if N == 0:
            break

fout.close()
