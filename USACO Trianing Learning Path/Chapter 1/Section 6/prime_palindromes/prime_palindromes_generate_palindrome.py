"""
ID: mck15821
LANG: PYTHON3
PROG: pprime
"""
fin = open('pprime.in', 'r')
fout = open("pprime.out", "w")

a, b = map(int, fin.readline().strip().split())


def is_prime(n):
    if n == 2:
        return True
    if n % 2 == 0:
        return False
    for i in range(3, int(n ** 0.5) + 1, 2):
        if n % i == 0:
            return False
    return True


def gen_odd_even(low, high):
    for i in range(low, high + 1):
        gen(i, 1)
    for i in range(low, high + 1):
        gen(i, 0)


def gen(n, is_odd):
    s = str(n)
    if is_odd == 1:
        result = s[:-1] + s[-1] + s[:-1][::-1]
    else:
        result = s + s[::-1]
    result = int(result)
    if a <= result <= b and is_prime(result):
        fout.write(f"{result}\n")


# a < b <= 100,000,000
gen_odd_even(1, 9)
gen_odd_even(10, 99)
gen_odd_even(100, 999)
gen_odd_even(1000, 9999)
fout.close()
