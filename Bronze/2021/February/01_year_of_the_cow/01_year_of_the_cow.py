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

cows = {"Bessie": (0, "Ox")}  # absolute number, zodiac

N = int(input())
for _ in range(N):
    record = input().split()
    target, source = record[0], record[-1]
    target_zodiac = record[4]
    source_age, source_zodiac = cows[source]
    if record[3] == "previous":
        age_diff = years[source_zodiac] - years[target_zodiac]
        if age_diff <= 0:
            age_diff += 12
        cows[target] = (cows[source][0] - age_diff, target_zodiac)
    else:
        age_diff = years[target_zodiac] - years[source_zodiac]
        if age_diff <= 0:
            age_diff += 12
        cows[target] = (cows[source][0] + age_diff, target_zodiac)
    if target == "Elsie":
        print(abs(cows[target][0]))
