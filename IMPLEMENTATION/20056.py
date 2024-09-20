N, M, K = map(int, input().split())

# 위치는(r, c), 질량m 속력s 방향d
graph = [[[] for _ in range(N)] for _ in range(N)]
steps = [(-1, 0), (-1, 1), (0, 1), (1, 1), (1, 0), (1, -1), (0, -1), (-1, -1)]
# 질량 (합쳐진 파이어볼 질량의 합)/5
# 속력 (합쳐진 파이어볼 속력의 합)/(합쳐진 파이어볼의 개수)
fire_ball = []
for _ in range(M):
    r, c, m, s, d = map(int, input().split())
    fire_ball.append((r - 1,c -  1, m, s, d))
for _ in range(K):
    while fire_ball:
        r, c, m, s, d = fire_ball.pop()
        nr, nc = (r + s * steps[d][0]) % N, (c + s * steps[d][1]) % N
        graph[nr][nc].append((m, s, d))


    for i in range(N):
        for j in range(N):
            odd, even = 0, 0
            if len(graph[i][j]) >= 2:
                mass = sum(map(lambda x: x[0], graph[i][j])) // 5
                speed = sum(map(lambda x: x[1], graph[i][j])) // len(graph[i][j])
                for c in range(len(graph[i][j])):
                    if graph[i][j][c][2] % 2 == 0:
                        even += 1
                    else:
                        odd += 1
                if odd == len(graph[i][j]) or even == len(graph[i][j]):
                    dir = [0, 2, 4, 6]
                else:
                    dir = [1, 3, 5, 7]
                if mass:
                    for d in dir:
                        fire_ball.append((i, j, mass, speed, d))
            elif len(graph[i][j]) == 1:
                fire_ball.append((i, j, graph[i][j][0][0], graph[i][j][0][1], graph[i][j][0][2]))
            graph[i][j] = []
print(sum(map(lambda x: x[2], fire_ball)))