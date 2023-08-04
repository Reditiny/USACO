"""
ID: mck15821
LANG: PYTHON3
PROG: pprime
"""
fin = open('pprime.in', 'r')
fout = open("pprime.out", "w")

a, b = map(int, fin.readline().strip().split())


# def is_prime(n, primes):
#     sqrt = int(n ** 0.5) + 1
#     for prime in primes:
#         if n % prime == 0 and 1 < prime < sqrt:
#             return False
#     return True


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


def sieveOfEuler(n):
    visited = [False for _ in range(n + 1)]
    prime_number = []
    for i in range(2, n + 1):
        if not visited[i]:
            prime_number.append(i)
        for j in range(len(prime_number)):
            if i * prime_number[j] > n:
                break
            visited[i * prime_number[j]] = True
            # i already been sieved by prime_number[j], and as prime is in order
            # i * prime_number[k] should also be sieved by prime_number[j]
            if i % prime_number[j] == 0:
                break
    return prime_number


# def generate_palindrome(digits, N, max, primes):
#     result = []


def is_palindrome(s):
    return s == s[::-1]


# Find prime number under b^1/2
primes = sieveOfEuler(b)

# palindrome_list = generate_palindrome(0, 0, 3, primes)

for prime in primes:
    if prime < a:
        continue
    if is_palindrome(str(prime)):
        fout.write(f"{prime}\n")

fout.close()
