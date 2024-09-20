import collections

n, m = map(int, input().split())
graph = [list(map(int, input().split())) for _ in range(n)]
steps = [(-1, -1), (-1, 0), (-1, 1), (0, 1), (1, 1), (1, 0), (1, -1), (0, -1)]
max_value = -1e9
queue = collections.deque()
for i in range(n):
    for j in range(m):
        if graph[i][j] == 1:
            queue.append((i, j))
while queue:
    x, y = queue.popleft()
    for step in steps:
        nx, ny = x + step[0], y + step[1]
        if 0 <= nx < n and 0 <= ny < m and not graph[nx][ny]:
            graph[nx][ny] = graph[x][y] + 1
            queue.append((nx, ny))


print(max(map(max, graph)) - 1)