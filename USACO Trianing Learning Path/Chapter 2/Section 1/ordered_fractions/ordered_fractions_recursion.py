"""
ID: mck15821
LANG: PYTHON3
PROG: frac1
"""
fin = open('frac1.in', 'r')
fout = open("frac1.out", "w")

N = int(fin.readline().strip())


def genfrac(top1, bottom1, top2, bottom2):
    if bottom1 + bottom2 <= N:
        genfrac(top1, bottom1, top1 + top2, bottom1 + bottom2)
        fout.write(f"{top1 + top2}/{bottom1 + bottom2}\n")
        genfrac(top1 + top2, bottom1 + bottom2, top2, bottom2)

fout.write(f"0/1\n")
genfrac(0, 1, 1, 1)
fout.write(f"1/1\n")

fout.close()
