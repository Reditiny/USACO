"""
ID: mck15821
LANG: PYTHON3
PROG: hps
"""
# http://www.usaco.org/index.php?page=viewproblem2&cpid=688
fin = open('hps.in', 'r')
fout = open("hps.out", "w")
N = int(fin.readline().strip())
rounds = []
for _ in range(N):
    rounds.append(list(map(int, fin.readline().strip().split())))

gestures = ["hoof", "paper", "scissor"]
combinations = []
for i in range(3):
    g = [gestures[i]]
    gc = gestures.copy()
    gc.remove(gestures[i])
    combinations.append(g + gc)
    combinations.append(g + gc[::-1])

max_count = 0
for comb in combinations:
    counter = 0
    for r in rounds:
        gesture_a = comb[r[0] - 1]
        gesture_b = comb[r[1] - 1]
        if (gesture_a == "hoof" and gesture_b == "scissor") or (gesture_a == "scissor" and gesture_b == "paper") or (gesture_a == "paper" and gesture_b == "hoof"):
            counter += 1
    max_count = max(max_count, counter)
fout.write(f"{max_count}")
fout.close()
