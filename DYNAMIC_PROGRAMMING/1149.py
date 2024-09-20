n = int(input())
# 0,1,2 = R, G, B
graph = [list(map(int, input().split())) for _ in range(n)]
dp =  [[0] * 3 for _ in range(n)]
for i in range(n):
    for j in range(3):
        if j == 0:
            dp[i][j] = min(dp[i - 1][1], dp[i - 1][2]) + graph[i][j]
        elif j == 1:
            dp[i][j] = min(dp[i - 1][0], dp[i - 1][2]) + graph[i][j]
        else:
            dp[i][j] = min(dp[i - 1][0], dp[i - 1][1]) + graph[i][j]
print(min(dp[n - 1]))