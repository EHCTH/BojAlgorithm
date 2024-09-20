import collections
n, m = map(int, input().split())
graph = [[[] for _ in range(n + 1)] for _ in range(n + 1)]
for i in range(m):
    x, y, a, b = map(int, input().split())
    graph[x][y].append((a, b))
visited = [[False] * (n + 1) for _ in range(n + 1)]
light = [[False] * (n + 1) for _ in range(n + 1)]
steps = [(-1, 0), (1, 0), (0, -1), (0, 1)]


def solutions(x, y):
    answer = 1
    queue = collections.deque()
    queue.append((x, y))
    visited[x][y] = True
    light[x][y] = True
    while queue:
        x, y = queue.popleft()
        for i in graph[x][y]:
            a, b = i
            if not light[a][b]:
                light[a][b] = True
                answer += 1
                for step in steps:
                    na, nb = a + step[0], b + step[1]
                    if 1 <= na < n + 1 and 1 <= nb < n + 1 and visited[na][nb]:
                        queue.append((na, nb))
        for step in steps:
            nx, ny = x + step[0], y + step[1]
            if 1 <= nx < n + 1 and 1 <= ny < n + 1 and light[nx][ny] and not visited[nx][ny]:
                visited[nx][ny] = True
                queue.append((nx, ny))
    return answer
print(solutions(1, 1))