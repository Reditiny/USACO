"""
ID: mck15821
LANG: PYTHON3
PROG: milk3
"""

fin = open('milk3.in', 'r')
fout = open("milk3.out", "w")

A, B, C = map(int, fin.readline().strip().split())
canget = [0 for i in range(C + 1)]
dp = []

# init dp
for i in range(A + 1):
    arr1 = []
    for j in range(B + 1):
        arr2 = [0 for _ in range(C + 1)]
        arr1.append(arr2)
    dp.append(arr1)
dp[0][0][C] = 1

flag = 1
while flag == 1:
    flag = 0
    for i in range(A + 1):
        for j in range(B + 1):
            for k in range(C + 1):
                if dp[i][j][k] > 0:
                    if i == 0:
                        canget[k] = 1
                    # pour when having milk in a
                    else:
                        if j < B:
                            # Pour a to b
                            # b has enough space
                            if B - j >= i:
                                if dp[0][i + j][k] == 0:
                                    dp[0][i + j][k] = 1
                                    flag = 1
                            # not enough space
                            else:
                                if dp[i - (B - j)][B][k] == 0:
                                    dp[i - (B - j)][B][k] = 1
                                    flag = 1
                        if k < C:
                            # Pour a to c
                            # c has enough space
                            if C - k >= i:
                                if dp[0][j][i + k] == 0:
                                    dp[0][j][i + k] = 1
                                    flag = 1
                            # not enough space
                            else:
                                if dp[i - (C - k)][j][C] == 0:
                                    dp[i - (C - k)][j][C] = 1
                                    flag = 1
                    # pour when having milk in b
                    if j > 0:
                        if i < A:
                            # Pour b to a
                            # a has enough space
                            if A - i >= j:
                                if dp[i + j][0][k] == 0:
                                    dp[i + j][0][k] = 1
                                    flag = 1
                            # not enough space
                            else:
                                if dp[A][j - (A - i)][k] == 0:
                                    dp[A][j - (A - i)][k] = 1
                                    flag = 1
                        if k < C:
                            # Pour b to c
                            # c has enough space
                            if C - k >= j:
                                if dp[i][0][j + k] == 0:
                                    dp[i][0][j + k] = 1
                                    flag = 1
                            # not enough space
                            else:
                                if dp[i][j - (C - k)][C] == 0:
                                    dp[i][j - (C - k)][C] = 1
                                    flag = 1
                    # pour when having milk in c
                    if k > 0:
                        if i < A:
                            # Pour c to a
                            # a has enough space
                            if A - i >= k:
                                if dp[i + k][j][0] == 0:
                                    dp[i + k][j][0] = 1
                                    flag = 1
                            # not enough space
                            else:
                                if dp[A][j][k - (A - i)] == 0:
                                    dp[A][j][k - (A - i)] = 1
                                    flag = 1
                        if j < B:
                            # Pour c to b
                            # b has enough space
                            if B - j >= k:
                                if dp[i][j + k][0] == 0:
                                    dp[i][j + k][0] = 1
                                    flag = 1
                            # not enough space
                            else:
                                if dp[i][B][k - (B - j)] == 0:
                                    dp[i][B][k - (B - j)] = 1
                                    flag = 1

everTrue = False
for i in range(len(canget)):
    if canget[i] == 1:
        fout.write(f"{i}")
        everTrue = True
        if i < len(canget) - 1:
            fout.write(" ")

if not everTrue:
    fout.write("NONE")
fout.write("\n")
fout.close()
