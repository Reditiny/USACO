"""
ID: mck15821
LANG: PYTHON3
PROG: Walking Home
"""
# Time complexity: O(V * N), V as maximum difference
# http://usaco.org/index.php?page=viewproblem2&cpid=1157
T = int(input())


for _ in range(T):
    N, K = map(int, input().strip().split())
    grid = []
    for x in range(N):
        grid.append(input())
    # if K == 1 meaning only turn at top right or bottom left
    answer = 0
    if K >= 1:
        for i in range(N):
            if grid[0][i] == "H" or grid[N - 1][i] == "H":
                break
        else:
            answer += 1
        for i in range(N):
            if grid[i][0] == "H" or grid[i][N - 1] == "H":
                break
        else:
            answer += 1
    if K >= 2:
        column_of_last_h_last_row = 1
        # finding the last H of last row
        for i in range(N - 1, -1, -1):
            if grid[N - 1][i] == "H":
                column_of_last_h_last_row = i + 1
                break
        # count how many column path without blocker
        for j in range(column_of_last_h_last_row, N):
            for i in range(N):
                if grid[i][j] == "H":
                    break
            else:
                answer += 1

        row_of_last_h_last_col = 1
        for i in range(N - 1, -1, -1):
            if grid[i][N - 1] == "H":
                row_of_last_h_last_col = i + 1
                break
        for i in range(row_of_last_h_last_col, N):
            for j in range(N):
                if grid[i][j] == "H":
                    break
            else:
                answer += 1

    else:
        # find a spot as intermediate point, count number of ways to the spot * number of ways from spot to goal
        ways_to_goal = [[0] * N for _ in range(N)]
        ways_to_goal[N - 1][N - 1] = 1
        for i in range(N - 2, -1, -1):
            if grid[N - 1][i] == "H":
                break
            ways_to_goal[N - 1][i] = 1
        for i in range(N - 2, -1, -1):
            if grid[i][N - 1] == "H":
                break
            ways_to_goal[i][N - 1] = 1

        for i in range(N - 2, -1, -1):
            for j in range(N - 2, -1, -1):
                if grid[i][j] != "H":
                    count = 0
                    for k in range(i + 1, N):
                        if grid[k][j] == "H":
                            break
                    else:
                        count += 1
                    for k in range(j + 1, N):
                        if grid[i][k] == "H":
                            break
                    else:
                        count += 1
                    ways_to_goal[i][j] = count

        for line in ways_to_goal:
            print(line)
        print()

        ways_from_start = [[0] * N for _ in range(N)]
        ways_from_start[0][0] = 1
        for i in range(1, N):
            if grid[0][i] == "H":
                break
            ways_from_start[0][i] = 1
        for i in range(1, N):
            if grid[i][0] == "H":
                break
            ways_from_start[i][0] = 1

        for i in range(1, N):
            for j in range(1, N):
                if grid[i][j] != "H":
                    count = 0
                    for k in range(i - 1, -1, -1):
                        if grid[k][j] == "H":
                            break
                    else:
                        count += 1
                    for k in range(j - 1, -1, -1):
                        if grid[i][k] == "H":
                            break
                    else:
                        count += 1
                    ways_from_start[i][j] = count

        for line in ways_from_start:
            print(line)

        for i in range(1, N - 1):
            for j in range(1, N - 1):
                answer += ways_from_start[i][j] * ways_to_goal[i][j]
        answer += ways_from_start[0][0] * ways_to_goal[0][0]
        print(answer)









