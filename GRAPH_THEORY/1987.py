n, m = map(int, input().split())
steps = [(-1, 0), (1, 0), (0, 1), (0, -1)]
graph = [list(input())for _ in range(n)]

def bfs(x, y):
    cnt = 1
    queue = set([(x, y, graph[x][y])])
    while queue:
        x, y, alphabet = queue.pop()
        cnt = max(cnt, len(alphabet))
        for step in steps:
            nx, ny = x + step[0], y + step[1]
            if 0 <= nx < n and 0 <= ny < m and graph[nx][ny] not in alphabet:
                queue.add((nx, ny, alphabet + graph[nx][ny]))
    return cnt


print(bfs(0, 0))