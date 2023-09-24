"""
ID: mck15821
LANG: PYTHON3
PROG: cownomics
"""
# http://www.usaco.org/index.php?page=viewproblem2&cpid=739
fin = open('cownomics.in', 'r')
fout = open("cownomics.out", "w")

def main():

    N, M = list(map(int, fin.readline().strip().split()))
    spotted_cow, plain_cow = [], []

    for _ in range(N):
        spotted_cow.append(fin.readline().strip())
    for _ in range(N):
        plain_cow.append(fin.readline().strip())

    # find unique spotty gene that using complete different ATCG then plain cow
    visited = [False for i in range(M)]
    unique = 0
    for i in range(M):
        temp = set()
        for j in range(N):
            temp.add(spotted_cow[j][i])
        is_unique = True
        for j in range(N):
            if plain_cow[j][i] in temp:
                is_unique = False
                break
        if is_unique:
            visited[i] = True
            unique += 1

    count = 0
    # combination math
    for i in range(unique):
        # given i unique pos, choose 2 other poses to form a gene piece
        # There's m - i - 1 options to choose the second one, then m - i - 2 to choose the third one
        # The order doesn not matter, so total possibility is C(2, m-i-1)
        # i represents the unique pos
        # e.g. if unique = 1, then we have (m - 1) * (m - 2) // 2 ways
        # if unique = 2, then first we have (m - 1) * (m - 2) // 2 ways
        # then when choosing the second round, skip the chosen unique one, do (m - 2) * (m - 3) // 2
        count += ((M - i - 1) * (M - i - 2) // 2)

    for i in range(M - 2):
        if visited[i]:
            continue
        for j in range(i + 1, M - 1):
            if visited[j]:
                continue
            for k in range(j + 1, M):
                if visited[k]:
                    continue
                valid = True

                for p in range(N):
                    for q in range(N):
                        if spotted_cow[p][i] == plain_cow[q][i] and spotted_cow[p][j] == plain_cow[q][j] and spotted_cow[p][k] == plain_cow[q][k]:
                            valid = False
                            break
                    if not valid:
                        break
                if valid:
                    count += 1

    fout.write(f"{count}\n")
    fout.close()

# use function call can make it faster in Python, without the function call the case 7 will timeout
main()
