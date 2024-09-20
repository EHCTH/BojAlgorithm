import collections
min_num = 1e9
n, m = map(int, input().split())
graph = [list(map(int, input().split())) for _ in range(n)]
data = []
for i in range(n):
    for j in range(n):
        if graph[i][j] == 1:
            graph[i][j] = '-'
        elif graph[i][j] == 2:
            graph[i][j] = 'virus'
            data.append((i, j))
        else:
            graph[i][j] = '.'
steps = [(1, 0), (0, 1), (-1, 0), (0, -1)]
def spread(vius_list):
    global min_num
    queue = collections.deque()
    for i in vius_list:
        queue.append(data[i])
    new_graph = [item[::] for item in graph]
    while queue:
        x, y = queue.popleft()
        for step in steps:
            nx, ny = x + step[0], y + step[1]
            if 0 <= nx < n and 0 <= ny < n and (new_graph[nx][ny] == '.' or new_graph[nx][ny] == 'virus'):
                new_graph[nx][ny] = new_graph[x][y] + 1
                queue.append((nx, ny))
    max_num = -1e9
    for i in range(n):
        for j in range(n):
            if new_graph[i][j] == '.' or new_graph[i][j] == 'virus':
                return
            elif new_graph[i][j] != '-':
                max_num = max(max_num, new_graph[i][j])
    if max_num != -1e9:
        min_num = min(min_num, max_num)
    return

def solutions(cnt, virus, virus_list):
    global answer
    if cnt == len(data):
        spread(virus_list)
        return
    if graph[data[cnt][0]][data[cnt][1]] == 'virus' and virus != m:
        graph[data[cnt][0]][data[cnt][1]] = 0
        solutions(cnt + 1, virus + 1, virus_list + [cnt])
        graph[data[cnt][0]][data[cnt][1]] = 'virus'
    solutions(cnt + 1, virus, virus_list)
solutions(0, 0, [])

if min_num == 1e9:
    print(-1)
else:
    print(min_num)