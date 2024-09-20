n = int(input())
graph = [list(map(int, input().split())) for _ in range(n)]
def dfs(x, y, case):
    answer = 0
    if x == n-1 and y == n-1:
        return 1
    # 0 가로 , 1 세로, 2 대각
    if case == 0 or case == 2:
        if y + 1 < n and graph[x][y+1] == 0:
            answer += dfs(x, y+1, 0)
    if case == 1 or case == 2:
        if x + 1 < n and graph[x+1][y] == 0:
            answer += dfs(x+1, y, 1)
    if case == 0 or case == 1 or case == 2:
        if x + 1 < n and y + 1 < n and graph[x+1][y] == 0 and graph[x][y+1] == 0 and graph[x+1][y+1] == 0:
            answer += dfs(x+1, y+1, 2)
    return answer

print(dfs(0, 1, 0))