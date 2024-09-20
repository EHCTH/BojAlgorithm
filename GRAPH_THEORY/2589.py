import collections

n, m = map(int, input().split())
graph = [list(input()) for _ in range(n)]
steps = [(-1, 0), (1, 0), (0, -1), (0, 1)]

def bfs(x, y):
    distance = [[-1] * m for _ in range(n)]
    distance[x][y] = 0
    queue = collections.deque()
    queue.append((x, y))
    while queue:
        x, y = queue.popleft()
        for step in steps:
            nx, ny = x + step[0], y + step[1]
            if 0 <= nx < n and 0 <= ny < m and graph[nx][ny] == 'L' and distance[nx][ny] == -1:
                distance[nx][ny] = distance[x][y] + 1
                queue.append((nx, ny))
    return max(map(max, distance))

answer = 0
for i in range(n):
    for j in range(m):
        if graph[i][j] == 'L':
            answer = max(answer, bfs(i,j))
print(answer)