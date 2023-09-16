"""
ID: mck15821
LANG: PYTHON3
PROG: whatbase
"""
# http://www.usaco.org/index.php?page=viewproblem2&cpid=509
fin = open('whatbase.in', 'r')
fout = open("whatbase.out", "w")


def get_N(source, base):
    digits = str(source)
    return int(digits[0]) * base ** 2 + int(digits[1]) * base + int(digits[2])


def find_base_of_b(converted_a, b):
    left, right = 10, 15000

    while left + 1 < right:
        mid = (left + right) // 2
        converted_b = get_N(b, mid)
        if converted_a == converted_b:
            return mid
        if converted_a < converted_b:
            right = mid
        else:
            left = mid
    if get_N(b, left) == converted_a:
        return left
    if get_N(b, right) == converted_a:
        return right
    return -1


K = int(fin.readline().strip())
for _ in range(K):
    a, b = map(int, fin.readline().strip().split())
    calc_a, calc_b = min(a, b), max(a, b)
    for base_of_a in range(10, 15000):
        converted_a = get_N(calc_a, base_of_a)
        base_of_b = find_base_of_b(converted_a, calc_b)
        if base_of_b != -1:
            if a < b:
                fout.write(f"{base_of_a} {base_of_b}\n")
            else:
                fout.write(f"{base_of_b} {base_of_a}\n")
            break

fout.close()
