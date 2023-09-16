"""
ID: mck15821
LANG: PYTHON3
PROG: Moo Language
"""
# http://usaco.org/index.php?page=viewproblem2&cpid=1324
def dfs(cur_sentence, output_number_of_words)



T = int(input())
for _ in range(T):
    N, C, P = map(int, input().strip().split())
    d = {
        "noun": [],
        "transitive-verb": [],
        "intransitive-verb": [],
        "conjunction": [],
    }
    for i in range(N):
        w, t = input().split()
        d[t].append(w)

    # define output variables
    output_number_of_words = [0]
    max_output_number_of_words = [0]
    output_sentence = []

    dfs()


