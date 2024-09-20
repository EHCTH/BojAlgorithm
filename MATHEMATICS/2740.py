n, m = map(int, input().split())
a = [list(map(int, input().split())) for _ in range(n)]
m, k = map(int, input().split())
b = [list(map(int, input().split())) for _ in range(m)]

mul = [[0]*k for _ in range(n)]
for i in range(n):
    for j in range(k):
        e = 0
        for l in range(m):
            e += a[i][l] * b[l][j]
        mul[i][j] = e

for i in mul:
    print(*i)