import collections
n, m, k = map(int, input().split())
graph = [list(map(int, input())) for _ in range(n)]
distance = [[[0] * m for _ in range(n)] for _ in range(k + 1)]
steps = [(-1, 0), (1, 0), (0, -1), (0, 1)]
#
#
def bfs(x, y):
    queue = collections.deque()
    queue.append((x, y, 0))
    distance[0][0][0] = 1
    cnt = 0
    while queue:
        x, y, t = queue.popleft()
        if x == n - 1 and y == m - 1:
            return distance[t][x][y]
        for step in steps:
            nx, ny = x + step[0], y + step[1]
            if 0 <= nx < n and 0 <= ny < m and not distance[t][nx][ny]:
                if graph[nx][ny] == 1 and t != k and not distance[t + 1][nx][ny]:
                    distance[t + 1][nx][ny] = distance[t][x][y] + 1
                    queue.append((nx, ny, t + 1))
                elif graph[nx][ny] == 0:
                    distance[t][nx][ny] = distance[t][x][y] + 1
                    queue.append((nx, ny, t))
    return -1
print(bfs(0, 0))