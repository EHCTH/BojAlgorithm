import collections

N, m, k = map(int, input().split())
arr = [list(map(int, input().split())) for _ in range(N)]
visited = [[False] * m for _ in range(N)]
answer = -1e9
steps = [(-1, 0), (1, 0), (0, 1), (0, -1)]


def check_visited(x, y):
    for step in steps:
        nx, ny = x + step[0], y + step[1]
        if 0 <= nx < N and 0 <= ny < m and visited[nx][ny]:
            return False
    return True


def solutions(x, y, n, sm):
    global answer
    if n == k:
        answer = max(answer, sm)
        return

    for i in range(x, N):
        for j in range(m):
            if not visited[i][j] and check_visited(i, j):
                visited[i][j] = True
                solutions(i, j, n + 1, sm + arr[i][j])
                visited[i][j] = False


solutions(0, 0, 0, 0)
print(answer)