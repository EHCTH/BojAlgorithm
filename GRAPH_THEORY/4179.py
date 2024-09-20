import collections
n, m = map(int, input().split())
graph = [list(input()) for _ in range(n)]
queue1 = collections.deque()
queue2 = collections.deque()
distance1 = [[-1] * m for _ in range(n)]
distance2 = [[-1] * m for _ in range(n)]
steps = [(-1, 0 ), (1, 0), (0, 1), (0, -1)]
for i in range(n):
    for j in range(m):
        if graph[i][j] == "J":
            queue1.append((i, j))
            distance1[i][j] = 0
        if graph[i][j] == "F":
            queue2.append((i, j))
            distance2[i][j] = 0
def solutions():
    while queue2:
        x, y = queue2.popleft()
        for step in steps:
            nx, ny = x + step[0], y + step[1]
            if 0 <= nx < n and 0 <= ny < m and distance2[nx][ny] == -1 and graph[nx][ny] != '#':
                distance2[nx][ny] = distance2[x][y] + 1
                queue2.append((nx, ny))
    while queue1:
        x, y = queue1.popleft()
        for step in steps:
            nx, ny = x + step[0], y + step[1]
            if nx < 0 or nx >= n or ny < 0 or ny >= m:
                return distance1[x][y] + 1
            if 0 <= nx < n and 0 <= ny < m and distance1[nx][ny] == -1 and graph[nx][ny] != '#':
                if (distance1[x][y] + 1 < distance2[nx][ny]) or distance2[nx][ny] == -1:
                    distance1[nx][ny] = distance1[x][y] + 1
                    queue1.append((nx, ny))
    return "IMPOSSIBLE"
print(solutions())