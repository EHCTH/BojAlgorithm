n = int(input())
graph = [list(map(int, input().split())) for _ in range(n)]
dp = [[0] * n for _ in range(n)]
dp[0][0] = graph[0][0]
for i in range(1, n):
    for j in range(len(graph[i])):
        dp[i][j] = max(dp[i - 1][j - 1], dp[i - 1][j]) + graph[i][j]
print(max(dp[n - 1]))