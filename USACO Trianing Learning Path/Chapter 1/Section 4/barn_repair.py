"""
ID: mck15821
LANG: PYTHON3
PROG: barn1
"""
#  Time Complexity: O(nlogn)
fin = open('barn1.in', 'r')
fout = open("barn1.out", "w")
number_of_boards, number_of_slots, number_of_cows = map(int, fin.readline().strip().split())
# If more boards than cows, than can cover each of the cow
if number_of_boards >= number_of_cows:
    total_coverage = number_of_cows
else:
    cows = []
    for i in range(number_of_cows):
        cows.append(int(fin.readline().strip()))
    cows.sort()

    # Start with 1 board, covering the first to the last cow
    total_coverage = cows[-1] - cows[0] + 1
    # Find the biggest gap to have the smallest coverage
    gaps = []
    for i in range(1, number_of_cows):
        gaps.append(cows[i] - cows[i - 1])
    gaps.sort(reverse=True)

    # Split number_of_boards - 1 times
    for i in range(number_of_boards - 1):
        total_coverage = total_coverage - gaps[i] + 1


fout.write(f"{total_coverage}\n")
fout.close()

