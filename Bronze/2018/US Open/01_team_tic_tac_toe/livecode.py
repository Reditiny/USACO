fin = open("tttt.in", "r")
fout = open("tttt.out", "w")

board = []
for _ in range(3):
    board.append(fin.readline().strip())

# 1. Get all the winning possibilities
def get_winning(board):
    result = []
    for i in range(3):
        # check rows
        result.append(board[i])
        # check columns
        result.append(board[0][i] + board[1][i] + board[2][i])
    # check diagonal
    result.append(board[0][0] + board[1][1] + board[2][2])
    result.append(board[0][2] + board[1][1] + board[2][0])
    return result

possibilities = get_winning(board)

single_wins = set()
team_wins = set()
# 2. count wins for each possibility
for s in possibilities:
    if s[0] == s[1] == s[2]:
        single_wins.add(s[0])
    elif s[0] == s[1] or s[1] == s[2] or s[0] == s[2]:
        deduplicated_teams = list(set(list(s)))  # XXO -> ["X", "X", "O"] -> ("X", "O") -> ["X", "O"]
        team = tuple(sorted(deduplicated_teams))  # sort to guarantee XO and OX is treated as the same team; tuple to be hashable in set
        team_wins.add(team)

fout.write(f"{len(single_wins)}\n")
fout.write(f"{len(team_wins)}\n")
fout.close()
