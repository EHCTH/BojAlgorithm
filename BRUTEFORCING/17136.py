shape = [0, 0, 0, 0, 0, 0]
n = 10
graph = [list(map(int, input().split())) for _ in range(n)]
min_value = 1e9

def patable(x, y, k):
    for i in range(k):
        for j in range(k):
            nx, ny = x + i, y + j
            if 0 <= nx < n and 0 <= ny < n and graph[nx][ny] == 1:
                pass
            else:
                return False
    for i in range(k):
        for j in range(k):
            nx, ny, = x + i, y + j
            graph[nx][ny] = 2
    return True

def soluitons(x, y, cnt):
    global min_value
    if x == 10:
        min_value = min(min_value, cnt)
        return
    if y == 10:
        soluitons(x + 1, 0, cnt)
    else:
        if graph[x][y] == 1:
            for k in range(1, 6):
                if shape[k] == 5:
                    continue
                if patable(x, y, k):
                    shape[k] += 1
                    soluitons(x, y + k, cnt + 1)
                    shape[k] -= 1
                    for i in range(k):
                        for j in range(k):
                            graph[x + i][y + j] = 1
        else:
            soluitons(x, y + 1, cnt)
soluitons(0, 0, 0)
if min_value == 1e9:
    min_value = - 1
print(min_value)