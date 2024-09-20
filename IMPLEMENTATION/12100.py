n = int(input())
graph = [list(map(int, input().split())) for _ in range(n)]

#
# 좌
def tilt(new_graph, dir):
    while dir:
        dir-=1
        rotate(new_graph)
    for i in range(n):
        tilted = [0] * n
        idx = 0
        for j in range(n):
            if not new_graph[i][j]:
                continue
            if not tilted[idx]:
                tilted[idx] = new_graph[i][j]
            elif tilted[idx] == new_graph[i][j]:
                tilted[idx] *= 2
                idx += 1
            else:
                idx += 1
                tilted[idx] = new_graph[i][j]
        for j in range(n):
            new_graph[i][j] = tilted[j]

# 그래프 회전
def rotate(graph):
    copy_graph = [[0] * n for _ in range(n)]
    for i in range(n):
        for j in range(n):
            copy_graph[i][j] = graph[n - 1 - j][i]
    for i in range(n):
        for j in range(n):
            graph[i][j] = copy_graph[i][j]


max_num = 0
def solutions(cnt, graph):
    global max_num
    if cnt == 5:
        for i in range(n):
            for j in range(n):
                max_num = max(max_num, graph[i][j])
        return
    for i in range(4):
        new_graph = [item[::] for item in graph]
        tilt(new_graph, i)
        solutions(cnt + 1, new_graph)


solutions(0, graph)
print(max_num)