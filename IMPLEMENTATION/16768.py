import collections

n, k = map(int, input().split())
graph = [list(input()) for _ in range(n)]
m = 10
steps = [(-1, 0), (0, 1), (0, -1), (1, 0)]


def gravity():
    for j in range(m):
        list = []
        for i in range(n):
            if graph[i][j] != '0':
                list.append(graph[i][j])
                graph[i][j] = '0'
        for i in range(n - 1, 0, -1):
            if list:
                graph[i][j] = list.pop()

def solutions(num, i, j, visited):
    queue = collections.deque()
    queue.append((i, j))
    cnt = 1
    while queue:
        x, y = queue.popleft()
        for step in steps:
            nx, ny = x + step[0], y + step[1]
            if 0 <= nx < n and 0 <= ny < m and not visited[nx][ny] and graph[nx][ny] == num:
                queue.append((nx, ny))
                visited[nx][ny] = True
                cnt += 1
    return cnt

def distroy(i, j, curNumber):
    visited2[i][j] = True
    graph[i][j] = '0'
    queue = collections.deque()
    queue.append((i, j))
    while queue:
        x, y = queue.popleft()
        for step in steps:
            nx, ny = x + step[0], y + step[1]
            if 0 <= nx < n and 0 <= ny < m and not visited2[nx][ny] and graph[nx][ny] == curNumber:
                queue.append((nx, ny))
                graph[nx][ny] = '0'
                visited2[nx][ny] = True
while True:
    is_bool = False
    visited = [[False] * m for _ in range(n)]
    visited2 = [[False] * m for _ in range(n)]
    for i in range(n):
        for j in range(m):
            if not visited[i][j] and graph[i][j] != '0':
                visited[i][j] = True
                cnt  = solutions(graph[i][j], i, j, visited)
                if cnt >= k:
                    distroy(i, j, graph[i][j])
                    is_bool = True
    if not is_bool:
        for i in graph:
            print(*i, sep="")
        break
    gravity()