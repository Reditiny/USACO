"""
ID: mck15821
LANG: PYTHON3
PROG: milk3
"""

fin = open('milk3.in', 'r')
fout = open("milk3.out", "w")


# a,b and c are for current volume; aCap, bCap and cCap are for capacity;
# times to control the end of iteration
# which to identify the move
# temp to mark the final answer
# checked to memorize number visited
def solve(a, b, c, aCap, bCap, cCap, times, which, temp, checked):
    # print(a,b,c,aCap,bCap,cCap,times,which,temp)
    # print(checked[a][b][c])
    if checked[a][b][c][which] is True:
        return temp
    # have not done any change
    if times == 0:
        if a == 0:
            temp[c] = True
        # try all the 6 operations
        for i in range(6):
            temp = solve(a, b, c, aCap, bCap, cCap, times + 1, i, temp, checked)
    # the max operation times is cCap
    elif times < cCap and 0 <= a <= aCap and 0 <= b <= bCap and 0 <= c <= cCap:
        if which == 0:
            # a -> b
            if bCap >= a + b:
                b += a
                a = 0
            else:
                a -= (bCap - b)
                b = bCap
        elif which == 1:
            # a -> c
            if cCap >= a + c:
                c += a
                a = 0
            else:
                a -= (cCap - c)
                c = cCap
        elif which == 2:
            # b -> a
            if aCap >= a + b:
                a += b
                b = 0
            else:
                b -= (aCap - a)
                a = aCap
        elif which == 3:
            # b -> c
            if cCap >= b + c:
                c += b
                b = 0
            else:
                b -= (cCap - c)
                c = cCap
        elif which == 4:
            # c -> a
            if aCap >= a + c:
                a += c
                c = 0
            else:
                c -= (aCap - a)
                a = aCap
        elif which == 5:
            # c -> b
            if bCap >= b + c:
                b += c
                c = 0
            else:
                c -= (bCap - b)
                b = bCap

        # connection among a, b and c; e.g., a->b is 0, and b->a is 2
        linked = [2, 4, 0, 5, 1, 3]
        if a == 0:
            temp[c] = True

        for i in range(6):
            # if i is which then nothing new; if i is linked[which] then it's a->b->a
            if i != which and i != linked[which]:
                temp = solve(a, b, c, aCap, bCap, cCap, times + 1, i, temp, checked)

    else:
        return temp

    checked[a][b][c][which] = True
    return temp


aCap, bCap, cCap = map(int, fin.readline().strip().split())
a, b, c = 0, 0, cCap
temp = [False for i in range(cCap + 1)]
checked = []
for i in range(aCap + 1):
    arr1 = []
    for j in range(bCap + 1):
        arr2 = []
        for k in range(cCap + 1):
            arr3 = [False for _ in range(6)]
            arr2.append(arr3)
        arr1.append(arr2)
    checked.append(arr1)

temp = solve(a, b, c, aCap, bCap, cCap, 0, 0, temp, checked)

everTrue = False
for i in range(len(temp)):
    if temp[i]:
        fout.write(f"{i}")
        everTrue = True
        if i < len(temp) - 1:
            fout.write(" ")

if not everTrue:
    fout.write("NONE")
fout.write("\n")
fout.close()