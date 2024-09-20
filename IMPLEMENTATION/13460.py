import collections

n, m = map(int, input().split())
graph = [list(input()) for _ in range(n)]
distance = [[[[-1] * m for _ in range(n)] for _ in range(m)] for _ in range(n)]

for i in range(n):
    for j in range(m):
        if graph[i][j] == 'R':
            red = (i, j)
            graph[i][j] = '.'
        elif graph[i][j] == 'B':
            blue = (i, j)
            graph[i][j] = '.'
distance[red[0]][red[1]][blue[0]][blue[1]] = 0
steps = [(0, 1), (0, -1), (1, 0), (-1, 0)]


# 동, 서, 남, 북
def solutions(red, blue):
    queue = collections.deque()
    queue.append((red, blue))
    while queue:
        red, blue = queue.popleft()
        rx, ry, bx, by = red[0], red[1], blue[0], blue[1]
        cnt = distance[rx][ry][bx][by]
        if cnt >= 10:
            return -1
        for i in range(len(steps)):
            nrx, nry, nbx, nby = rx,  ry, bx, by
            while graph[nbx + steps[i][0]][nby + steps[i][1]] == '.':
                nbx += steps[i][0]
                nby += steps[i][1]
            if graph[nbx + steps[i][0]][nby + steps[i][1]] == 'O':
                continue
            while graph[nrx + steps[i][0]][nry + steps[i][1]] == '.':
                nrx += steps[i][0]
                nry += steps[i][1]
            if graph[nrx + steps[i][0]][nry + steps[i][1]] == 'O':
                return cnt + 1
            if nrx == nbx and nry == nby:
                if i == 0:
                    if ry < by:
                        nry -= 1
                    else:
                        nby -=1
                elif i == 1:
                    if ry > by:
                        nry += 1
                    else:
                        nby += 1
                elif i == 2:
                    if rx < bx:
                        nrx -= 1
                    else:
                        nbx -= 1
                else:
                    if rx > bx:
                        nrx += 1
                    else:
                        nbx += 1
            if distance[nrx][nry][nbx][nby] == -1:
                distance[nrx][nry][nbx][nby] = cnt + 1
                queue.append(((nrx, nry), (nbx, nby)))
    return -1
print(solutions(red, blue))