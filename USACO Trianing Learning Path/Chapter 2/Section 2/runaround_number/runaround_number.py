"""
ID: mck15821
LANG: PYTHON3
PROG: runround
"""
fin = open('runround.in', 'r')
fout = open("runround.out", "w")

N = int(fin.readline().strip())
used = [False for i in range(10)]


def is_runaround(s_list):
    i = 0
    while s_list[i] != 'X':
        old_i = i
        i = (i + int(s_list[i])) % len(s_list)
        s_list[old_i] = 'X'

    # if string is all X and i is 0, then it is a runaround number
    if i != 0:
        return False

    for j in range(len(s_list)):
        if s_list[j] != 'X':
            return False
    return True


def permutation(s, used, length, number_of_digits):
    if length == number_of_digits:
        if int(s) > N and is_runaround(list(s)):
            fout.write(f"{s}\n")
            exit(0)
        return
    for i in range(1, 10):
        if not used[i]:
            used[i] = True
            permutation(s + str(i), used, length + 1, number_of_digits)
            used[i] = False


# at most 9 digits long, cannot have 0
for number_of_digits in range(len(str(N)), len(str(N)) + 2):
    for digit in range(1, 10):
        if used[digit]:
            continue
        used[digit] = True
        permutation(str(digit), used, 1, number_of_digits)
        used[digit] = False

fout.close()
