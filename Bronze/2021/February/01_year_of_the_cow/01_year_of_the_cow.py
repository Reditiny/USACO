"""
ID: mck15821
LANG: PYTHON3
PROG: Year of the cow
"""
# http://usaco.org/index.php?page=viewproblem2&cpid=1107

years = {
    "Ox": 0,
    "Tiger": 1,
    "Rabbit": 2,
    "Dragon": 3,
    "Snake": 4,
    "Horse": 5,
    "Goat": 6,
    "Monkey": 7,
    "Rooster": 8,
    "Dog": 9,
    "Pig": 10,
    "Rat": 11
}

cows = {"bessie": (0, "Ox")}  # absolute number, zodiac

N = int(input())
for _ in range(N):
    record = input().split()
    target, source = record[0], record[-1]
    target_zodiac = record[4]
    source_age, source_zodiac = cows[source]
    age_diff = years[source_zodiac] - years[target_zodiac]
    if record[3] == "previous":
        cows[target] = (source_age + years[cows[source][1]] - years[cows[target][1]], target_zodiac)
    else:
        cows[target] = (cows[source] + 12 + target_zodiac_rank) % 12
