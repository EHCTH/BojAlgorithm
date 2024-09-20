import collections
m, n = map(int, input().split())
graph = [list(map(int, input())) for _ in range(n)]

steps = [(-1, 0), (1, 0), (0, -1), (0, 1)]
distance = [[-1] * m for _ in range(n)]
distance[0][0] = 0
def dfs(x, y):
    queue = collections.deque()
    queue.append((x, y))
    while queue:
        x, y = queue.popleft()
        if x == n -1 and y == m - 1:
            return distance[n -1][m - 1]
        for step in steps:
            nx, ny = x + step[0], y + step[1]
            if 0 <= nx < n and 0 <= ny < m and distance[nx][ny] == -1:
                if graph[nx][ny] == 0:
                    distance[nx][ny] = distance[x][y]
                    queue.appendleft((nx, ny))
                else:
                    distance[nx][ny] = distance[x][y] + 1
                    queue.append((nx, ny))

print(dfs(0, 0))