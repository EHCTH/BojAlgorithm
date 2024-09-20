import collections

k = int(input())
m, n = map(int, input().split())
graph = [list(map(int, input().split())) for _ in range(n)]
distance = [[[0] * m for _ in range(n)] for _ in range(k + 1)]
steps = [(-2, -1), (-2, 1), (2, -1), (2, 1), (-1, -2), (1, -2), (-1, 2), (1, 2)]
other_steps = [(-1, 0), (1, 0), (0, -1), (0, 1)]
def bfs(z, x, y):
    queue = collections.deque()
    queue.append((z, x, y))
    distance[z][x][y] = 0
    while queue:
        z, x, y = queue.popleft()
        if x == n - 1 and y == m - 1:
            return distance[z][x][y]
        for other in other_steps:
            nx, ny = x + other[0], y + other[1]
            if 0 <= nx < n and 0 <= ny < m and graph[nx][ny] == 0:
                if not distance[z][nx][ny]:
                    distance[z][nx][ny] = distance[z][x][y] + 1
                    queue.append((z, nx, ny))

        for step in steps:
            nx, ny = x + step[0], y + step[1]
            if 0 <= nx < n and 0 <= ny < m and graph[nx][ny] == 0 and z != k:
                if not distance[z + 1][nx][ny]:
                    distance[z + 1][nx][ny] = distance[z][x][y] + 1
                    queue.append((z + 1, nx, ny))

    return - 1
print(bfs(0, 0, 0))