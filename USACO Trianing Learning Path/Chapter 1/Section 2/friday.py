"""
ID: mck15821
LANG: PYTHON3
PROG: friday
"""
fin = open("friday.in", "r")
fout = open("friday.out", "w")
N = int(fin.readline())


def is_leap_year(year):
    if year % 4 != 0:
        return False
    if year % 400 == 0:
        return True
    return year % 100 != 0


def get_days(year):
    return [31, 29 if is_leap_year(year) else 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]


result = [0] * 7
start = 2  # 1900 Monday
year_start = 1900
year_end = year_start + N

while year_start < year_end:
    for day in get_days(year_start):
        result[(start + 12) % 7] += 1
        start = (start + day) % 7
    year_start += 1

for i in range(7):
    result[i] = str(result[i])

fout.write(" ".join(result) + "\n")
fout.close()
