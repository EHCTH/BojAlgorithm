n = 9
graph = [list(map(int, input().split())) for _ in range(n)]
arr = [(i, j) for i in range(n) for j in range(n) if graph[i][j] == 0]

# 가로 세로 3 * 3 크기 확인

def checkCol(x, a):  # 가로
    for j in range(n):
        if a == graph[x][j]:
            return False
    return True


def checkRow(y, a):  # 세로
    for i in range(n):
        if a == graph[i][y]:
            return False
    return True


def checkRect(x, y, a):  # 넓이
    nx, ny = x // 3 * 3, y // 3 * 3
    for i in range(n // 3):
        for j in range(n // 3):
            if a == graph[nx + i][ny + j]:
                return False
    return True

def solution(cnt):
    if cnt == len(arr):
        for i in graph:
            print(*i)
        exit(0)
    else:
        x, y = arr[cnt][0], arr[cnt][1]
        for i in range(1, n + 1):
            if checkCol(x, i) and checkRow(y, i) and checkRect(x, y, i):
                graph[x][y] = i
                solution(cnt + 1)
                graph[x][y] = 0

solution(0)