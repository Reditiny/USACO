"""
ID: mck15821
LANG: PYTHON3
PROG: subset
"""
fin = open('subset.in', 'r')
fout = open("subset.out", "w")

N = int(fin.readline().strip())


def is_runaround(s):
    i = 0
    s_list = list(s)
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


def permutation(s, used, nd, md):
    if nd == md:
        if is_runaround(s):
            fout.write(f"{s}\n")
        return
    for i in range(1, N + 1):
        if not used[i]:
            used[i] = True
            permutation(s + str(i), used, nd + 1, md)
            used[i] = False





fout.close()
