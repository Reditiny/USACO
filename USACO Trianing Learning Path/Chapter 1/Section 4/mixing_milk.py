"""
ID: mck15821
LANG: PYTHON3
PROG: milk
"""
#  Time Complexity: O(nlogn)
fin = open('milk.in', 'r')
fout = open("milk.out", "w")
total_demand, number_of_farmers = map(int, fin.readline().strip().split())
supplies = []
for i in range(number_of_farmers):
    amount, price = map(int, fin.readline().strip().split())
    supplies.append([amount, price])

supplies.sort(key=lambda x: x[0])

total_spent = 0
total_amount = 0
for supply in supplies:
    if supply[1] <= total_demand - total_amount:
        total_amount += supply[1]
        total_spent += (supply[0] * supply[1])
    else:
        total_spent += ((total_demand - total_amount) * supply[0])
        break

fout.write(f"{total_spent}\n")
fout.close()

