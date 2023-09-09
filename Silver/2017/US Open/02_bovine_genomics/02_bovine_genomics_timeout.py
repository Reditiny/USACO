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
    genes = []
    for _ in range(2 * N):
        genes.append(fin.readline().strip())

    count = 0
    for i in range(M):
        for j in range(i + 1, M):
            for k in range(j + 1, M):
                spotted_set = set()
                for r in range(N):
                    spotted_set.add(genes[r][i] + genes[r][j] + genes[r][k])
                duplicated = False
                for r in range(N, 2 * N):
                    if genes[r][i] + genes[r][j] + genes[r][k] in spotted_set:
                        duplicated = True
                        break
                if duplicated is False:
                    count += 1

    fout.write(f"{count}\n")
    fout.close()

# use function call can make it faster in Python, without the function call the case 7 will timeout
main()
