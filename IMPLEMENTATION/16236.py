from collections import deque

def bfs(n, shark_x, shark_y, shark_size, grid):
    dx = [-1, 1, 0, 0]
    dy = [0, 0, -1, 1]
    queue = deque([(shark_x, shark_y)])
    visited = [[False] * n for _ in range(n)]
    visited[shark_x][shark_y] = True
    dist = [[0] * n for _ in range(n)]
    fish_candidates = []

    while queue:
        x, y = queue.popleft()

        for i in range(4):
            nx, ny = x + dx[i], y + dy[i]

            if 0 <= nx < n and 0 <= ny < n and not visited[nx][ny] and grid[nx][ny] <= shark_size:
                visited[nx][ny] = True
                dist[nx][ny] = dist[x][y] + 1
                queue.append((nx, ny))

                if 0 < grid[nx][ny] < shark_size:
                    fish_candidates.append((dist[nx][ny], nx, ny))

    return fish_candidates

def solution(n, grid):
    shark_size = 2
    shark_eaten = 0
    shark_x, shark_y = 0, 0

    for i in range(n):
        for j in range(n):
            if grid[i][j] == 9:
                shark_x, shark_y = i, j
                grid[i][j] = 0

    result = 0
    while True:
        fish_candidates = bfs(n, shark_x, shark_y, shark_size, grid)
        if not fish_candidates:
            break

        fish_candidates.sort()
        dist, fish_x, fish_y = fish_candidates[0]
        result += dist

        shark_x, shark_y = fish_x, fish_y
        grid[shark_x][shark_y] = 0
        shark_eaten += 1

        if shark_eaten == shark_size:
            shark_size += 1
            shark_eaten = 0

    return result

n = int(input())
grid = [list(map(int, input().split())) for _ in range(n)]

result = solution(n, grid)
print(result)