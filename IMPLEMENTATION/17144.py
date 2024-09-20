n, m, t = map(int, input().split())
graph = [list(map(int, input().split())) for _ in range(n)]
# 미세먼지 확산시
steps = [(1, 0), (0, 1), (-1, 0), (0, -1)]
# graph[x][y] -= (graph[x][y] // 5) * 확산된 범위
# 미세먼지가 확산되는 양은 graph[x][y] // 5 이다

# 공기 청정기는 항상 0번열에 있다
clean_robot_1, clean_robot_2 = [], []
for i in range(n):
    for j in range(m):
        if graph[i][j] == -1:
            if not clean_robot_1:
                clean_robot_1.extend((i, j))
            else:
                clean_robot_2.extend((i, j))


# 로봇1은 반시계 방향으로
# 로봇2는 시계 방향으로 밀려난다

def up(robot):
    x, y = robot[0], robot[1] + 1
    steps = [(0, 1), (-1, 0), (0, -1), (1, 0)]
    dir = 0
    tmp = graph[x][y]
    graph[x][y] = 0
    while True:
        nx, ny = x + steps[dir][0] , y + steps[dir][1]
        while 0 <= nx < n and 0 <= ny < m:
            if nx == robot[0] and ny == robot[1]:
                return
            next_tmp = graph[nx][ny]
            graph[nx][ny] = tmp
            tmp = next_tmp
            nx += steps[dir][0]
            ny += steps[dir][1]
        nx -= steps[dir][0]
        ny -= steps[dir][1]
        x, y = nx,  ny
        dir += 1


def down(robot):
    x, y = robot[0], robot[1] + 1
    steps = [(0, 1), (1, 0), (0, -1), (-1, 0)]
    dir = 0
    tmp = graph[x][y]
    graph[x][y] = 0
    while True:
        nx, ny = x + steps[dir][0], y + steps[dir][1]
        while 0 <= nx  < n and 0 <= ny < m:
            if nx == robot[0] and ny == robot[1]:
                return
            next_tmp = graph[nx][ny]
            graph[nx][ny] = tmp
            tmp = next_tmp
            nx += steps[dir][0]
            ny += steps[dir][1]
        nx -= steps[dir][0]
        ny -= steps[dir][1]
        x, y = nx, ny
        dir += 1


for _ in range(t):
    tmp = []
    tmp_2 = []
    for i in range(n):
        for j in range(m):
            if graph[i][j] and graph[i][j] != -1:
                cnt = 0
                for step in steps:
                    nx, ny = i + step[0], j + step[1]
                    if 0 <= nx < n and 0 <= ny < m and graph[nx][ny] != -1:
                        if graph[nx][ny]:
                            tmp.append((nx, ny, graph[i][j] // 5))
                        else:
                            tmp_2.append((nx, ny, graph[i][j] // 5))
                        cnt += 1
                graph[i][j] -= (graph[i][j] // 5) * cnt

    for x, y, plus in tmp_2:
        graph[x][y] += plus
    for x, y, plus in tmp:
        graph[x][y] += plus
    up(clean_robot_1)
    down(clean_robot_2)

answer= 0
for i in range(n):
    for j in range(m):
        if graph[i][j] != -1:
            answer += graph[i][j]
print(answer)