"""
ID: mck15821
LANG: PYTHON3
PROG: Moo Language
"""
# http://usaco.org/index.php?page=viewproblem2&cpid=1324
T = int(input())
for _ in range(T):
    N, C, P = map(int, input().strip().split())
    nouns, vts, vis, conjs = [], [], [], []
    for line in range(N):
        w, t = input().strip().split()
        if t == "noun":
            nouns.append(w)
        elif t == "transitive-verb":
            vts.append(w)
        elif t == "intransitive-verb":
            vis.append(w)
        else:
            conjs.append(w)
    # print(nouns, vts, vis, conjs)

    # 1. Connect noun, intransitive-verb and noun (+3)
    # 2. Connect noun and transitive-verb (+2)
    # 3. Add comma and noun to make 1 long
    # 4. Use conjunction to connect longest sentences

    noun_count, vt_count, vi_count, conj_count = len(nouns), len(vts), len(vis), len(conjs)
    max_words = 0
    number_of_vi = number_of_vt = number_of_extra_noun = number_of_conj = 0
    # i: number of intransitive-verb; j: number of transitive-verb
    for i in range(vi_count + 1):
        j = min(vt_count, (noun_count - i) // 2)
        found = False
        while j >= 0:
            conj_used = min(conj_count, (i + j) // 2)
            # enough periods to use
            if i + j - conj_used <= P:
                found = True
                break
            j -= 1
        if found:
            extra_noun = min(noun_count - i - 2 * j, C)
            if j == 0:
                extra_noun = 0  # no transitive-verb, no chance to use extra noun
            total_words = 2 * i + 3 * j + conj_used + extra_noun
            if total_words > max_words:
                max_words = total_words
                number_of_vi, number_of_vt, number_of_extra_noun, number_of_conj = i, j, extra_noun, conj_used
    print(max_words)
    simple_sentence = []
    for num in range(number_of_vi):
        simple_sentence.append(f"{nouns.pop()} {vis.pop()}")
    for num in range(number_of_vt):
        simple_sentence.append(f"{nouns.pop()} {vts.pop()} {nouns.pop()}")
    for num in range(number_of_extra_noun):
        simple_sentence[-1] += ", " + nouns.pop()
    compound_sentence = []
    for num in range(number_of_conj):
        compound_sentence.append(f"{simple_sentence.pop()} {conjs.pop()} {simple_sentence.pop()}")
    sentences = [s + "." for s in simple_sentence + compound_sentence]
    print(" ".join(sentences))
