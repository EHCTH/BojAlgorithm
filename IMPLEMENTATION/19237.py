n, m, k = map(int, input().split())
visited = [[[0,0] for _ in range(n)] for _ in range(n)]
graph = [list(map(int, input().split())) for _ in range(n)]
shark_dir = [0] + list(map(int, input().split()))
shark_xy = [[] for _ in range(m + 1)]
for i in range(n):
    for j in range(n):
        if graph[i][j] > 0:
            visited[i][j][0] = graph[i][j]
            visited[i][j][1] = k
            shark_xy[graph[i][j]] = (i, j)
# 1, 2, 3, 4, 상 하 좌 우
# visited[0][0][0] = 상어번호,
# visited[0][0][1]= 냄새
shark = [1] * (m + 1)
shark[0] = 0
# print(shark)
answer = 0
steps = [(), (-1, 0), (1, 0), (0, -1), (0, 1)]
shark_move_steps = [[[]] for _ in range(m + 1)]
for i in range(1, m + 1):
    for j in range(1, 5):
        shark_move_steps[i].append(list(map(int, input().split())))

# nx, ny = 초기화 시켜야함
def shark_moving():
    global k, answer
    move_data = []
    for shark_num in range(1, len(shark_move_steps)):
        cnt = 0
        x, y = shark_xy[shark_num]
        if shark_dir[shark_num] == 0:
            continue
        for i in shark_move_steps[shark_num][shark_dir[shark_num]]:
            nx, ny = x + steps[i][0], y + steps[i][1]
            if 0 <= nx < n and 0 <= ny < n and not visited[nx][ny][1]:
                move_data.append((nx, ny, shark_num, k))
                shark_dir[shark_num] = i

                break
            cnt += 1
        if cnt == 4: # 만약 모든 방향이 막혀있을 경우
            for i in shark_move_steps[shark_num][shark_dir[shark_num]]:
                nx, ny = x + steps[i][0], y + steps[i][1]
                if 0 <= nx < n and 0 <= ny < n and visited[nx][ny][0] == shark_num:
                    shark_dir[shark_num] = i
                    move_data.append((nx, ny, shark_num, k))
                    break
    #  냄새가 사라짐
    for i in range(n):
        for j in range(n):
            if visited[i][j][0] > 0:
                if visited[i][j][1] - 1 == 0:
                    visited[i][j][1] = 0
                    visited[i][j][0] = 0
                else:
                    visited[i][j][1] -= 1
    move_data.sort(key= lambda x: x[2])
    for nx ,ny, shark_num, smell in move_data:
        if visited[nx][ny][0] == 0:
            visited[nx][ny][0] = shark_num
            visited[nx][ny][1] = smell
            shark_xy[shark_num] = (nx, ny)
        elif visited[nx][ny][0] == shark_num:
            visited[nx][ny][0] = shark_num
            visited[nx][ny][1] = smell
            shark_xy[shark_num] = (nx, ny)
        else: #먹혔을경우
            shark_dir[shark_num] = 0
            shark[shark_num] = 0
    return
while True:
    if sum(shark) == 1:
        print(answer)
        break
    elif answer >= 1000:
        print(-1)
        break
    shark_moving()
    answer += 1