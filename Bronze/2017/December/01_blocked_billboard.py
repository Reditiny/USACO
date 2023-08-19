"""
ID: mck15821
LANG: PYTHON3
PROG: billboard
"""
fin = open('billboard.in', 'r')
fout = open("billboard.out", "w")

class Rect:
    def __init__(self, x1, y1, x2, y2):
        self.x1, self.y1, self.x2, self.y2 = x1, y1, x2, y2

    def area(self):
        return (self.x2 - self.x1) * (self.y2 - self.y1)

    def overlap(self, other):
        x_overlap = max(0, min(self.x2, other.x2) - max(self.x1, other.x1))
        y_overlap = max(0, min(self.y2, other.y2) - max(self.y1, other.y1))
        return x_overlap * y_overlap

    def __repr__(self):
        return f"({self.x1}, {self.y1}, {self.x2}, {self.y2})"


billboard1 = Rect(*map(int, fin.readline().strip().split()))
billboard2 = Rect(*map(int, fin.readline().strip().split()))
truck = Rect(*map(int, fin.readline().strip().split()))

area = billboard1.area() + billboard2.area() - billboard1.overlap(truck) - billboard2.overlap(truck)

fout.write(f"{area}\n")
fout.close()
