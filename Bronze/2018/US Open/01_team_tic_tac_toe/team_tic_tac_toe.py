
fin = open('tttt.in', 'r')
fout = open("tttt.out", "w")
# 单牛获胜
def single_win(target, board):
    if board[0][0] == target and board[1][1] == target and board[2][2] == target:
        return True
    if board[0][2] == target and board[1][1] == target and board[2][0] == target:
        return True
    for i in range(3):
        if board[i][0] == target and board[i][1] == target and board[i][2] == target:
            return True
        if board[0][i] == target and board[1][i] == target and board[2][i] == target:
            return True
    return False
# 双牛获胜
def team_win(target1, target2, board):
    for i in range(3):
        if match2(target1, target2, board[i][0], board[i][1], board[i][2]):
            return True
        if match2(target1, target2, board[0][i], board[1][i], board[2][i]):
            return True
    if match2(target1, target2, board[0][0], board[1][1], board[2][2]):
        return True
    if match2(target1, target2, board[0][2], board[1][1], board[2][0]):
        return True
    return False
def match2(target1, target2, c1, c2, c3):
    return (c1 == target1 and c2 == target2 and c3 == target2) or \
        (c1 == target2 and c2 == target1 and c3 == target2) or \
        (c1 == target2 and c2 == target2 and c3 == target1) or \
        (c1 == target2 and c2 == target1 and c3 == target1) or \
        (c1 == target1 and c2 == target2 and c3 == target1) or \
        (c1 == target1 and c2 == target1 and c3 == target2)

board = [list(fin.readline().strip()) for _ in range(3)]
single_count = 0
team_count = 0

for c1 in range(ord('A'), ord('Z') + 1):
    # 单牛获胜
    if single_win(chr(c1), board):
        single_count += 1
    for c2 in range(ord('Z'), c1, -1):
        # 双牛获胜，team_win中已实现ZA和AZ为相同组合
        if team_win(chr(c1), chr(c2), board):
            team_count += 1

fout.write(f"{single_count}\n{team_count}\n")
fout.close()



