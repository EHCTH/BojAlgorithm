N, M, K = map(int, input().split())
graph = [list(input()) for _ in range(N)]
visited = [[False] * M for _ in range(N)]
steps = [(-1, 0), (1, 0), (0, 1), (0, -1)]
visited[N - 1][0] = True
cnt = 0

def solutions(n, x, y):
    if graph[N - 1][0] == 'T':
        return

    global cnt
    if n == K:
        if x == 0 and y == M - 1:
            cnt += 1
        return
    for step in steps:
        nx, ny = x + step[0], y+ step[1]
        if 0 <= nx < N and 0 <= ny < M and graph[nx][ny] == '.' and not visited[nx][ny]:
            visited[nx][ny] = True
            solutions(n + 1, nx, ny)
            visited[nx][ny] = False

solutions(1, N - 1, 0)
print(cnt)