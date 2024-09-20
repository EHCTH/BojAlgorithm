import math
a, b, v = map(int, input().split())
total = (v - b) / (a - b)
print(math.ceil(total))