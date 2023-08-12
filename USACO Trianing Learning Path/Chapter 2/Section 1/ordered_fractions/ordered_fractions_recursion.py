"""
ID: mck15821
LANG: PYTHON3
PROG: frac1
"""
fin = open('frac1.in', 'r')
fout = open("frac1.out", "w")

N = int(fin.readline().strip())

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


primes = sieveOfEratosthenes(160)
fracs = []
for i in range(1, N + 1):
    for j in range(0, i + 1):
        top = j
        bottom = i
        for prime in primes:
            while top % prime == 0 and bottom % prime == 0:
                top //= prime
                bottom //= prime
            if top < prime or bottom < prime:
                break
        processed = False
        temp = [top, bottom]
        for k in range(len(fracs)):
            a = fracs[k][0] * bottom
            b = fracs[k][1] * top
            # fracs[k] > bottom / top, insert bottom / top before fracs[k]
            if a > b:
                fracs.insert(k, temp)
                processed = True
                break
            # already exists, no need to insert
            elif a == b:
                processed = True
                break
        if not processed:
            fracs.append(temp)

for frac in fracs:
    fout.write(f"{frac[0]}/{frac[1]}\n")
fout.close()
