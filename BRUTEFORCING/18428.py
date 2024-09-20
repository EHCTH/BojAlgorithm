from collections import deque

teacher_index = []

n = int(input())
steps = [(-1, 0), (1, 0), (0, 1), (0, -1)]
answer = False
graph = []


for i in range(n):
    graph.append(list(input().split()))
    for j in range(n):
        if graph[i][j] == 'T':
            teacher_index.append((i, j))



def search(data):
    queue = deque(data)
    while queue:
        x, y = queue.popleft()
        for step in steps:
            nx, ny = x + step[0], y + step[1]
            while 0 <= nx < n and 0 <= ny < n and graph[nx][ny] != "O":
                if graph[nx][ny] == "S":
                    return False
                else:
                    nx += step[0]
                    ny += step[1]
    return True


def wall(cnt):
    global answer
    if cnt != 3:
        for i in range(n):
            for j in range(n):
                if graph[i][j] == "X":
                    graph[i][j] = "O"
                    wall(cnt + 1)
                    graph[i][j] = "X"
    else:
        if search(teacher_index):
            answer = True


wall(0)

if answer:
    print("YES")
else:
    print("NO")