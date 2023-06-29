"""
ID: mck15821
LANG: PYTHON3
PROG: ride
"""
fin = open("ride.in", "r")
fout = open("ride.out", "w")
comet = fin.readline()
group = fin.readline()
comet_result = 1
group_result = 1
for c in comet:
    comet_result = comet_result * (ord(c) - ord("A") + 1) % 47
for c in group:
    group_result = group_result * (ord(c) - ord("A") + 1) % 47

fout.write(("GO" if comet_result == group_result else "STAY") + "\n")
fout.close()