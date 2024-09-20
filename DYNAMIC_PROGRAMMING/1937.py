n = int(input())
graph = [list(map(int, input().split())) for _ in range(n)]
answer = -1e9
steps = [(-1, 0), (1, 0), (0, -1), (0, 1)]
distance = [[-1] * n for _ in range(n)]
def solutions(x, y):
    if distance[x][y] == -1:
        distance[x][y] = 0
        for step in steps:
            nx, ny = x + step[0], y + step[1]
            if 0 <= nx < n and 0 <= ny < n and graph[x][y] < graph[nx][ny]:
                distance[x][y] = max(distance[x][y], solutions(nx, ny))
    return distance[x][y] + 1

for i in range(n):
    for j in range(n):
        answer = max(answer, solutions(i, j))
print(answer)