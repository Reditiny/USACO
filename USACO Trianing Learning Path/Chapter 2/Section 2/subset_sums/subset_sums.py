"""
ID: mck15821
LANG: PYTHON3
PROG: preface
"""
fin = open('preface.in', 'r')
fout = open("preface.out", "w")

N = int(fin.readline().strip())
values = [1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1]
strs = ["M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"]
count = {}

def number_to_roman(num):
    s = ""
    for i in range(len(values)):
        while num >= values[i]:
            num -= values[i]
            s += strs[i]
        if num == 0:
            break
    return s

for i in range(1, N + 1):
    s = number_to_roman(i)
    for c in s:
        if c not in count:
            count[c] = 0
        count[c] += 1

for c in "IVXLCDM":
    if c in count:
        fout.write(f"{c} {count[c]}\n")

fout.close()
