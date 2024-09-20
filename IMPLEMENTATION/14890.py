n, l = map(int, input().split())
graph = [list(map(int, input().split())) for _ in range(n)]
tmp = [[0] * n for _ in range(n)]
for i in range(n):
    for j in range(n):
       tmp[i][j] = graph[n - 1 - j][i]
graph = graph + tmp
result = 0
n , m = n * 2, n
for i in range(n):
    start = 1
    for j in range(m - 1):
        if graph[i][j] == graph[i][j + 1]:
            start += 1
        elif graph[i][j] + 1 == graph[i][j + 1] and start >= l:
            start = 1
        elif graph[i][j] - 1 == graph[i][j + 1] and start >= 0:
            start = (1 - l)
        else:
            start = -1
            break
    if j + 1 == m - 1 and start >= 0 :
        result += 1
print(result)

# 3 2 3
# 2 3 2
# 3 2 3
# 3 2 3
# 2 3 2
# 3 2 3