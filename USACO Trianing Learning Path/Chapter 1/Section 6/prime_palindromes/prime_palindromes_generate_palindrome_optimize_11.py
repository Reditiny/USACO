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


def gen(n):
    s = str(n)
    result = s[:-1] + s[-1] + s[:-1][::-1]
    result = int(result)
    if a <= result <= b and is_prime(result):
        fout.write(f"{result}\n")


# a < b <= 100,000,000
for i in range(1, 10):
    gen(i)
# any even palindrome can be divided by 11, so can only do odd
if a <= 11 <= b:
    fout.write(f"11\n")
for i in range(10, 10000):
    gen(i)
fout.close()
