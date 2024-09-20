from collections import deque

import sys

input = sys.stdin.readline
n, k = map(int, input().split())
data = []
array = []

for i in range(n):
    array.append(list(map(int, input().split())))
    for j in range(n):
        if array[i][j] != 0:
            data.append((array[i][j], 0, i, j))

target_s, target_x, target_y = map(int, input().split())

steps = [(-1, 0), (1, 0), (0, -1), (0, 1)]

sort_data = sorted(data, key=lambda x: x[0])


def compptiton_virus(sort_data: list):
    queue = deque(sort_data)
    while queue:
        virus, s, x, y = queue.popleft()
        if s == target_s:
            break
        for step in steps:
            nx, ny = x + step[0], y + step[1]
            if 0 <= nx < n and 0 <= ny < n and array[nx][ny] == 0:
                array[nx][ny] = virus
                queue.append([virus, s + 1, nx, ny])
    return array[target_x - 1][target_y - 1]


print(compptiton_virus(sort_data))