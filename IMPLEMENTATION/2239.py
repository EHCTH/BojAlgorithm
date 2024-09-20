import sys
n = 9
graph = [list(map(int, input())) for _ in range(n)]
arr = [(i, j) for i in range(n) for j in range(n) if graph[i][j] == 0]

def check_row(y, a):
    for i in range(n):
        if a == graph[i][y]:
            return False
    return True

def check_column(x, a):
    for j in range(n):
        if a == graph[x][j]:
            return False
    return True

def check_rect(x, y, a):
    nx, ny = x // 3 * 3, y // 3 * 3
    for i in range(n // 3):
        for j in range(n // 3):
            if a == graph[nx + i][ny + j]:
                return False
    return True
# 넓이 가로 세로
def solution(cnt):
    if cnt == len(arr):
        for i in range(len(graph)):
            for j in range(len(graph)):
                print(graph[i][j], end="")
            print()
        exit(0)
    else:
        x, y = arr[cnt][0], arr[cnt][1]
        for i in range(1, n + 1):
            if check_row(y, i) and check_column(x, i) and check_rect(x, y, i):
                graph[x][y] = i
                solution(cnt + 1)
                graph[x][y] = 0

solution(0)