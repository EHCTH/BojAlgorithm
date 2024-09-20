n = int(input())
graph = [list(map(int, input().split())) for _ in range(n)]
# 모래가 흩날리는 방향

# 1% (1, 1), (-1, 1)
# 2% (2, 0), (-2, 0)
# 5% (0, -2)
# 7% (1, 0), (-1, 0)
# 10% (1, -1), (-1, -1)

left = [(1, 1, 0.01), (-1, 1, 0.01), (1, 0, 0.07), (-1, 0, 0.07), (1, -1, 0.1),
        (-1, -1, 0.1), (2, 0, 0.02), (-2, 0, 0.02), (0, -2, 0.05),  (0, -1, 0)]

# 10% 기준 아래로 1, 1, 1 - 1, -y , x
down = [(-y, x, ratio) for x, y, ratio in left]

# 10% 기준 우로 -1, 1, 1 1,  x, -y
right = [(x, -y, ratio) for x, y, ratio in left]

# 10% 기준 상로 -1, 1, -1 - 1 y, x
up = [(y, x, ratio) for x, y, ratio in left]
# 이동하고 남는 모래는 45%
# donw 하고 up 일때 1칸씩 증가 (down && up) == n - 1일 경우 증가 금지
# left down right up
mid_x = mid_y = n // 2
steps = [(0, -1), (1, 0), (0, 1), (-1, 0)]
tornado_dir = [left, down, right, up]
answer = 0
def set_point(i, j, dir):
    global answer
    sum =  0
    for dx, dy, ratio in tornado_dir[dir]:
        nx = i + dx
        ny = j + dy
        if ratio == 0:
            sand = graph[i][j] - sum
        else:
            sand = int(graph[i][j] * ratio)
            sum += sand
        if 0 <= nx < n and 0 <= ny < n:
            graph[nx][ny] += sand
        else:
            answer += sand
    graph[i][j] = 0

i, j = mid_x, mid_y
cnt = 1
while True:
    for y in range(j - 1, j - cnt - 1, -1):
        j = y
        set_point(i, j, 0)
    if not i and not j:
        break
    for x in range(i + 1, i + cnt + 1):
        i = x
        set_point(i, j, 1)

    if cnt != n - 1:
        cnt += 1
    for y in range(j + 1, j + cnt + 1):
        j = y
        set_point(i, j, 2)

    for x in range(i - 1, i - cnt - 1, -1):
        i = x
        set_point(i, j, 3)

    if cnt != n - 1:
        cnt += 1
print(answer)