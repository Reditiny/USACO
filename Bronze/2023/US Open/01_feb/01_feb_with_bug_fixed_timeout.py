"""
ID: mck15821
LANG: PYTHON3
PROG: Moo Language
"""
# http://usaco.org/index.php?page=viewproblem2&cpid=1323
N = int(input())
S = input()

# BE -> 0
# BB -> 1
# BFE -> 1
# BFB -> 0, 2
# BFFE -> BBBE, BBEE, BEBE, BEEE -> 0, 2
# BFFB -> BBBB, BBEB, BEBB, BEEB -> 1, 3
# BFFFE -> BBBBE, BBBEE, BBEBE, BBEEE, BEBBE, BEBEE, BEEBE, BEEEE -> 1, 3
# BFFFB -> BBBBB, BBBEB, BBEBB, BBEEB, BEBBB, BEBEB, BEEBB, BEEEB -> 0, 2, 4
# BFFFFE -> BBBBBE, BBBBEE, BBBEBE, BBBEEE, BBEBBE, BBEBEE, BBEEBE, BBEEEE, BEBBBE, BEBBEE, BEBEBE, BEBEEE, BEEBBE, BEEBEE, BEEEBE, BEEEEE -> 0, 2, 4
# BFFFFB -> BBBBBB, BBBBEB, BBBEBB, BBBEEB, BBEBBB, BBEBEB, BBEEBB, BBEEEB, BEBBBB, BEBBEB, BEBEBB, BEBEEB, BEEBBB, BEEBEB, BEEEBB, BEEEEB -> 1, 3, 5

# FF -> 0, 1
# FFF -> 0, 1, 2
# FFFF -> 0, 1, 2, 3

# if it's BXE, then:
# 1 -> (0,2) -> (1,3) -> (0,2,4) -> (1,3,5) -> (0,2,4,6) -> (1,3,5,7)
# if it's BXB, then:
# (0,2) -> (1,3) -> (0,2,4) -> (1,3,5) -> (0,2,4,6) -> (1,3,5,7)
# def get_possible_excitement(s):
#     result = []
#     length_of_f = len(s) - 2
#     if s[0] != s[-1]:
#         if length_of_f % 2 == 0:
#             return [i for i in range(0, length_of_f + 1, 2)]
#         return [i for i in range(1, length_of_f + 1, 2)]
#     if length_of_f % 2 == 0:
#         return [i for i in range(1, length_of_f + 2, 2)]
#     return [i for i in range(0, length_of_f + 2, 2)]

# def recursion(s):
#     excitement = [0]
#     for i in range(N - 1):
#         if S[i:i + 2] == "BB" or S[i:i + 2] == "EE":
#             excitement = [e + 1 for e in excitement]
#             i += 1
#         elif S[i:i + 2] == "BE" or S[i:i + 2] == "EB":
#             i += 1
#         elif S[i:i + 2] == "BF" or S[i:i + 2] == "EF":
#             new_excitement = []


    # return excitement


last_character = ["B", "E"]
excitement = {"B": set([0]), "E": set([0])}

for i in range(N - 1):
    print(S[:i + 2])
    if S[i:i + 2] == "BB" or S[i:i + 2] == "EE":
        new_excitement = dict()
        excitement[S[i + 1]] = set([e + 1 for e in excitement[S[i + 1]]])
        last_character = [S[i + 1]]
    elif S[i:i + 2] == "BE" or S[i:i + 2] == "EB":
        new_excitement = dict()
        new_excitement[S[i + 1]] = excitement[S[i + 0]]
        excitement = new_excitement
        last_character = [S[i + 1]]
    elif S[i:i + 2] == "BF" or S[i:i + 2] == "EF":
        # equal character
        new_excitement = dict()
        if S[i] == "B":
            new_excitement["B"] = set([e + 1 for e in excitement["B"]])
            new_excitement["E"] = excitement["B"]
        else:
            new_excitement["E"] = set([e + 1 for e in excitement["E"]])
            new_excitement["B"] = excitement["E"]
        excitement = new_excitement
        last_character = ["B", "E"]
    elif S[i:i + 2] == "FB" or S[i:i + 2] == "FE":
        new_excitement = dict()
        if S[i + 1] == "B":
            new_excitement["B"] = set([e + 1 for e in excitement["B"]]).union(excitement["E"])
        else:
            new_excitement["E"] = set([e + 1 for e in excitement["E"]]).union(excitement["B"])
        excitement = new_excitement
        last_character = [S[i + 1]]
    else:  # FF
        new_excitement = dict()
        new_excitement["B"] = set([e + 1 for e in excitement["B"]]).union(excitement["E"])
        new_excitement["E"] = set([e + 1 for e in excitement["E"]]).union(excitement["B"])
        excitement = new_excitement
        last_character = ["B", "E"]
    # print(last_character)
    # for k, v in excitement.items():
    #     print(k, list(v))


final = set()
for k, v in excitement.items():
    final = final.union(v)
    # print("final")
    # print(list(final))

print(len(final))
for e in sorted(list(final)):
    print(e)




