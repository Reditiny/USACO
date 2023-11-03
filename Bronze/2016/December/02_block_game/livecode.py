fin = open("blocks.in", "r")
fout = open("blocks.out", "w")

N = int(fin.readline().strip())
global_alphabet = [0] * 26
for _ in range(N):
    word_a, word_b = fin.readline().strip().split()
    word_a_alphabet = [0] * 26
    word_b_alphabet = [0] * 26
    for ch in word_a:
        word_a_alphabet[ord(ch) - ord("a")] += 1
    for ch in word_b:
        word_b_alphabet[ord(ch) - ord("a")] += 1
    for i in range(26):
        global_alphabet[i] += max(word_a_alphabet[i], word_b_alphabet[i])

for ch in global_alphabet:
    fout.write(f"{ch}\n")
fout.close()
