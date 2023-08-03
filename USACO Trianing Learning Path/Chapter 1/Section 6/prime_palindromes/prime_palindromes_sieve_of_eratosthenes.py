"""
ID: mck15821
LANG: PYTHON3
PROG: pprime
"""
fin = open('pprime.in', 'r')
fout = open("pprime.out", "w")

a, b = map(int, fin.readline().strip().split())


# if found a prime number than mark all times as non-prime
def sieveOfEratosthenes(n):
    prime_number = []
    prime = [True for _ in range(n + 1)]
    p = 2
    while p * p <= n:
        if prime[p]:
            for i in range(2 * p, n + 1, p):
                prime[i] = False
        p += 1
    for i in range(2, n + 1):
        if prime[i] is True:
            prime_number.append(i)
    # return a list of prime number less than n
    return prime_number


def is_palindrome(s):
    return s == s[::-1]


primes = sieveOfEratosthenes(b)

for prime in primes:
    if prime < a:
        continue
    if is_palindrome(str(prime)):
        fout.write(f"{prime}\n")

fout.close()
