
fin = open('hps.in', 'r')
fout = open("hps.out", "w")

N = int(fin.readline())
rules = [['H', 'P', 'S'], ['P', 'S', 'H'], ['S', 'H', 'P'], ['H', 'S', 'P'], ['P', 'H', 'S'], ['S', 'P', 'H']]
gestures = [list(map(lambda x: int(x) - 1, fin.readline().split())) for _ in range(N)]
ans = 0

def check(rule, gesture):
    cow1 = rule[gesture[0]]
    cow2 = rule[gesture[1]]
    if (cow1 == 'H' and cow2 == 'S') or (cow1 == 'P' and cow2 == 'H') or (cow1 == 'S' and cow2 == 'P'):
        return 1
    return 0

for rule in rules:
    cur_ans = 0
    for gesture in gestures:
        cur_ans += check(rule, gesture)
    ans = max(ans, cur_ans)

fout.write(str(ans) + "\n")
