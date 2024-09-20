import collections
n, m, g, r = map(int, input().split())
graph = [list(map(int, input().split())) for _ in range(n)]

total = g + r
data = []
flower = 0
for i in range(n):
    for j in range(m):
        if graph[i][j] == 2:
            flower += 1
            data.append((i, j))

visited = [[[-1] * m for _ in range(n)] for _ in range(2)]
steps = [(-1, 0), (1, 0), (0, 1), (0, -1)]
result = 0
queue = collections.deque()
RED = 1
GREEN = 2
FLOWER = 3

def run(redL, greenL):
    cnt = 0
    queue = collections.deque()
    new_visited = [[[-1] * m for _ in range(n)] for _ in range(2)]
    for i in redL:
        new_visited[1][i[0]][i[1]] = RED
        new_visited[0][i[0]][i[1]] = 0
        queue.append(i)
    for i in greenL:
        new_visited[1][i[0]][i[1]] = GREEN
        new_visited[0][i[0]][i[1]] = 0
        queue.append(i)
    while queue:
        x, y = queue.popleft()
        if new_visited[1][x][y] == FLOWER:
            continue
        for step in steps:
            nx, ny = x + step[0], y + step[1]
            if 0 <= nx < n and 0 <= ny < m and graph[nx][ny] != 0:
                if new_visited[0][nx][ny] == -1 and new_visited[1][nx][ny] == -1:
                    new_visited[0][nx][ny] = new_visited[0][x][y] + 1
                    new_visited[1][nx][ny] = new_visited[1][x][y]
                    queue.append((nx, ny))
                elif new_visited[1][nx][ny] == GREEN:
                    if new_visited[1][x][y] == RED and new_visited[0][x][y] + 1 == new_visited[0][nx][ny]:
                        new_visited[1][nx][ny] = FLOWER
                        cnt += 1
                elif new_visited[1][nx][ny] == RED:
                    if new_visited[1][x][y] == GREEN and new_visited[0][x][y] + 1 == new_visited[0][nx][ny]:
                        new_visited[1][nx][ny] = FLOWER
                        cnt += 1
    return cnt

redL, greenL = [], []
def solutions(n, red, green):
    global index
    global result
    if n == flower:
        if red == 0 and green == 0:
            answer = run(redL, greenL)
            result = max(answer, result)
        return
    else:
        if red != 0:
            redL.append(data[n])
            solutions(n + 1, red - 1, green)
            redL.pop()
        if green != 0:
            greenL.append(data[n])

            solutions(n + 1, red, green - 1)
            greenL.pop()
        solutions(n + 1, red, green)


solutions(0, r, g)
print(result)