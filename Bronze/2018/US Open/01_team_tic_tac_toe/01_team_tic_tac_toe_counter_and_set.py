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
visited_single = set()
visited_team = set()
for winner in winners:
    winner_counter = Counter(winner)
    if len(winner_counter) == 1:
        if winner in visited_single:
            continue
        single_wins += 1
        visited_team.add(winner)
    elif len(winner_counter) == 2:
        key_list = list(winner_counter.keys())
        if tuple(key_list) in visited_team or tuple(key_list[::-1]) in visited_team:
            continue
        team_wins += 1
        visited_team.add(tuple(key_list))

fout.write(f"{single_wins}\n{team_wins}\n")
fout.close()
