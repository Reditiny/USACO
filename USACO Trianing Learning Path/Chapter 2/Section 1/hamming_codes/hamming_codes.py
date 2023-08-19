"""
ID: mck15821
LANG: PYTHON3
PROG: hamming
"""
fin = open('hamming.in', 'r')
fout = open("hamming.out", "w")

N, B, D = list(map(int, fin.readline().strip().split()))
nums = set()
num = 0


def hamming_distance(target):
    for n in nums:
        if (n ^ target).bit_count() < D:
            return False
    return True


while len(nums) < N:
    if hamming_distance(num):
        nums.add(num)
        if len(nums) % 10 == 0 or len(nums) == N:
            fout.write(f"{num}\n")
        else:
            fout.write(f"{num} ")
    num += 1

fout.close()
