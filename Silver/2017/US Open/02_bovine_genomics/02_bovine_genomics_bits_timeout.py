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

    # build a quadruple base with ATCG
    gene_base = {
        "A": 0,
        "T": 1,
        "C": 2,
        "G": 3
    }

    count = 0
    spotted_array = [False for _ in range(64)]  # the max of 4-base system for 3 digits is 64
    for i in range(M):
        for j in range(i + 1, M):
            for k in range(j + 1, M):
                for r in range(N):
                    spotted_array[gene_base[genes[r][i]] * 16 + gene_base[genes[r][j]] * 4 + gene_base[genes[r][k]]] = True
                duplicated = False
                for r in range(N, 2 * N):
                    if spotted_array[gene_base[genes[r][i]] * 16 + gene_base[genes[r][j]] * 4 + gene_base[genes[r][k]]] is True:
                        duplicated = True
                        break
                if duplicated is False:
                    count += 1
                for r in range(N):
                    spotted_array[gene_base[genes[r][i]] * 16 + gene_base[genes[r][j]] * 4 + gene_base[genes[r][k]]] = False


    fout.write(f"{count}\n")
    fout.close()

# use function call can make it faster in Python
main()
