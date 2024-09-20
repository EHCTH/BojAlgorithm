from collections import deque

n, m = map(int,input().split())
array = [list(map(int,input().split())) for _ in range(n)]

steps = [(-1, 0), (1, 0), (0, -1), (0, 1)]
result = []
virus_index = []
for i in range(n):
    for j in range(m):
        if array[i][j] == 2:
            virus_index.append([i, j])


def virus(graph):
    array2 = [item[::] for item in array]
    queue = deque(graph)
    while queue:
        x, y = queue.popleft()
        for step in steps:
            nx, ny = x + step[0], y + step[1]
            if 0 <= nx < n and 0 <= ny < m and array2[nx][ny] == 0:
                array2[nx][ny] = 2
                queue.append([nx, ny])
    return array2


def search(new_array):
    cnt = 0
    for i in range(n):
        for j in range(m):
            if new_array[i][j] == 0:
                cnt += 1

    return cnt


def count(nu):
    if nu != 3:
        for i in range(n):
            for j in range(m):
                if array[i][j] == 0:
                    array[i][j] = 1
                    count(nu + 1)
                    array[i][j] = 0

    else:
        array2 = virus(virus_index)
        result.append(search(array2))


count(0)
print(max(result))