import collections

n = 5
graph = [list(input()) for _ in range(n)]
vistied = [[0] * n for _ in range(n)]
answer = 0
arr = []
steps = [(-1, 0), (1, 0), (0, 1), (0, -1)]
def check(idx):
    new_visited = [item[::] for item in vistied]
    queue = collections.deque()
    new_visited[idx // 5][idx % 5] = 1
    queue.append(idx)
    while queue:
        idx = queue.pop()
        x, y = idx // 5, idx % 5
        for step in steps:
            nx, ny = x + step[0], y + step[1]
            if 0 <= nx < n and 0 <= ny < n and not new_visited[nx][ny] and (nx * 5 + ny) in arr:
                new_visited[nx][ny] = 1
                queue.append((nx * 5 + ny))
    return sum(map(sum, new_visited))


def solutions(count, idx, y_num):
    global answer
    if (y_num >= 4) or (25 - idx < 7 - count):
        return
    if count == 7:
        if check(arr[0]) == 7:
            answer += 1
        return
    else:
        x, y = idx // 5, idx % 5
        arr.append(idx)
        solutions(count + 1, idx + 1, y_num + int(graph[x][y] == 'Y'))
        arr.pop()
        solutions(count, idx + 1, y_num)
solutions(0, 0, 0)
print(answer)