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


# First digit should be prime
primes = [2, 3, 5, 7]

for prime in primes:
    if N == 1:
        fout.write(f"{prime}\n")
    else:
        for i in range(prime * 10 ** (N - 1) + 1, (prime + 1) * 10 ** (N - 1), 2):
            is_valid = True
            s = str(i)
            for j in range(len(s)):
                if not is_prime(int(s[:j + 1])):
                    is_valid = False
                    break
            if is_valid:
               fout.write(f"{i}\n")

fout.close()
