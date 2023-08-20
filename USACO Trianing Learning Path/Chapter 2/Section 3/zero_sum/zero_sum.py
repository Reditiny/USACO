"""
ID: mck15821
LANG: PYTHON3
PROG: zerosum
"""
fin = open('zerosum.in', 'r')
fout = open("zerosum.out", "w")


N = int(fin.readline().strip())


def search(s, count):
    if count == N:
        if eval(s) == 0:
            fout.write(f"{s}\n")
        return
    search(s + " " + str(count + 1), count + 1)
    search(s + "+" + str(count + 1), count + 1)
    search(s + "-" + str(count + 1), count + 1)


def eval(s):
    sign = 1
    sum = 0
    num = 0
    for c in s:
        if c == "+":
            sum += sign * num
            num = 0
            sign = 1
        elif c == "-":
            sum += sign * num
            num = 0
            sign = -1
        elif c == " ":
            continue
        else:
            num = num * 10 + int(c)
    sum += num * sign
    return sum


search("1", 1)


fout.close()
