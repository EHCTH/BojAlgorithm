maxA = -1e9
minA = 1e9

maxB = -1e9
minB = 1e9
n = int(input())
for _ in range(n):
    a, b = map(int, input().split())
    maxA = max(maxA, a)
    minA = min(minA, a)

    maxB =max(maxB, b)
    minB = min(minB, b)

print((maxA - minA) * (maxB - minB))