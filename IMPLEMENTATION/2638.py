import collections

n, m = map(int, input().split())
graph = [list(map(int, input().split())) for _ in range(n)]
steps = [(-1, 0),(1, 0),(0, -1),(0, 1)]
cnt = 0
def melitng(visited):
    global cnt
    for i in range(n):
        for j in range(m):
            if visited[1][i][j] >= 2:
                graph[i][j] = 0
    cnt += 1
def solutions(x, y):
    queue = collections.deque()
    queue2 = collections.deque()
    queue2.append((x, y))
    queue.append((x, y))
    visited = [[[0] * m for _ in range(n)] for _ in range(2)]
    visited[0][x][y] = 1
    while queue:
        x, y = queue.popleft()
        for step in steps:
            nx, ny = x + step[0], y + step[1]
            if 0 <= nx < n and 0 <= ny < m and not graph[nx][ny] and not visited[0][nx][ny]:
                visited[0][nx][ny] = 1
                queue.append((nx, ny))
                queue2.append((nx, ny))
    while queue2:
        x, y = queue2.popleft()
        for step in steps:
            nx, ny = x + step[0], y + step[1]
            if 0 <= nx < n and 0 <= ny < m and graph[nx][ny]:
                visited[1][nx][ny] += 1
    melitng(visited)

for i in range(n):
    for j in range(m):
        if not graph[i][j] and sum([sum(item) for item in graph]):
            solutions(i, j)
print(cnt)