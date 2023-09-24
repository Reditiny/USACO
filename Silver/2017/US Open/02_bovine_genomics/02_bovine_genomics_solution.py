# had to pre-compute some values just to pass TLE :)

import sys


def solver(filename):
    sys.stdin = open(filename + '.in', 'r')
    sys.stdout = open(filename + '.out', 'w')

    n, m = map(int, input().split())
    spottyCow = []
    plainCow = []
    for i in range(n):
        spottyCow.append(input().rstrip())
    for i in range(n):
        plainCow.append(input().rstrip())

    # pre-computing some values for positions that are guaranteed
    # to make their own set with all other position
    cow = [False for i in range(m)]
    unique = 0
    for i in range(m):
        temp = set()
        for j in range(n):
            temp.add(spottyCow[j][i])

        for j in range(n):
            if plainCow[j][i] in temp:
                break
        else:
            cow[i] = True
            unique += 1

    final = 0
    for i in range(unique):
        final += (((m - 2 - i) * (m - 1 - i)) // 2)

    # normal searching on all other position except those
    # which were pre-computed
    for o in range(m - 2):
        if cow[o]:
            continue
        for j in range(o + 1, m - 1):
            if cow[j]:
                continue
            for k in range(j + 1, m):
                if cow[k]:
                    continue
                flag = False
                for i in range(n):
                    for l in range(n):
                        if spottyCow[i][o] == plainCow[l][o] and spottyCow[i][j] == plainCow[l][j] and spottyCow[i][
                            k] == plainCow[l][k]:
                            flag = True
                            break
                    if flag:
                        break
                else:
                    final += 1
    print(final)


if __name__ == "__main__":
    solver('cownomics')