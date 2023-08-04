"""
ID: mck15821
LANG: PYTHON3
PROG: sprime
"""
fin = open('sprime.in', 'r')
fout = open("sprime.out", "w")

N = int(fin.readline().strip())


def is_prime(n):
    if n == 2:
        return True
    if n % 2 == 0:
        return False
    for i in range(3, int(n ** 0.5) + 1, 2):
        if n % i == 0:
            return False
    return True


def gen_number(n):
    if not is_prime(n):
        return
    if 10 ** (N - 1) < n < 10 ** N:
        result.append(n)
        return
    for i in range(1, 11, 2):
        cur = int(f"{n}{i}")
        gen_number(cur)


# First digit should be prime
primes = [2, 3, 5, 7]
result = []

for prime in primes:
    if N == 1:
        fout.write(f"{prime}\n")
    else:
        for i in range(1, 11, 2):
            cur = int(f"{prime}{i}")
            gen_number(cur)

for number in result:
    fout.write(f"{number}\n")

fout.close()
