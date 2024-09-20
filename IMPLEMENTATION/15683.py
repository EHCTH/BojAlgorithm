n, m = map(int, input().split())
graph = [list(map(int, input().split())) for _ in range(n)]
CCTV = [
    [(0,), (1,), (2,), (3,)],
    [(1, 3), (0, 2)],
    [(0, 1), (0, 3), (2, 3), (1, 2)],
    [(0, 1, 2), (0, 2, 3), (1, 2, 3), (0, 1, 3)],
    [(0, 1, 2, 3)]
]

steps = [(-1, 0), (0, 1), (1, 0), (0, -1)]
data = []
min_num = 1e9

for i in range(n):
    for j in range(m):
        if 1 <= graph[i][j] <= 5:
            data.append((graph[i][j], i, j))

def search(graph, type, x, y):
    for i in type:
        nx, ny = x + steps[i][0], y + steps[i][1]
        while 0 <= nx < n and 0 <= ny < m:
            if not graph[nx][ny]:
                graph[nx][ny] = 7
            elif graph[nx][ny] == 6:
                break
            else:
                nx += steps[i][0]
                ny += steps[i][1]


def solutions(count, graph):
    global min_num
    if count == len(data):
        answer = 0
        for i in range(n):
            answer += graph[i].count(0)
        min_num = min(min_num, answer)
        return
    num, x, y = data[count]
    for type in CCTV[num - 1]:
        new_graph = [item[::] for item in graph]
        search(new_graph, type, x, y)
        solutions(count + 1, new_graph)
solutions(0, graph)
print(min_num)