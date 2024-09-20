for T in range(int(input())):
    n = int(input())
    graph = [0] + list(map(int, input().split()))
    visited = [False] * (n + 1)
    cnt = 0


    def dfs(v):
        if visited[v]:
            return 0
        visited[v] = True
        i = graph[v]
        if not visited[i]:
            dfs(i)
        return 1


    for i in range(1, n + 1):
        cnt += dfs(i)
    print(cnt)