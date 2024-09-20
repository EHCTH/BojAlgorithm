N = int(input())
graph = [list(map(int, input().split())) for _ in range(N)]
visited = [False] * (2 * N - 1)
max_value = 0
# (0 + 4)
# (1 + 3)  (x + y)
# (2 + 2)
# (0 -2 + 4) 2
# (1 -3 + 4) 2
# (2 - 4 + 4) = 2 (x - y + n - 1)
data = [[] for _ in range(2 * N - 1)]
for i in range(N):
    for j in range(N):
        if graph[i][j]:
            data[i + j].append((i, j))


def solutions(n, data, cnt):
    global max_value
    if max_value >= cnt + (len(data) - n):
        return
    if n == len(data):
        max_value = max(max_value, cnt)
        return

    for x, y in data[n]:
        if not visited[x - y + N - 1]:
            visited[x - y + N - 1] = True
            solutions(n + 1, data, cnt + 1)
            visited[x - y + N - 1] = False
    solutions(n + 1, data, cnt)


solutions(0, data, 0)
print(max_value)