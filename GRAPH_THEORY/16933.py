# 낮과 밤이 번갈아가면서 등장한다. 가장 처음에 이동할 때는 낮이고, 한 번 이동할 때마다 낮과 밤이 바뀌게 된다.
# 이동하지 않고 같은 칸에 머무르는 경우에도 낮과 밤이 바뀌게 된다.
# 벽은 낮에만 부술 수 있다.
# 낮은 짝수,  밤은 홀수
import collections
import sys
input = sys.stdin.readline

n, m, k = map(int, input().split())
graph = [list(map(int, input().strip())) for _ in range(n)]
distance = [[[0] * m for _ in range(n)] for _ in range(k + 1)]

steps = [(-1, 0), (1, 0), (0 ,-1), (0, 1)]
def bfs(z, x, y, daytime):
    queue = collections.deque()
    queue.append((z, x, y, daytime))
    distance[z][x][y] = 1
    while queue:
        z, x, y, daytime = queue.popleft()
        add = 0
        if x == n - 1 and y == m - 1:
            return distance[z][x][y]
        for step in steps:
            nx, ny = x + step[0], y + step[1]
            if nx == 0 and ny == 0:
                continue
            if 0 <= nx < n and 0 <= ny < m and not distance[z][nx][ny]:
                if graph[nx][ny] == 1 and z != k and not distance[z + 1][nx][ny]:
                    if daytime % 2 == 0:
                        distance[z + 1][nx][ny] = distance[z][x][y] + 1
                        queue.append((z + 1, nx, ny,  daytime +1))
                    elif daytime % 2 == 1:
                        add = 1
                        queue.append((z, x, y, daytime + 1))
                elif not graph[nx][ny]:
                    distance[z][nx][ny] = distance[z][x][y] + 1
                    queue.append((z, nx, ny, daytime + 1))
        distance[z][x][y] += add
    return -1
print(bfs(0, 0, 0, 0))