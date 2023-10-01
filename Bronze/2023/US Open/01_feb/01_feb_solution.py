"""
ID: mck15821
LANG: PYTHON3
PROG: Moo Language
"""
N = int(input())
S = input()
cur_level = 0
for i in range(N - 1):
    cur_level += S[i] == S[i + 1] != "F"
min_level = max_level = cur_level
step = 2
i = 0
while i < N:
    if S[i] == "F":
        j = i
        while j < N and S[j] == "F":
            j += 1
        if i > 0 and j < N:
            number_of_two_digits = j - (i - 1)
            parity = number_of_two_digits % 2
            if S[i - 1] == S[j]:
                min_level += parity
                max_level += j - (i - 1)  # between i - 1 and j can all be turned into same character
            else:
                min_level += 1 - parity
                max_level += j - i  # between i - 1 and j - 1 can all be turned into same character
        else:
            step = 1
            if i == 0 and j == N:
                max_level = N - 1
            else:
                max_level += j - i
        i = j
    else:
        i += 1
possibilities = range(min_level, max_level + 1, step)
print(len(possibilities))
for excitement_level in possibilities:
    print(excitement_level)