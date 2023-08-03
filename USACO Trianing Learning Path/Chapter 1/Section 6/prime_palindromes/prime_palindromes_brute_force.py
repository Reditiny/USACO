"""
ID: mck15821
LANG: PYTHON3
PROG: pprime
"""
fin = open('pprime.in', 'r')
fout = open("pprime.out", "w")

a, b = map(int, fin.readline().strip().split())


def is_prime(n):
    for i in range(2, int(n ** 0.5) + 1):
        if n % i == 0:
            return False
    return True


def is_palindrome(s):
    return s == s[::-1]


for i in range(a, b + 1):
    if is_palindrome(str(i)) and is_prime(i):
        fout.write(f"{i}\n")
fout.close()
