import collections

N, M = map(int, input().split())
graph = [list(map(int, input().split())) for _ in range(N)]
# ←, ↖, ↑, ↗, →, ↘, ↓, ↙  1번 부터 표기
steps = [(),
         (0, -1), (-1, -1), (-1, 0), (-1, 1), (0, 1), (1, 1), (1, 0), (1, -1)]

# 비구름 위치 (n - 1, 0), (n - 1, 1), (n -2, 0), (n-2, 1)
# 움직일경우 (x or y + steps[d] * s) % N

rain_cloud = [(N - 1, 0), (N - 1, 1), (N - 2, 0), (N - 2, 1)]
move = [list(map(int, input().split())) for _ in range(M)]


def water_copy_magic(final_move_cloud, visited):
    new_rain_move = []
    for x, y in final_move_cloud:
        cnt = 0
        for i in range(2, len(steps), 2):
            nx, ny = x + steps[i][0], y + steps[i][1]
            if 0 <= nx < N and 0 <= ny < N and graph[nx][ny]:
                cnt += 1
        graph[x][y] += cnt

    for i in range(N):
        for j in range(N):
            if not visited[i][j] and graph[i][j] >= 2:
                graph[i][j] -= 2
                new_rain_move.append((i, j))
    return new_rain_move


def solutions(rain_cloud, d, s):
    queue = collections.deque(rain_cloud)
    visited = [[False] * N for _ in range(N)]
    final_move_cloud = []
    while queue:
        x, y = queue.popleft()
        nx, ny = (x + steps[d][0] * s) % N, (y + steps[d][1] * s) % N
        visited[nx][ny] = True
        graph[nx][ny] += 1
        final_move_cloud.append((nx, ny))
    return water_copy_magic(final_move_cloud, visited)


for d, s in move:
    rain_cloud = solutions(rain_cloud, d, s)
print(sum(map(sum, graph)))