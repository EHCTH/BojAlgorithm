import collections
n, m = map(int, input().split())
graph = [list(map(int, input())) for _ in range(n)]
distance = [[[0] * m for _ in range(n)] for _ in range(2)]
steps = [(-1, 0), (1, 0), (0, -1), (0, 1)]

def bfs(x, y):
    queue = collections.deque()
    queue.append((x, y, 0))
    distance[0][0][0] = 1
    while queue:
        x, y, d = queue.popleft()
        if x == n - 1 and y == m - 1:
            return distance[d][x][y]
        for step in steps:
            nx, ny = x + step[0], y + step[1]
            if 0 <= nx < n and 0 <= ny < m and not distance[d][nx][ny]:
                if graph[nx][ny] == 1 and d != 1:
                    distance[d + 1][nx][ny] = distance[d][x][y] + 1
                    queue.append((nx, ny, d + 1))
                elif graph[nx][ny] == 0:
                    distance[d][nx][ny] = distance[d][x][y] + 1
                    queue.append((nx, ny, d))
    return -1
print(bfs(0, 0))