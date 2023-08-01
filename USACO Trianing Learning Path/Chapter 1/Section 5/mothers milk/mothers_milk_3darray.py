"""
ID: mck15821
LANG: PYTHON3
PROG: milk3
"""

fin = open('milk3.in', 'r')
fout = open("milk3.out", "w")


def search(state):
    if seen[state[0]][state[1]][state[2]] is True:
        return
    seen[state[0]][state[1]][state[2]] = True
    # a is empty, mark current c as valid
    if state[0] == 0:
        canget[state[2]] = True
    for i in range(3):
        for j in range(3):
            if i != j and state[i] != 0 and state[j] < caps[j]:
                new_state = pour(state.copy(), i, j)
                if seen[new_state[0]][new_state[1]][new_state[2]] is True:
                    continue
                search(new_state)


def pour(state, source, target):
    amount = state[source]
    if state[target] + amount > caps[target]:
        amount = caps[target] - state[target]
    state[source] -= amount
    state[target] += amount
    return state


caps = list(map(int, fin.readline().strip().split()))
cur = [0, 0, caps[2]]
seen = []
canget = [False for i in range(caps[2] + 1)]

# init seen
for i in range(caps[0] + 1):
    arr1 = []
    for j in range(caps[1] + 1):
        arr2 = [False for _ in range(caps[2] + 1)]
        arr1.append(arr2)
    seen.append(arr1)

search(cur)

everTrue = False
for i in range(len(canget)):
    if canget[i] is True:
        fout.write(f"{i}")
        everTrue = True
        if i < len(canget) - 1:
            fout.write(" ")

if not everTrue:
    fout.write("NONE")
fout.write("\n")
fout.close()
