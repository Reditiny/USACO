"""
ID: mck15821
LANG: PYTHON3
PROG: measurement
"""
# http://www.usaco.org/index.php?page=viewproblem2&cpid=761
fin = open('measurement.in', 'r')
fout = open("measurement.out", "w")

N = int(fin.readline().strip())
records = []
for _ in range(N):
    timestamp, name, diff = fin.readline().strip().split()
    records.append([int(timestamp), name, int(diff)])

records.sort(key=lambda x: x[0])
count = 0
production = [7, 7, 7]  # Bessie, Elsie, and Mildred
board = [1, 1, 1]

for record in records:
    if record[1] == "Bessie":
        index = 0
    elif record[1] == "Elsie":
        index = 1
    else:
        index = 2
    production[index] += record[2]
    # leaderboard logic: find max value and index
    max_value = max(production)

    # leaderboard logic: find new board
    new_board = [0, 0, 0]
    for i in range(3):
        if production[i] == max_value:
            new_board[i] = 1

    if board != new_board:
        count += 1
        board = new_board

fout.write(f"{count}\n")
fout.close()
