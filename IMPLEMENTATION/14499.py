n, m, x, y, k = map(int, input().split())
graph = []
steps = [[], (0, 1),(0, -1),(-1, 0),(1, 0)]
# 1동, 2서, 3북, 4남
dice = [-1, 0, 0, 0, 0, 0, 0]
# dice = 1 ~ 6
dice_rotation = [[],[4, 2, 1, 6, 5, 3], [3, 2, 6, 1, 5, 4], [5, 1, 3, 4, 6, 2], [2, 6, 3, 4, 1, 5]]
idx = 1
# 출력은 dice_rotation[n][0]
# 아래를 기준으로 복사 즉 마지막 다이스의 마지막인덱스
for i in range(n):
    graph.append(list(map(int, input().split())))

move = list(map(int, input().split()))
for i in move:
    a, b = steps[i]
    if 0 <= x + a < n and 0 <= y + b < m:
        tmp = [-1]
        for j in dice_rotation[i]:
            tmp.append(dice[j])
        dice = tmp
        x += a
        y += b
        print(dice[1])
        if graph[x][y]:
            dice[6] = graph[x][y]
            graph[x][y] = 0
        else:
            graph[x][y] = dice[6]