import collections

n = int(input())
graph = [list(map(int, input().split())) for _ in range(n)]
min_num = 1e9
visited = [[False] * n for _ in range(n)]
steps = [(-1, 0), (1, 0), (0, 1), (0, -1)]


def island_number(i, j, count):
    queue = collections.deque()
    visited[i][j] = True
    queue.append((i, j))
    graph[i][j] = count
    while queue:
        x, y = queue.popleft()
        for step in steps:
            nx, ny = x + step[0], y + step[1]
            if 0 <= nx < n and 0 <= ny < n and not visited[nx][ny] and graph[nx][ny] == 1:
                visited[nx][ny] = True
                graph[nx][ny] = count
                queue.append((nx, ny))


count = 1
for i in range(n):
    for j in range(n):
        if graph[i][j] == 1 and not visited[i][j]:
            island_number(i, j, count)
            count += 1

def solution(a):
    global min_num
    queue = collections.deque()
    distance = [[-1] * n for _ in range(n)]
    for i in range(n):
        for j in range(n):
            if graph[i][j] == a:
                distance[i][j] = 0
                queue.append((i, j))
    while queue:
        x, y = queue.popleft()
        for step in steps:
            nx, ny = x + step[0], y + step[1]
            if 0 <= nx < n and 0 <= ny < n and distance[nx][ny] == -1:
                if graph[nx][ny] != 0 and graph[nx][ny] != a:
                    min_num = min(min_num, distance[x][y])
                    return
                distance[nx][ny] = distance[x][y] + 1
                queue.append((nx, ny))
for i in range(1, max(map(max, graph)) + 1):
    solution(i)
print(min_num)