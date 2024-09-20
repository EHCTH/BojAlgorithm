import collections

n, m = map(int, input().split())
d = list(map(int, input().split()))
x, y = d[:2]
dir = d[-1]
graph = [list(map(int, input().split())) for _ in range(n)]

steps = [(-1, 0), (0, 1), (1, 0), (0, -1)]
dir_li = {0: steps[2], 1: steps[3], 2: steps[0], 3: steps[1]}
# 0샹, 1우, 2하, 3좌
# 0은 청소되지않은칸
# 1은 벽
clean = 2
i = 0
while True:
    if not graph[x][y]:
        graph[x][y] = clean
    is_clean = True
    for _ in range(len(steps)):
        dir = (dir + 3) % 4
        nx, ny = x + steps[dir][0], y + steps[dir][1]
        if not graph[nx][ny]:
            x, y = nx, ny
            is_clean = False
            break
    if not is_clean:
       continue
    else:
        nx, ny = x + dir_li[dir][0], y + dir_li[dir][1]
        if graph[nx][ny] == 1:
            break
        x, y = nx, ny
answer = 0
for i in range(n):
    for j in range(m):
        if graph[i][j] == 2:
            answer += 1
print(answer)