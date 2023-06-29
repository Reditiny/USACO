"""
ID: mck15821
LANG: PYTHON3
PROG: gift1
"""
fin = open("gift1.in", "r")
fout = open("gift1.out", "w")
number_of_friends = int(fin.readline())
friends = {}
for i in range(number_of_friends):
    friends[fin.readline().strip()] = 0

while True:
    sender = fin.readline().strip()
    if not sender:
        break
    amount, split_into = map(int, fin.readline().split())
    if split_into == 0:
        continue
    amount_to_give_per_person = amount // split_into
    amount_to_charge_self = amount % split_into
    friends[sender] = friends[sender] - amount + amount_to_charge_self

    for i in range(split_into):
        friends[fin.readline().strip()] += amount_to_give_per_person

for key, value in friends.items():
    fout.write(f"{key} {value}\n")
fout.close()
