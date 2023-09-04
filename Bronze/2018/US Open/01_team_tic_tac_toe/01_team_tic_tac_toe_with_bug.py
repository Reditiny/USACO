"""
ID: mck15821
LANG: PYTHON3
PROG: tttt
"""
from collections import Counter
# http://www.usaco.org/index.php?page=viewproblem2&cpid=831
fin = open('tttt.in', 'r')
fout = open("tttt.out", "w")

board = []
for i in range(3):
    board.append(list(fin.readline().strip()))


def check_win(board):
    result = set()
    for i in range(3):
        result.add(board[i][0] + board[i][1] + board[i][2]) # rows
        result.add(board[0][i] + board[1][i] + board[2][i]) # columns
    result.add(board[0][0] + board[1][1] + board[2][2]) # diagonal
    result.add(board[0][2] + board[1][1] + board[2][0]) # diagonal
    return list(result)


winners = check_win(board)
single_wins = 0
team_wins = 0
visited = set()
for winner in winners:

    winner = "".join(sorted(winner))
    winner_counter = Counter(winner)
    if winner in visited:
        continue
    if len(winner_counter) == 1:
        single_wins += 1
    elif len(winner_counter) == 2:
        team_wins += 1
    visited.add(winner)
    print(visited)

fout.write(f"{single_wins}\n{team_wins}\n")
fout.close()
